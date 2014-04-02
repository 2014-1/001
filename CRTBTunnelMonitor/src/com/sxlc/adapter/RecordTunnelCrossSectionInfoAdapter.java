/**
 * 
 */
package com.sxlc.adapter;

import java.util.List;

import com.sxlc.activity.R;
import com.sxlc.entity.TunnelCrossSectionInfo;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Administrator
 *
 */
public class RecordTunnelCrossSectionInfoAdapter extends BaseAdapter {
	private List<TunnelCrossSectionInfo>listinfos;
	private Context context;
	public RecordTunnelCrossSectionInfoAdapter(Context ct,List<TunnelCrossSectionInfo> lis){
		context=ct;
		listinfos=lis;
	}
	@Override
	public int getCount() {
		return listinfos.size();
	}

	@Override
	public Object getItem(int arg0) {
		return listinfos.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater lay;
		if(convertView==null){
			lay=LayoutInflater.from(context);
			convertView=lay.inflate(R.layout.listinfos_pic, null);
		}
		TextView name=(TextView) convertView.findViewById(R.id.t1);
		ImageView start=(ImageView) convertView.findViewById(R.id.t2);
		name.setTextColor(Color.BLACK);
		//start.setTextColor(Color.BLACK);
		name.setText(listinfos.get(position).getChainageName());
		if (listinfos.get(position).isbUse()) {
			start.setBackgroundResource(R.drawable.use);
		}
		else {
			start.setBackgroundResource(R.drawable.nouse);
		}
		return convertView;
	}
}
