package com.byd.player.audio;

import java.util.ArrayList;
import java.util.List;

import android.content.AsyncQueryHandler;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

public class AudioLoader extends AsyncQueryHandler {

    public final static Uri AUDIO_URI = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    public final static String[] DEF_PROJECTION = new String[] {
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM, 
            MediaStore.Audio.Media.YEAR,
            MediaStore.Audio.Media.MIME_TYPE, 
            MediaStore.Audio.Media.SIZE,
            MediaStore.Audio.Media.DATA };

    public final static String DEF_SELECTION = MediaStore.Audio.Media.MIME_TYPE + "=? or "
            + MediaStore.Audio.Media.MIME_TYPE + "=?";

    public final static String[] DEF_SELECTION_ARGS = new String[] { "audio/mpeg", "audio/x-ms-wma" };

    private AudioManager mAudioManager;

    public AudioLoader(Context context, AudioManager am) {
        super(context.getContentResolver());
        mAudioManager = am;
    }

    @Override
    protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
        super.onQueryComplete(token, cookie, cursor);
        if (cursor == null) {
            return;
        }
        try {
            if (cursor.moveToFirst()) {
                getSongList(cursor);
            }
        } finally {
            cursor.close();
        }
    }

    /**
     * getSongList()
     * 
     * @param cursor
     */
    public void getSongList(Cursor cursor) {
        List<Song> songs = new ArrayList<Song>();
        do {
            Song song = null;
            song = new Song();
            song.setFileName(cursor.getString(1));// file Name
            song.setFileTitle(cursor.getString(2));// song name
            song.setDuration(cursor.getInt(3));// play time
            song.setSinger(cursor.getString(4));// artist
            song.setAlbum(cursor.getString(5));// album
            if (cursor.getString(6) != null) {
                song.setYear(cursor.getString(6));
            } else {
                song.setYear("undefine");
            }
            if ("audio/mpeg".equals(cursor.getString(7).trim())) {// file type
                song.setFileType("mp3");
            } else if ("audio/x-ms-wma".equals(cursor.getString(7).trim())) {
                song.setFileType("wma");
            }
            if (cursor.getString(8) != null) {// fileSize
                float temp = cursor.getInt(8) / 1024f / 1024f;
                String sizeStr = (temp + "").substring(0, 4);
                song.setFileSize(sizeStr + "M");
            } else {
                song.setFileSize("undefine");
            }

            if (cursor.getString(9) != null) {// file path
                song.setFilePath(cursor.getString(9));
            }

        } while (cursor.moveToNext());

        mAudioManager.clearData();
        mAudioManager.add(songs);
        mAudioManager.notifyDataChange();
    }
}
