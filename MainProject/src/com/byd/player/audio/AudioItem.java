package com.byd.player.audio;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

public class AudioItem {

    private Song mSong = null;
    private boolean mChecked = false;
    private Bitmap mAblumBitmap = null;

    public AudioItem(Song song) {
        mSong = song;
    }

    public Bitmap getAlbum() {
        String path = mSong.getAlbum();
        if (!TextUtils.isEmpty(path) && mAblumBitmap == null) {
            mAblumBitmap = BitmapFactory.decodeFile(path);
        }
        return mAblumBitmap;
    }

    public String getAudioName() {
        return mSong.getFileTitle();
    }

    public String getSinger() {
        return mSong.getSinger();
    }

    public void setChecked(boolean flag) {
        mChecked = flag;
    }

    public boolean isChecked() {
        return mChecked;
    }
}
