package com.crtb.tunnelmonitor.widget;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;

import com.crtb.tunnelmonitor.dao.impl.v2.RawSheetIndexDao;
import com.crtb.tunnelmonitor.entity.RawSheetIndex;

/**
 * 隧道内断面测量单
 * 
 * @author zhouwei
 *
 */
public final class CrtbTestRecordTunnelSectionListView extends CrtbBaseListView {
	
	private CrtbTestRecordTunnelSectionAdapter mAdapter ;
	
	public CrtbTestRecordTunnelSectionListView(Context context) {
		this(context, null);
	}

	public CrtbTestRecordTunnelSectionListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		mAdapter	= new CrtbTestRecordTunnelSectionAdapter(context);
		setAdapter(mAdapter);
		
		clearCacheColor() ;
		
		setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mAdapter.changeStatus(position);
			}
		}) ;
	}
	
	public RawSheetIndex getItem(int position){
		return mAdapter.getItem(position);
	}
	
	public List<RawSheetIndex> getSelectedSection(){
		return mAdapter.getSelectedSection();
	}
	
	@Override
	public void onResume() {
		
		if(mAdapter.isEmpty()){
			onReload();
		} else {
			mAdapter.notifyDataSetChanged() ;
		}
	}

	@Override
	public void onReload() {
		
		List<RawSheetIndex> list = RawSheetIndexDao.defaultDao().queryTunnelSectionRawSheetIndex();
		mAdapter.loadEntityDatas(list);
	}
	
}
