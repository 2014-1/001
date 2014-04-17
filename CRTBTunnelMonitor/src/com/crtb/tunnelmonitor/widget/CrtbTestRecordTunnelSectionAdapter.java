package com.crtb.tunnelmonitor.widget;

import org.zw.android.framework.ioc.InjectCore;
import org.zw.android.framework.ioc.InjectLayout;
import org.zw.android.framework.ioc.InjectView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crtb.tunnelmonitor.activity.R;
import com.crtb.tunnelmonitor.entity.TunnelSettlementTotalData;

/**
 * 隧道内断面测量单
 * 
 * @author zhouwei
 *
 */
public class CrtbTestRecordTunnelSectionAdapter extends CrtbEntityAdapter<TunnelSettlementTotalData> {

	protected CrtbTestRecordTunnelSectionAdapter(Context context) {
		super(context);
	}
	
	protected void changeStatus(int position){
		
		for(int index = 0 ,size = mList.size() ; index < size ; index++){
			
			TunnelSettlementTotalData item = mList.get(index) ;
			
			if(index == position){
				item.setChecked(!item.isChecked());
			}
		}
		
		notifyDataSetChanged() ;
	}
	
	protected TunnelSettlementTotalData getSelectedSection(){
		
		for(TunnelSettlementTotalData item : mList){
			
			if(item.isChecked()){
				return item ;
			}
		}
		
		return null ;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		HolderView holder 	= null ;
		TunnelSettlementTotalData item 	= getItem(position);
		
		if(convertView == null){
			holder		= new HolderView() ;
			convertView	= InjectCore.injectOriginalObject(holder);
			convertView.setTag(holder);
		} else {
			holder		= (HolderView)convertView.getTag() ;
		}
		
		holder.recordNo.setText(String.valueOf(item.getID()));
		holder.recordName.setText(item.getPrefix());
		
		if(!item.isChecked()){
			holder.status.setBackgroundResource(R.drawable.no);
		} else {
			holder.status.setBackgroundResource(R.drawable.yes);
		}
		
		return convertView;
	}

	@InjectLayout(layout=R.layout.item_record_section_info_layout)
	class HolderView {
		
		@InjectView(id=R.id.t1)
		TextView recordNo ;
		
		@InjectView(id=R.id.t2)
		TextView recordName ;
		
		@InjectView(id=R.id.t3)
		TextView status ;
	}
}
