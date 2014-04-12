/**
 * 
 */
package com.crtb.tunnelmonitor.activity;

import java.util.ArrayList;
import java.util.List;

import org.zw.android.framework.ioc.InjectCore;
import org.zw.android.framework.ioc.InjectLayout;
import org.zw.android.framework.ioc.InjectView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.crtb.tunnelmonitor.AppCRTBApplication;
import com.crtb.tunnelmonitor.WorkFlowActivity;
import com.crtb.tunnelmonitor.adapter.TestRecordAdapter;
import com.crtb.tunnelmonitor.common.Constant;
import com.crtb.tunnelmonitor.dao.impl.RecordDaoImpl;
import com.crtb.tunnelmonitor.entity.RecordInfo;
import com.crtb.tunnelmonitor.entity.WorkInfos;
import com.crtb.tunnelmonitor.utils.SelectPicPopupWindow;

/**
 * 测量模块
 * 
 * @author zhouwei
 *
 */
@InjectLayout(layout=R.layout.activity_testrecord)
public class TestRecordActivity extends WorkFlowActivity implements OnPageChangeListener{
	
	private View vie;
	
	private ListView listView,listView1;
	
	ArrayList<View> list = new ArrayList<View>();
	
	@InjectView(id=R.id.vPager)
	private ViewPager mPager;
	
	@InjectView(id=R.id.cursor)
	private ImageView cursor;
	
	private TextView t1, t2;
	private int offset = 0;
	private int currIndex = 0;
	private int bmpW;
	int disPlayWidth, offSet;
	Bitmap b;
	
	LinearLayout xin;
	private LinearLayout record_bianji;
	/***/
	private ListView record_lv_suidao;
	private ListView record_lv_dibiao;
	
	@InjectView(layout=R.layout.testrecord_listdibiao)
	private LinearLayout mSectionRecordLayout ;
	
	@InjectView(layout=R.layout.testrecord_listdibiao)
	private LinearLayout mSubsidenceRecordLayout ;
	
	private List<RecordInfo> infos = null,infos1 = null;
	
	private TestRecordAdapter adapter = null,adapter1 = null;
	private int iListPos1 = -1,iListPos2 = -1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// add by wei.zhou
		InjectCore.injectUIProperty(this);

		// title
		setTopbarTitle(getString(R.string.test_record_title));
		
