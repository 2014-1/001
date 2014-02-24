package com.byd.player.audio;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.byd.player.R;
import com.byd.player.audio.AudioManager.DataListener;

public class AudioAdapter extends BaseAdapter implements DataListener {
    private List<AudioItem> mData = new ArrayList<AudioItem>();
    private Context mContext = null;
    private LayoutInflater mInflater;

    public AudioAdapter(Context context, LayoutInflater inflater) {
        mContext = context;
        mInflater = inflater;
        setData(AudioManager.getInstance().getSongs());
    }

    public void setData(List<Song> data) {
        mData.clear();

        for (Song song : data) {
            mData.add(new AudioItem(song));
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public AudioItem getItem(int pos) {
        return mData.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.audio_item, null);

            viewHolder = new ViewHolder();
            viewHolder.mIamgeAlbum = (ImageView) convertView.findViewById(R.id.audio_album);
            viewHolder.mTextAudioName = (TextView) convertView.findViewById(R.id.audio_name);
            viewHolder.mTextAudioSinger = (TextView) convertView.findViewById(R.id.audio_singer);
            // construct an item tag
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        AudioItem item = getItem(pos);
        viewHolder.mIamgeAlbum.setImageBitmap(item.getAlbum());
        viewHolder.mTextAudioName.setText(item.getAudioName());
        viewHolder.mTextAudioSinger.setText(item.getSinger());
        return convertView;
    }

    private final class ViewHolder {
        ImageView mIamgeAlbum;
        TextView mTextAudioName;
        TextView mTextAudioSinger;
    }

    @Override
    public void onDataChange() {
        setData(AudioManager.getInstance().getSongs());
    }
}
