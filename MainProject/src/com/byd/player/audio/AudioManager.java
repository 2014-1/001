package com.byd.player.audio;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.provider.MediaStore;

public class AudioManager {
    private static AudioManager sInstance = new AudioManager();

    public interface DataListener {
        void onDataChange();
    }

    private Context mContext = null;
    private List<Song> mSongsList = new ArrayList<Song>();

    private AudioManager() {
    }

    public void init(Context context) {
        mContext = context;
    }

    public static AudioManager getInstance() {
        return sInstance;
    }

    public void load() {
        new AudioLoader(mContext, sInstance).startQuery(0, (Object) null,
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, AudioLoader.DEF_PROJECTION,
                AudioLoader.DEF_SELECTION, AudioLoader.DEF_SELECTION_ARGS, null);
    }

    public void clearData() {
        mSongsList.clear();
    }

    public void add(Song song) {
        mSongsList.add(song);
    }

    public void add(List<Song> songs) {
        mSongsList.addAll(songs);
    }

    public List<Song> getSongs() {
        return mSongsList;
    }

    public void notifyDataChange() {

    }
}
