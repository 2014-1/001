package com.byd.player.audio;

import java.lang.ref.WeakReference;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.byd.player.AuxAudioPlayActivity;
import com.byd.player.BaseActivity;
import com.byd.player.R;
import com.byd.player.audio.AudioDeleteAsyncTask.DeleteListener;
import com.byd.player.audio.AudioPlayerService.OnSongChangedListener;
import com.byd.player.audio.AudioSearchTask.SearchListener;
import com.byd.player.bluetooth.BTPlayerActivity;
import com.byd.player.config.Constants;
import com.byd.player.receiver.USBMountReceiver;
import com.byd.player.utils.ToastUtils;
import com.byd.player.utils.VideoContentObserver;

/**
 * 启动Audio List页面，指定default的页面的方法：
 * Intent intent = new Intent();
 * intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
 * intent.setClassName("com.byd.player.audio", "com.byd.player.audio.AudioListActivity");
 * intent.putExtra("audio_page", number); // number: 0-本地, 1-sdcard, 2-usb, 3-aux, 4-手机
 * startActivity(intent)
 */
public class AudioListActivity extends BaseActivity implements OnItemClickListener,
OnItemLongClickListener, SearchListener, DeleteListener {
    private final static String TAG = "AudioListActivity";

    private final static String TAB_INDEX = "audio_page";

    public final static int TAB_INDEX_LOCAL = 0;
    public final static int TAB_INDEX_SDCARD = 1;
    public final static int TAB_INDEX_USB = 2;
    public final static int TAB_INDEX_AUX = 3;
    public final static int TAB_INDEX_MOBILE = 4;

    public final static int MODE_NORMAL = 0;
    public final static int MODE_EDIT = MODE_NORMAL + 1;
    public final static int MODE_SEARCH = MODE_EDIT + 1;

    private final static int REQCODE_AUX = 100;
    private final static int REQCODE_BT = 200;

    private final static int REQUEST_CODE_PLAY = 999;

    private final int[] TAB_IDS = new int[] { R.id.btn_audio_Local, R.id.btn_audio_sdcard,
            R.id.btn_audio_usb, R.id.btn_audio_aux, R.id.btn_audio_mobile };
    private final int[] TAB_NORMAL_BGS = new int[] { R.drawable.bg_audio_local_normal,
            R.drawable.bg_sdcard_normal, R.drawable.bg_usb_normal, R.drawable.bg_aux_normal,
            R.drawable.bg_mobile_normal, };
    private final int[] TAB_SELECTED_BGS = new int[] { R.drawable.bg_audio_local_selected,
            R.drawable.bg_sdcard_selcted, R.drawable.bg_usb_selected, R.drawable.bg_aux_selected,
            R.drawable.bg_mobile_selected, };

    private GridView mAudioList = null;
    private AudioAdapter mAdapter = null;

    private EditText mSearchText = null;
    private ProgressDialog mProgressDialog = null;

    private USBMountReceiver mUSBMountReceiver;

    private MediaStoreChangedHandler mMediaStoreChangedHandler;

    private OnSongChangedListener mOnSongChangedListener;

    static class MediaStoreChangedHandler extends Handler {
        WeakReference<AudioListActivity> wrActivity = null;

        MediaStoreChangedHandler(AudioListActivity activity) {
            wrActivity = new WeakReference<AudioListActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            AudioListActivity activity = wrActivity.get();
            switch (msg.what) {
                case VideoContentObserver.INTERNAL_VIDEO_CONTENT_CHANGED:
                case VideoContentObserver.EXTERNAL_VIDEO_CONTENT_CHANGED:
                    AudioLoaderManager.getInstance().loadData(AudioLoaderManager.EXTERNAL_SDCARD_TYPE);
                    AudioLoaderManager.getInstance().loadData(AudioLoaderManager.EXTERNAL_USB_TYPE);
                    AudioLoaderManager.getInstance().loadData(AudioLoaderManager.INTERNAL_TYPE);
                    break;
            }
            super.handleMessage(msg);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mMediaStoreChangedHandler = new MediaStoreChangedHandler(this);

        AudioLoaderManager.getInstance().init(getApplicationContext());
        refreshDatas();

        setContentView(R.layout.audio_list_view);

        initViews();
        setMode(MODE_NORMAL);

        registerUSBStateChangedReceiver();

        registerMediaStoreChangedObserver();

        if (getIntent() != null) {
            final int tabIndex = getIntent().getIntExtra(TAB_INDEX, TAB_INDEX_LOCAL);
            tabIndex(tabIndex);
            Log.d(TAG, "onCreate tabIndex=" + tabIndex);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            final int tabIndex = intent.getIntExtra(TAB_INDEX, TAB_INDEX_LOCAL);
            tabIndex(tabIndex);
            Log.d(TAG, "onNewIntent tabIndex=" + tabIndex);
        }
    }

    /**
     * Reload Audio List form MediaProvider.
     */
    public void refreshDatas() {
        AudioLoaderManager.getInstance().loadData(AudioLoaderManager.EXTERNAL_SDCARD_TYPE);
        AudioLoaderManager.getInstance().loadData(AudioLoaderManager.EXTERNAL_USB_TYPE);
        AudioLoaderManager.getInstance().loadData(AudioLoaderManager.INTERNAL_TYPE);
    }

    private void initViews() {
        mAudioList = (GridView) findViewById(R.id.audio_grid_list);
        mAdapter = new AudioAdapter(this, getLayoutInflater());
        mAudioList.setAdapter(mAdapter);
        mAudioList.setOnItemClickListener(this);
        mAudioList.setOnItemLongClickListener(this);

        initHeaderButtons();
        initBottomButtons();
    }

    private void updateHeadTitle() {
        TextView headTitle = (TextView) findViewById(R.id.header_title);
        int titleId = R.string.audio_page;
        if (mAdapter.isSearchMode()) {
            titleId = R.string.search;
        } else {
            switch (AudioLoaderManager.getInstance().getViewType()) {
                case TAB_INDEX_LOCAL:
                    titleId = R.string.title_audio_local;
                    break;
                case TAB_INDEX_SDCARD:
                    titleId = R.string.title_audio_sdcard;
                    break;
                case TAB_INDEX_USB:
                    titleId = R.string.title_audio_usb;
                    break;
                default:
                    break;
            }
        }
        headTitle.setText(titleId);
    }

    private void initHeaderButtons() {
        ImageView back = (ImageView) findViewById(R.id.button_header_back);
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mAdapter.isNormalMode()) {
                    setMode(MODE_NORMAL);
                } else {
                    AudioListActivity.this.finish();
                }
            }
        });

        Button edit = (Button) findViewById(R.id.button_header_edit);
        edit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setMode(MODE_EDIT);
            }
        });

        Button search = (Button) findViewById(R.id.button_header_search);
        search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setMode(MODE_SEARCH);
            }
        });

        Button delete = (Button) findViewById(R.id.button_header_delete);
        delete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAdapter.isEditMode()) {
                    FragmentManager fm = AudioListActivity.this.getFragmentManager();
                    List<Song> songs = mAdapter.getSeletedSongs();
                    DeleteDialog.newInstance(AudioListActivity.this, songs).show(fm,
                            "DELETE_DIALOG");
                }
            }
        });

        Button deleteAll = (Button) findViewById(R.id.button_header_delete_all);
        deleteAll.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAdapter.isEditMode()) {
                    FragmentManager fm = AudioListActivity.this.getFragmentManager();
                    List<Song> songs = mAdapter.getAllSongs();
                    DeleteDialog.newInstance(AudioListActivity.this, songs).show(fm,
                            "DELETE_DIALOG");
                }
            }
        });

        mSearchText = (EditText) findViewById(R.id.search_text);

        Button searchByName = (Button) findViewById(R.id.button_search_name);
        searchByName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAdapter.isSearchMode()) {
                    String text = mSearchText.getText().toString().trim();
                    if (!TextUtils.isEmpty(text)) {
                        Message msg = mHandler.obtainMessage(MSG_SHOW_SEARCH_PROGRESS_DIALOG);
                        mHandler.sendMessageDelayed(msg, 200);
                        new AudioSearchTask(AudioListActivity.this, AudioListActivity.this).search(
                                text, AudioLoaderManager.SEARCH_BY_NAME);
                    }
                }
            }
        });

        Button searchBySinger = (Button) findViewById(R.id.button_search_signer);
        searchBySinger.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAdapter.isSearchMode()) {
                    String text = mSearchText.getText().toString();
                    if (!TextUtils.isEmpty(text)) {
                        Message msg = mHandler.obtainMessage(MSG_SHOW_SEARCH_PROGRESS_DIALOG);
                        mHandler.sendMessageDelayed(msg, 200);
                        new AudioSearchTask(AudioListActivity.this, AudioListActivity.this).search(
                                text, AudioLoaderManager.SEARCH_BY_SINGER);
                    }
                }
            }
        });
    }

    private void initBottomButtons() {
        for (int i = 0; i < TAB_IDS.length; i++) {
            findViewById(TAB_IDS[i]).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = TAB_INDEX_LOCAL;
                    for (int i = 0; i < TAB_IDS.length; i++) {
                        if (TAB_IDS[i] == v.getId()) {
                            index = i;
                        }
                    }

                    tabIndex(index);
                }
            });
        }
        tabIndex(TAB_INDEX_LOCAL);
    }

    private void setMode(int mode) {
        switch (mode) {
            case MODE_NORMAL:
                if (!mAdapter.isNormalMode()) {
                    findViewById(R.id.btn_edit_container).setVisibility(View.VISIBLE);
                    findViewById(R.id.button_header_search).setVisibility(View.VISIBLE);
                    findViewById(R.id.button_header_edit).setVisibility(View.VISIBLE);
                    findViewById(R.id.btn_bottom_container).setVisibility(View.VISIBLE);

                    findViewById(R.id.button_header_delete).setVisibility(View.GONE);
                    findViewById(R.id.button_header_delete_all).setVisibility(View.GONE);
                    findViewById(R.id.search_text_container).setVisibility(View.GONE);
                    findViewById(R.id.btn_search_container).setVisibility(View.GONE);
                }
                break;
            case MODE_EDIT:
                if (!mAdapter.isEditMode() && mAdapter.getCount() > 0) {
                    findViewById(R.id.btn_edit_container).setVisibility(View.VISIBLE);
                    findViewById(R.id.button_header_delete).setVisibility(View.VISIBLE);
                    findViewById(R.id.button_header_delete_all).setVisibility(View.VISIBLE);
                    findViewById(R.id.btn_bottom_container).setVisibility(View.VISIBLE);

                    findViewById(R.id.button_header_search).setVisibility(View.GONE);
                    findViewById(R.id.button_header_edit).setVisibility(View.GONE);

                    findViewById(R.id.search_text_container).setVisibility(View.GONE);
                    findViewById(R.id.btn_search_container).setVisibility(View.GONE);
                }
                break;
            case MODE_SEARCH:
                if (!mAdapter.isSearchMode()) {
                    findViewById(R.id.btn_search_container).setVisibility(View.VISIBLE);
                    findViewById(R.id.search_text_container).setVisibility(View.VISIBLE);

                    findViewById(R.id.btn_edit_container).setVisibility(View.GONE);
                    findViewById(R.id.btn_bottom_container).setVisibility(View.GONE);
                    mSearchText.requestFocus();
                }
                break;
        }
        mSearchText.setText(null);
        mAdapter.setMode(mode);
        updateHeadTitle();
    }

    private void registerUSBStateChangedReceiver() {
        mUSBMountReceiver = new USBMountReceiver(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_MEDIA_MOUNTED);
        filter.addAction(Intent.ACTION_MEDIA_CHECKING);
        filter.addAction(Intent.ACTION_MEDIA_EJECT);
        filter.addAction(Intent.ACTION_MEDIA_REMOVED);
        filter.addDataScheme("file");
        registerReceiver(mUSBMountReceiver, filter);
    }

    private void unregisterUSBStateChangedReceiver() {
        unregisterReceiver(mUSBMountReceiver);
    }

    private void registerMediaStoreChangedObserver() {
        getContentResolver().registerContentObserver(
                MediaStore.Video.Media.INTERNAL_CONTENT_URI,
                true,
                new VideoContentObserver(VideoContentObserver.INTERNAL_VIDEO_CONTENT_CHANGED,
                        mMediaStoreChangedHandler));
        getContentResolver().registerContentObserver(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                true,
                new VideoContentObserver(VideoContentObserver.EXTERNAL_VIDEO_CONTENT_CHANGED,
                        mMediaStoreChangedHandler));
    }

    public void tabIndex(int index) {
        switch (index) {
            case TAB_INDEX_LOCAL:
            case TAB_INDEX_SDCARD:
            case TAB_INDEX_USB:
                AudioLoaderManager.getInstance().setViewType(index);
                mAdapter.onDataChange();
                break;
            case TAB_INDEX_AUX:
                Intent intent_aux = new Intent();
                intent_aux.setClass(AudioListActivity.this, AuxAudioPlayActivity.class);
                startActivityForResult(intent_aux, REQCODE_AUX);
                break;
            case TAB_INDEX_MOBILE:
                Intent intent_bt = new Intent();
                intent_bt.setClass(AudioListActivity.this, BTPlayerActivity.class);
                startActivityForResult(intent_bt, REQCODE_BT);
                break;
        }

        for (int i = 0; i < TAB_IDS.length; i++) {
            if (i == index) {
                findViewById(TAB_IDS[i]).setEnabled(false);
                findViewById(TAB_IDS[i]).setBackgroundResource(TAB_SELECTED_BGS[i]);
            } else {
                findViewById(TAB_IDS[i]).setEnabled(true);
                findViewById(TAB_IDS[i]).setBackgroundResource(TAB_NORMAL_BGS[i]);
            }
        }
        updateHeadTitle();
    }

    @Override
    protected void onResume() {
        mAdapter.notifyDataSetInvalidated();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        unregisterUSBStateChangedReceiver();
        stopService(new Intent(this, AudioPlayerService.class));
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (!mAdapter.isNormalMode()) {
            setMode(MODE_NORMAL);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View view, int pos, long arg3) {
        if (mAdapter.isEditMode()) {
            mAdapter.setItemSelected(pos);
            return;
        } else {
            List<Song> songs = AudioLoaderManager.getInstance().getViewSongs();
            AudioPlayerManager.getInstance().setPlayerList(songs);
            AudioPlayerManager.getInstance().setPlayerPosition(pos);

            Intent intent = new Intent(this, AudioPlayerActivity.class);
            intent.putExtra(Constants.MUSIC_SONG_POSITION, pos);
            startActivityForResult(intent, REQUEST_CODE_PLAY);
            AudioLoaderManager.getInstance().setPlayType(
                    AudioLoaderManager.getInstance().getViewType());
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> arg0, View view, int pos, long arg3) {
        if (mAdapter.isNormalMode()) {
            setMode(MODE_EDIT);
        }
        mAdapter.setItemSelected(pos);
        return true;
    }

    public List<Song> getDeletedList() {
        return mAdapter.getSeletedSongs();
    }

    private static class DeleteDialog extends DialogFragment {
        private AudioListActivity mActivity = null;
        private List<Song> mSongs;

        public DeleteDialog(AudioListActivity activity, List<Song> songs) {
            mActivity = activity;
            mSongs = songs;
        }

        public static DialogFragment newInstance(AudioListActivity activity, List<Song> songs) {
            DeleteDialog deleteDialog = new DeleteDialog(activity, songs);
            return deleteDialog;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("DELETE").setMessage("音乐文件将从文件系统彻底删除。请确认是否删除？");

            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mActivity.onDeleteStart();
                    AudioLoaderManager.getInstance().deleteSongs(mSongs, mActivity);
                }
            }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            return builder.create();
        }

    }

    @Override
    public void onDeleteStart() {
        Message msg = mHandler.obtainMessage(MSG_SHOW_DELETE_PROGRESS_DIALOG);
        mHandler.sendMessageDelayed(msg, 300);
    }

    @Override
    public void onDeleteUpdateProgress(int progress, int count) {

    }

    @Override
    public void onDeleteEnd() {
        mHandler.removeMessages(MSG_SHOW_DELETE_PROGRESS_DIALOG);
        mHandler.sendEmptyMessage(MSG_DISMISS_PROGRESS_DIALOG);
    }

    @Override
    public void onDeleteCancelled() {
        mHandler.removeMessages(MSG_SHOW_DELETE_PROGRESS_DIALOG);
        mHandler.sendEmptyMessage(MSG_DISMISS_PROGRESS_DIALOG);
    }

    // Don't show the delete dialog if not spent too much time.
    private final static int MSG_SHOW_DELETE_PROGRESS_DIALOG = 10;
    private final static int MSG_SHOW_SEARCH_PROGRESS_DIALOG = 11;
    private final static int MSG_DISMISS_PROGRESS_DIALOG = 21;
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SHOW_DELETE_PROGRESS_DIALOG:
                    if (mProgressDialog == null) {
                        mProgressDialog = new ProgressDialog(AudioListActivity.this);
                        mProgressDialog.setCancelable(false);
                        mProgressDialog.setTitle("删除歌曲");
                        mProgressDialog.setMessage("正在删除歌曲,请稍候...");
                    }
                    if (!mProgressDialog.isShowing()) {
                        mProgressDialog.show();
                    }
                    break;
                case MSG_SHOW_SEARCH_PROGRESS_DIALOG:
                    if (mProgressDialog == null) {
                        mProgressDialog = new ProgressDialog(AudioListActivity.this);
                        mProgressDialog.setCancelable(false);
                        mProgressDialog.setTitle("搜索歌曲");
                        mProgressDialog.setMessage("正在搜索歌曲,请稍候...");
                    }
                    if (!mProgressDialog.isShowing()) {
                        mProgressDialog.show();
                    }
                    break;
                case MSG_DISMISS_PROGRESS_DIALOG:
                    if (mProgressDialog != null && mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }
                    break;
            }
        }

    };

    @Override
    public void onSearchComplete(List<Song> result) {
        if (result == null || result.isEmpty()) {
            ToastUtils.showToast(this, "未搜索到相关歌曲");
        } else {
            mAdapter.setData(result);
        }
        mHandler.removeMessages(MSG_SHOW_SEARCH_PROGRESS_DIALOG);
        mHandler.sendEmptyMessage(MSG_DISMISS_PROGRESS_DIALOG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQCODE_BT)
        {
            final int tabIndex = AudioLoaderManager.getInstance().getViewType();
            findViewById(TAB_IDS[tabIndex]).setEnabled(false);
            findViewById(TAB_IDS[tabIndex]).setBackgroundResource(TAB_SELECTED_BGS[tabIndex]);
            findViewById(TAB_IDS[4]).setEnabled(true);
            findViewById(TAB_IDS[4]).setBackgroundResource(TAB_NORMAL_BGS[4]);
        } else if (requestCode == REQCODE_AUX)
        {
            final int tabIndex = AudioLoaderManager.getInstance().getViewType();
            findViewById(TAB_IDS[tabIndex]).setEnabled(false);
            findViewById(TAB_IDS[tabIndex]).setBackgroundResource(TAB_SELECTED_BGS[tabIndex]);
            findViewById(TAB_IDS[3]).setEnabled(true);
            findViewById(TAB_IDS[3]).setBackgroundResource(TAB_NORMAL_BGS[3]);
        } else if (requestCode == REQUEST_CODE_PLAY) {
            if (null == mOnSongChangedListener) {
                mOnSongChangedListener = new OnSongChangedListener() {
                    @Override
                    public void onSongChanged(int newPosition) {
                        mAdapter.notifyDataSetChanged();
                    }
                };
            }
            AudioPlayerService.setOnSongChangedListener(mOnSongChangedListener);
        }
    }
}
