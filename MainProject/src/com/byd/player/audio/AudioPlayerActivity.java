package com.byd.player.audio;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.byd.player.BaseActivity;
import com.byd.player.R;
import com.byd.player.audio.AudioPlayerService.OnPlayPauseListener;
import com.byd.player.audio.AudioPlayerService.OnSongChangedListener;
import com.byd.player.audio.AudioPlayerService.OnUpdateListener;
import com.byd.player.audio.AudioPlayerService.PlayerBinder;
import com.byd.player.config.Constants;
import com.byd.player.view.CheckableImageView;
import com.byd.player.view.VerticalSeekBar;

public class AudioPlayerActivity extends BaseActivity {
    private Song mPlayingSong;

    private int mSongPosition;

    /**
     * The container of song info or lyrics
     */
    private LinearLayout mSongInfoAndLyricsContainer;

    private TextView mTotalTime;

    private TextView mPlayingTime;

    private SeekBar mProgressBar;

    private TextView mAlbumName;

    private TextView mSingerName;

    private TextView mMusicName;

    private CheckableImageView mBtnPlayPause;

    private ImageView mBtnNext;

    private ImageView mBtnPrevious;

    private CheckableImageView mBtnRandomPlay;

    private CheckableImageView mBtnSingleLoop;

    private CheckableImageView mBtnVolume;

    private PopupWindow mPopupVolume;

    private VerticalSeekBar mVolumeSeekbar;

    private LayoutInflater mInflater;

    private PlayerReceiver mPlayerReceiver;

    private Intent mAudioServiceIntent;

    private AudioServiceConn mConn;

    private android.media.AudioManager mAudioMgr;

