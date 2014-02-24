package com.byd.player.audio;

<<<<<<< HEAD
import android.app.ActionBar;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.byd.player.R;
=======
import android.os.Bundle;
import android.view.WindowManager;
>>>>>>> 95e037151d539df2d4d3bfeac3157cff7c9bf39e

import com.byd.player.BaseActivity;
import com.byd.player.R;

public class AudioPlayerActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.audio_player_view);
    }
}
