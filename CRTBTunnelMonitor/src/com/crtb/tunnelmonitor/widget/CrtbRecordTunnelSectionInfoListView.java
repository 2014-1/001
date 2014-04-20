package com.crtb.tunnelmonitor.widget;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;

import com.crtb.tunnelmonitor.dao.impl.v2.TunnelCrossSectionIndexDao;
import com.crtb.tunnelmonitor.entity.TunnelCrossSectionIndex;

/**
 * 
 * @author zhouwei
 *
 */
public class CrtbRecordTunnelSectionInfoListView extends CrtbBaseListView {
	
	private CrtbRecordTunnelSectionInfoAdapter mAdapter ;
	private List<String> sectionIds = new ArrayList<String>() ;
	
	public CrtbRecordTunnelSectionInfoListView(Context context) {
		this(context, null);
	}

	public CrtbRecordTunnelSectionInfoListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		mAdapter	= new CrtbRecordTunnelSectionInfoAdapter(context);
		setAdapter(mAdapter);
		
		clearCacheColor() ;
		
		setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				mAdapter.changeStatus(position);
			}
		}) ;
	}
	
	public TunnelCrossSectionIndex getItem(int position){
		return mAdapter.getItem(position);
	}
	
	public String getSelectedSection(){
		return mAdapter.getSelectedSection();
	}
	
	public void setSectionIds(String section){
		
		if(section != null){
			
			String[] array = section.split(",");
			
			for(String id: array){
				sectionIds.add(id);
			}
		}
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
		
		List<TunnelCrossSectionIndex> list = TunnelCrossSectionIndexDao.defaultDao().queryAllSection() ;
		
		if(list != null && sectionIds != null){
			
			for(TunnelCrossSectionIndex item : list){
				
				for(String id : sectionIds){
					if(id.equals(String.valueOf(item.getID()))){
						item.setUsed(true);
						break ;
					}
				}
			}
			
		}
		
		mAdapter.loadEntityDatas(list);
	}
	
}