    private AudioPlayerService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.audio_player_view);

        mInflater = this.getLayoutInflater();

        mAudioServiceIntent = new Intent(this, AudioPlayerService.class);

        mAudioMgr = (android.media.AudioManager)getSystemService(Context.AUDIO_SERVICE);

        init(getIntent().getIntExtra(Constants.MUSIC_SONG_POSITION, -1));
        startPlay();

        registerBroadcast();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBtnRandomPlay.setChecked(Constants.getRandomPlayStatus(this));
        mBtnSingleLoop.setChecked(Constants.getSingleLoopStatus(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        mConn = new AudioServiceConn();
        bindService(mAudioServiceIntent, mConn, 0);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(mConn);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterBroadcast();
        if (mService != null && !mService.isPlaying()) {
            stopService(mAudioServiceIntent);
        }
    }

    private void init(int songPosition) {
        initSong(songPosition);
        initViews();
    }

    private void initSong(int songPosition) {
        mSongPosition = songPosition;
        mPlayingSong = AudioManager.getInstance().getSongAtPosition(mSongPosition);
    }

    private void initViews() {
        if (null == mSongInfoAndLyricsContainer) {
            mSongInfoAndLyricsContainer = (LinearLayout)findViewById(R.id.ll_song_info_and_lyrics);
        }
        mSongInfoAndLyricsContainer.removeAllViews();
        if (hasLyrics()) {
            // TODO: Add lyrics display
        } else {
            mSongInfoAndLyricsContainer.addView(mInflater.inflate(
                    R.layout.layout_song_info, null));
            mAlbumName = (TextView)findViewById(R.id.album_name);
            mSingerName = (TextView)findViewById(R.id.singer_name);
            mMusicName = (TextView)findViewById(R.id.music_name);
            mAlbumName.setText(mPlayingSong.getAlbum());
            mSingerName.setText(mPlayingSong.getSinger());
            mMusicName.setText(mPlayingSong.getFileTitle());
        }

        if (null == mTotalTime) {
            mTotalTime = (TextView)findViewById(R.id.audio_total_time);
        }
        if (null == mPlayingTime) {
            mPlayingTime = (TextView)findViewById(R.id.audio_playing_time);
        }
        if (null == mProgressBar) {
            mProgressBar = (SeekBar)findViewById(R.id.audio_seekbar);
            mProgressBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    seekTo(seekBar.getProgress());
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    // TODO Auto-generated method stub
                }
            });
        }

        if (null == mBtnPlayPause) {
            mBtnPlayPause = (CheckableImageView)findViewById(R.id.btn_audio_play_pause);
            mBtnPlayPause.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mBtnPlayPause.isChecked()) {
                        continuePlay();
                    } else {
                        pause();
                    }
                }
            });
        }

        if (null == mBtnNext) {
            mBtnNext = (ImageView)findViewById(R.id.btn_audio_next);
            mBtnNext.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    playNext();
                }
            });
        }

        if (null == mBtnPrevious) {
            mBtnPrevious = (ImageView)findViewById(R.id.btn_audio_previous);
            mBtnPrevious.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    playPrevious();
                }
            });
        }

        if (null == mBtnRandomPlay) {
            mBtnRandomPlay = (CheckableImageView)findViewById(R.id.btn_audio_random_play);
            mBtnRandomPlay.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isChecked = mBtnRandomPlay.isChecked();
                    Constants.setRandomPlayStatus(getApplicationContext(), !isChecked);
                    mBtnRandomPlay.setChecked(!isChecked);
                }
            });
        }

        if (null == mBtnSingleLoop) {
            mBtnSingleLoop = (CheckableImageView)findViewById(R.id.btn_audio_single_loop);
            mBtnSingleLoop.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isChecked = mBtnSingleLoop.isChecked();
                    Constants.setSingleLoopStatus(getApplicationContext(), !isChecked);
                    mBtnSingleLoop.setChecked(!isChecked);
                }
            });
        }

        if (null == mPopupVolume) {
            View volume_view = mInflater.inflate(R.layout.layout_audio_volume, null);
            mPopupVolume = new PopupWindow(volume_view, LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            mVolumeSeekbar = (VerticalSeekBar)volume_view.findViewById(R.id.audio_volume_seekbar);
            mVolumeSeekbar
            .setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress,
                        boolean fromUser) {
                    mAudioMgr.setStreamVolume(android.media.AudioManager.STREAM_MUSIC, progress,
                            android.media.AudioManager.FLAG_PLAY_SOUND);
                }
            });
        }

        if (null == mBtnVolume) {
            mBtnVolume = (CheckableImageView)findViewById(R.id.btn_audio_volume);
            mBtnVolume.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isChecked = mBtnVolume.isChecked();
                    if (!isChecked) {
                        mVolumeSeekbar.setProgress(mAudioMgr
                                .getStreamVolume(android.media.AudioManager.STREAM_MUSIC));
                        mVolumeSeekbar.setMax(mAudioMgr
                                .getStreamMaxVolume(android.media.AudioManager.STREAM_MUSIC));
                        mPopupVolume.showAtLocation(mSongInfoAndLyricsContainer, Gravity.RIGHT,
                                -100, 0);
                        mPopupVolume.update();
                    } else if (mPopupVolume.isShowing()) {
                        mPopupVolume.dismiss();
                    }
                    mBtnVolume.setChecked(!isChecked);
                }
            });
        }
    }

    private boolean hasLyrics() {
        // TODO: Add lyrics check
        return false;
    }

    private void startPlay() {
        mAudioServiceIntent.putExtra(Constants.PLAYER_MSG, Constants.PlayerCommand.PLAY);
        mAudioServiceIntent.putExtra(Constants.MUSIC_SONG_POSITION, mSongPosition);
        startService(mAudioServiceIntent);
    }

    private void stopPlay() {
        mAudioServiceIntent.putExtra(Constants.PLAYER_MSG, Constants.PlayerCommand.STOP);
        startService(mAudioServiceIntent);
    }

    private void seekTo(int progress) {
        mAudioServiceIntent.putExtra(Constants.PLAYER_MSG, Constants.PlayerCommand.SEEK);
        mAudioServiceIntent.putExtra(Constants.MUSIC_SEEK_TO, progress);
        startService(mAudioServiceIntent);
    }

    private void pause() {
        mAudioServiceIntent.putExtra(Constants.PLAYER_MSG, Constants.PlayerCommand.PAUSE);
        startService(mAudioServiceIntent);
    }

    private void continuePlay() {
        mAudioServiceIntent.putExtra(Constants.PLAYER_MSG, Constants.PlayerCommand.CONTINUE_PLAY);
        startService(mAudioServiceIntent);
    }

    private void playNext() {
        mAudioServiceIntent.putExtra(Constants.PLAYER_MSG, Constants.PlayerCommand.NEXT);
        startService(mAudioServiceIntent);
    }

    private void playPrevious() {
        mAudioServiceIntent.putExtra(Constants.PLAYER_MSG, Constants.PlayerCommand.PREVIOUS);
        startService(mAudioServiceIntent);
    }

    private void registerBroadcast() {
        mPlayerReceiver = new PlayerReceiver();
        IntentFilter filter = new IntentFilter();
        //        registerReceiver(mPlayerReceiver, filter);
    }

    private void unregisterBroadcast() {
        //        unregisterReceiver(mPlayerReceiver);
    }

    public class PlayerReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // if (action.equals(MUSIC_CURRENT)) {
            // currentTime = intent.getIntExtra("currentTime", -1);
            // currentProgress.setText(MediaUtil.formatTime(currentTime));
            // music_progressBar.setProgress(currentTime);
            // } else if (action.equals(MUSIC_DURATION)) {
            // int duration = intent.getIntExtra("duration", -1);
            // music_progressBar.setMax(duration);
            // finalProgress.setText(MediaUtil.formatTime(duration));
            // } else if (action.equals(UPDATE_ACTION)) {
            // // 获取Intent中的current消息，current代表当前正在播放的歌曲
            // listPosition = intent.getIntExtra("current", -1);
            // url = mp3Infos.get(listPosition).getUrl();
            // if (listPosition >= 0) {
            // musicTitle.setText(mp3Infos.get(listPosition).getTitle());
            // musicArtist.setText(mp3Infos.get(listPosition).getArtist());
            // }
            // if (listPosition == 0) {
            // finalProgress.setText(MediaUtil.formatTime(mp3Infos.get(
            // listPosition).getDuration()));
            // playBtn.setBackgroundResource(R.drawable.pause_selector);
            // isPause = true;
            // }
            // }
        }
    }

    private void updateAudioDuration(int duration) {
        mProgressBar.setMax(duration);
        mTotalTime.setText(progresstime(duration));
    }

    private void updateAudioCurrent(int position) {
        mProgressBar.setProgress(position);
        mPlayingTime.setText(progresstime(position));
    }

    private void updatePlayPauseBtn(boolean isPlay) {
        // Check the button if the audio is not playing.
        mBtnPlayPause.setChecked(!isPlay);
    }

    private void initPlayTime(int position, int duration) {
        updateAudioDuration(duration);
        updateAudioCurrent(position);
    }

    private class AudioServiceConn implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            PlayerBinder binder = (PlayerBinder)service;
            mService = binder.getService();
            initPlayTime(mService.getAudioCurrent(),mService.getAudioDuration());
            updatePlayPauseBtn(mService.isPlaying());
            mService.setOnUpdateListener(new OnUpdateListener() {
                @Override
                public void onUpdate(int current) {
                    updateAudioCurrent(current);
                    // Make sure the status of PlayPause button is right
                    if (mService.isPlaying() == mBtnPlayPause.isChecked()) {
                        mBtnPlayPause.setChecked(!mService.isPlaying());
                    }
                }
            });
            mService.setOnPlayPauseListener(new OnPlayPauseListener() {
                @Override
                public void onPlayPause(boolean isPlay) {
                    updatePlayPauseBtn(isPlay);
                }
            });
            mService.setOnSongChangedListener(new OnSongChangedListener() {
                @Override
                public void onSongChanged(int newPosition) {
                    init(newPosition);
                    initPlayTime(mService.getAudioCurrent(),mService.getAudioDuration());
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub

        }
    }

    private String progresstime(int progress) {
        Date date = new Date(progress);
        SimpleDateFormat format = new SimpleDateFormat("mm:ss");
        return format.format(date);
    }
}
