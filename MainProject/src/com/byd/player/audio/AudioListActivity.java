package com.byd.player.audio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.byd.player.BaseActivity;
import com.byd.player.R;
import com.byd.player.bluetooth.BlueToothPlayActivity;

public class AudioListActivity extends BaseActivity implements OnItemClickListener {
    private GridView mAudioList = null;
    private TextView mPhoneMusic = null;
    private AudioAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        AudioManager.getInstance().init(getApplicationContext());
        AudioManager.getInstance().load();

        setContentView(R.layout.audio_list_view);

        mPhoneMusic = (TextView) findViewById(R.id.btn_audio_mobile);
        mPhoneMusic.setClickable(true);
        mPhoneMusic.setFocusable(true);
        mPhoneMusic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(AudioListActivity.this, BlueToothPlayActivity.class);
                startActivity(intent);
            }
        });

        initHeaderButtons();

        mAudioList = (GridView) findViewById(R.id.audio_grid_list);
        mAdapter = new AudioAdapter(this, getLayoutInflater());
        mAudioList.setAdapter(mAdapter);
        mAudioList.setOnItemClickListener(this);
    }

    private void initHeaderButtons() {
        Button edit = (Button) findViewById(R.id.button_header_edit);
        edit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAdapter.getCount() > 0) {
                    mAdapter.setMode(AudioAdapter.MODE_EDIT);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onBackPressed() {
        if (!mAdapter.isNormalMode()) {
            mAdapter.setMode(AudioAdapter.MODE_NORMAL);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View view, int pos, long arg3) {
        Intent intent = new Intent(this, AudioPlayerActivity.class);
        startActivity(intent);
    }

}