		// init ViewPager
		initPager() ;
		
//		initUI();
//		InitImageView();
//		initPager();
	}

	public void setdata() {
		AppCRTBApplication CurApp = ((AppCRTBApplication)getApplicationContext());
		WorkInfos CurW = CurApp.GetCurWork();
		if(CurW == null)
		{
			return;
		}
		infos = CurW.getTcsirecordList();
		boolean bLoadDB = true;
		if(infos!=null)
		{
			if(infos.size()>0)
			{
				bLoadDB = false;
			}
		}
		if(bLoadDB)
		{
			if(infos == null)
			{
				infos = new ArrayList<RecordInfo>();
			}
			RecordDaoImpl impl = new RecordDaoImpl(this, CurW.getProjectName());
			impl.GetRecordList(1,CurW, infos);
			CurW.setTcsirecordList(infos);
			CurApp.UpdateWork(CurW);
		}
		
		infos1 = CurW.getScsirecordList();
		bLoadDB = true;
		if(infos1!=null)
		{
			if(infos1.size()>0)
			{
				bLoadDB = false;
			}
		}
		if(bLoadDB)
		{
			if(infos1 == null)
			{
				infos1 = new ArrayList<RecordInfo>();
			}
			RecordDaoImpl impl = new RecordDaoImpl(this, CurW.getProjectName());
			impl.GetRecordList(2,CurW, infos1);
			CurW.setScsirecordList(infos1);
			CurApp.UpdateWork(CurW);
		}
	}
	// 初始化
	public void initUI() {
		
//		xin = (LinearLayout) findViewById(R.id.xin);
//		record_bianji = (LinearLayout) findViewById(R.id.record_bianji);
		
//		mPager = (ViewPager) findViewById(R.id.vPager);
		
	}

	public void InitImageView() {
		
		
//		setdata();
		
		
	}

	public void initPager() {
		
		t1 = (TextView) findViewById(R.id.text1);
		t2 = (TextView) findViewById(R.id.text2);
		
		t1.setOnClickListener(tv_Listener);
		t2.setOnClickListener(tv_Listener);
		
		list.add(mSectionRecordLayout);
		list.add(mSubsidenceRecordLayout);
		
		disPlayWidth = mDisplayMetrics.widthPixels ;
		b = BitmapFactory.decodeResource(this.getResources(), R.drawable.heng);
		offSet = ((disPlayWidth / 4) - b.getWidth() / 2);
		
		ViewGroup.LayoutParams lp = cursor.getLayoutParams() ;
		lp.width = disPlayWidth >> 1 ;
		lp.height = 4 ;
		cursor.setLayoutParams(lp);
		
		PagerAdapter pa = new PagerAdapter() {

			@Override
			public void destroyItem(View arg0, int arg1, Object arg2) {
				((ViewPager) arg0).removeView((View) list.get(arg1));

			}

			@Override
			public void finishUpdate(View arg0) {

			}

			@Override
			public int getCount() {
				return list.size();
			}

			@Override
			public Object instantiateItem(View arg0, int arg1) {
				((ViewPager) arg0).addView((View) list.get(arg1), 0);
				

				return (View) list.get(arg1);
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public void restoreState(Parcelable arg0, ClassLoader arg1) {

			}

			@Override
			public Parcelable saveState() {
				return null;
			}

			@Override
			public void startUpdate(View arg0) {

			}
		};
		
		mPager.setAdapter(pa);
		mPager.setCurrentItem(TAB_ONE);
		mPager.setOnPageChangeListener(this);
		
//		/** 隧道内断面 */
//		Layout1();
//		/** 地表下沉断面 */
//		Layout2();
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int arg0) {
		int single = (int) (b.getWidth() + offSet * 2);
		TranslateAnimation ta = new TranslateAnimation(currIndex * single,
				single * arg0, 0, 0);
		ta.setFillAfter(true);
		ta.setDuration(200);
		cursor.startAnimation(ta);
		currIndex = arg0;
	}

	public OnClickListener tv_Listener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			int single = (int) (b.getWidth() + offSet * 2);
			switch (v.getId()) {
			case R.id.text1:
				mPager.setCurrentItem(0);
				if (currIndex != 0) {
					TranslateAnimation ta = new TranslateAnimation(
							(currIndex * single), 0, 0, 0);
					ta.setFillAfter(true);
					ta.setDuration(200);
					cursor.startAnimation(ta);
				}
				currIndex = 0;
				break;
			case R.id.text2:
				mPager.setCurrentItem(1);
				if (currIndex != 1) {
					TranslateAnimation ta = new TranslateAnimation(currIndex
							* single, single, 0, 0);
					ta.setFillAfter(true);
					ta.setDuration(200);
					cursor.startAnimation(ta);
				}
				currIndex = 1;
				break;
			default:
				break;
			}
		}
	};

	public void Layout1() {
		/** 隧道内断面界面的控件 */
		/** List集合中存儲的是View,获取界面上的控件,就List.get(0),0就是集合中第一个界面,1就是集合中第二个界面 */
		listView = (ListView) list.get(0).findViewById(R.id.record_lv_dibiao);

		adapter = new TestRecordAdapter(TestRecordActivity.this, infos);
		listView.setAdapter(adapter);
		// listview的行点击
//		listView.setOnItemLongClickListener(new OnItemLongClickListener() {
//
//			@Override
//			public boolean onItemLongClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				iListPos1 = position;
//				// 对话框的选项
//				//CharSequence items[] = { "打开", "编辑", "导出", "删除" };
//				// 实例化对话
//				new AlertDialog.Builder(TestRecordActivity.this)
//						.setItems(/*items*/Constant.RecordRowClickItems, new DialogInterface.OnClickListener() {
//
//							@Override
//							public void onClick(DialogInterface dialog,
//									int which) {
//								CRTBTunnelMonitor CurApp = ((CRTBTunnelMonitor)getApplicationContext());
//								RecordInfo item = (RecordInfo)listView.getItemAtPosition(iListPos1);
//								switch (which) {
//								case 0:// 编辑
//									Intent intent = new Intent(TestRecordActivity.this,
//											RecordNewActivity.class);
//									Bundle mBundle = new Bundle();  
//									mBundle.putInt(Constant.Select_RecordRowClickItemsName_Name, 2);
//							        mBundle.putParcelable(Constant.Select_RecordRowClickItemsName_Data, item);
//							        intent.putExtras(mBundle);
//							        TestRecordActivity.this.startActivityForResult(intent,0);
//									//startActivity(intent);
//									break;
//								case 1:// 删除
////									WorkInfos Curw = CurApp.GetCurWork();
////									TunnelCrossSectionDaoImpl impl = new TunnelCrossSectionDaoImpl(RecordActivity.this,Curw.getProjectName());
////									int iRet = impl.DeleteSection(item.getId());
////									switch (iRet) {
////									case 0:
////										Toast.makeText(RecordActivity.this, "删除失败", 3000).show();
////										break;
////									case 1:
////										Curw.DelTunnelCrossSectionInfo(item);
////										CurApp.UpdateWork(Curw);
////										adapter.notifyDataSetChanged();
////										Toast.makeText(RecordActivity.this, "删除成功", 3000).show();
////										break;
////									case -1:
////										Toast.makeText(RecordActivity.this, "删除的断面中存在数据,不可删除", 3000).show();
////										break;
////									default:
////										break;
////									}
//									break;
//								default:
//									break;
//								}
//
//							}
//						})
//						.setCancelable(false)
//						.show().setCanceledOnTouchOutside(true);// 显示对话框
//				return true;
//			}
//		});

	}

	public void Layout2() {
		/** 隧道内断面界面的控件 */
		/** List集合中存儲的是View,获取界面上的控件,就List.get(0),0就是集合中第一个界面,1就是集合中第二个界面 */
		listView1 = (ListView) list.get(1).findViewById(R.id.record_lv_dibiao);

		adapter1 = new TestRecordAdapter(TestRecordActivity.this, infos1);
		listView1.setAdapter(adapter1);
//		// listview的行点击
//		listView1.setOnItemLongClickListener(new OnItemLongClickListener() {
//
//			@Override
//			public boolean onItemLongClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				iListPos2 = position;
//				new AlertDialog.Builder(TestRecordActivity.this)
//						.setItems(/*items*/Constant.RecordRowClickItems, new DialogInterface.OnClickListener() {
//
//							@Override
//							public void onClick(DialogInterface dialog,
//									int which) {
//								CRTBTunnelMonitor CurApp = ((CRTBTunnelMonitor)getApplicationContext());
//								RecordInfo item = (RecordInfo)listView1.getItemAtPosition(iListPos2);
//								switch (which) {
//								case 0:// 编辑
//									Intent intent = new Intent(TestRecordActivity.this,
//											RecordNewActivity.class);
//									Bundle mBundle = new Bundle();  
//									mBundle.putInt(Constant.Select_RecordRowClickItemsName_Name, 4);
//							        mBundle.putParcelable(Constant.Select_RecordRowClickItemsName_Data, item);
//							        intent.putExtras(mBundle);
//									TestRecordActivity.this.startActivityForResult(intent,0);
//									//startActivity(intent);
//									break;
//								case 1:// 删除
////									WorkInfos Curw = CurApp.GetCurWork();
////									SubsidenceCrossSectionDaoImpl impl = new SubsidenceCrossSectionDaoImpl(TestRecordActivity.this,Curw.getProjectName());
////									int iRet = impl.DeleteSubsidenceCrossSection(item.getId());
////									switch (iRet) {
////									case 0:
////										Toast.makeText(TestRecordActivity.this, "删除失败", 3000).show();
////										break;
////									case 1:
////										Curw.DelSubsidenceCrossSectionInfo(item);
////										CurApp.UpdateWork(Curw);
////										adapter1.notifyDataSetChanged();
////										Toast.makeText(TestRecordActivity.this, "删除成功", 3000).show();
////										break;
////									case -1:
////										Toast.makeText(TestRecordActivity.this, "删除的断面中存在数据,不可删除", 3000).show();
////										break;
////									default:
////										break;
////									}
//									break;
//								default:
//									break;
//								}
//
//							}
//						})
//						.setCancelable(false)
//						.show().setCanceledOnTouchOutside(true);// 显示对话框
//				return true;
//			}
//		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		switch (resultCode) {
		case RESULT_OK:
		{
			int iSel = data.getExtras().getInt(Constant.Select_RecordRowClickItemsName_Name);
			switch (iSel) {
			case 1:
				adapter.notifyDataSetChanged();
				break;
			case 2:
				adapter1.notifyDataSetChanged();
				break;

			default:
				break;
			}
		}
			break;

		default:
			break;
		}
	}
}
