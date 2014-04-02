/**
 * 
 */
package com.sxlc.activity;

import java.util.ArrayList;
import java.util.List;

import com.sxlc.adapter.RecordAdapter;
import com.sxlc.adapter.TestRecordAdapter;
import com.sxlc.common.Constant;
import com.sxlc.dao.impl.RecordDaoImpl;
import com.sxlc.entity.RecordInfo;
import com.sxlc.entity.WorkInfos;
import com.sxlc.utils.SelectPicPopupWindow;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemLongClickListener;

/**
 * @author Administrator
 *
 */
public class TestRecordActivity extends Activity implements OnPageChangeListener{
	private OnClickListener itemsOnClick;
	private SelectPicPopupWindow menuWindow;
	private View vie;
	
	private ListView listView,listView1;
	private ViewPager mPager;// ҳ������
	private List<View> listViews; // Tabҳ���б�
	private ImageView cursor;// ����ͼƬ
	private TextView t1, t2;// ҳ��ͷ��
	private int offset = 0;// ����ͼƬƫ����
	private int currIndex = 0;// ��ǰҳ�����
	private int bmpW;// ����ͼƬ���
	double disPlayWidth, offSet;
	Bitmap b;
	ArrayList<View> list = null;
	LinearLayout xin;
	private LinearLayout record_bianji;
	/***/
	private ListView record_lv_suidao;
	private ListView record_lv_dibiao;
	
	private List<RecordInfo> infos = null,infos1 = null;
	
	private TestRecordAdapter adapter = null,adapter1 = null;
	private int iListPos1 = -1,iListPos2 = -1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_testrecord);
		initUI();
		InitImageView();
		initPager();
	}

	public void setdata() {
		CRTBTunnelMonitor CurApp = ((CRTBTunnelMonitor)getApplicationContext());
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
	// ��ʼ��
	public void initUI() {
		t1 = (TextView) findViewById(R.id.text1);
		t2 = (TextView) findViewById(R.id.text2);
//		xin = (LinearLayout) findViewById(R.id.xin);
//		record_bianji = (LinearLayout) findViewById(R.id.record_bianji);
		cursor = (ImageView) findViewById(R.id.cursor);
		mPager = (ViewPager) findViewById(R.id.vPager);
		t1.setOnClickListener(tv_Listener);
		t2.setOnClickListener(tv_Listener);
	}

	public void InitImageView() {
		Display dis = this.getWindowManager().getDefaultDisplay();
		disPlayWidth = dis.getWidth();
		b = BitmapFactory.decodeResource(this.getResources(), R.drawable.heng);
		offSet = ((disPlayWidth / 4) - b.getWidth() / 2);
		setdata();
		list = new ArrayList<View>();
		LayoutInflater li = LayoutInflater.from(TestRecordActivity.this);
		list.add(li.inflate(R.layout.testrecord_listdibiao, null));
		list.add(li.inflate(R.layout.testrecord_listdibiao, null));
	}

	public void initPager() {
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
		mPager.setCurrentItem(0);
		mPager.setOnPageChangeListener(this);
		/** ����ڶ��� */
		Layout1();
		/** �ر��³����� */
		Layout2();
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
			case R.id.t1:
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
			case R.id.t2:
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
		/** ����ڶ������Ŀؼ� */
		/** List�����д惦����View,��ȡ�����ϵĿؼ�,��List.get(0),0���Ǽ����е�һ������,1���Ǽ����еڶ������� */
		listView = (ListView) list.get(0).findViewById(R.id.record_lv_dibiao);

		adapter = new TestRecordAdapter(TestRecordActivity.this, infos);
		listView.setAdapter(adapter);
		// listview���е��
//		listView.setOnItemLongClickListener(new OnItemLongClickListener() {
//
//			@Override
//			public boolean onItemLongClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				iListPos1 = position;
//				// �Ի����ѡ��
//				//CharSequence items[] = { "��", "�༭", "����", "ɾ��" };
//				// ʵ�����Ի�
//				new AlertDialog.Builder(TestRecordActivity.this)
//						.setItems(/*items*/Constant.RecordRowClickItems, new DialogInterface.OnClickListener() {
//
//							@Override
//							public void onClick(DialogInterface dialog,
//									int which) {
//								CRTBTunnelMonitor CurApp = ((CRTBTunnelMonitor)getApplicationContext());
//								RecordInfo item = (RecordInfo)listView.getItemAtPosition(iListPos1);
//								switch (which) {
//								case 0:// �༭
//									Intent intent = new Intent(TestRecordActivity.this,
//											RecordNewActivity.class);
//									Bundle mBundle = new Bundle();  
//									mBundle.putInt(Constant.Select_RecordRowClickItemsName_Name, 2);
//							        mBundle.putParcelable(Constant.Select_RecordRowClickItemsName_Data, item);
//							        intent.putExtras(mBundle);
//							        TestRecordActivity.this.startActivityForResult(intent,0);
//									//startActivity(intent);
//									break;
//								case 1:// ɾ��
////									WorkInfos Curw = CurApp.GetCurWork();
////									TunnelCrossSectionDaoImpl impl = new TunnelCrossSectionDaoImpl(RecordActivity.this,Curw.getProjectName());
////									int iRet = impl.DeleteSection(item.getId());
////									switch (iRet) {
////									case 0:
////										Toast.makeText(RecordActivity.this, "ɾ��ʧ��", 3000).show();
////										break;
////									case 1:
////										Curw.DelTunnelCrossSectionInfo(item);
////										CurApp.UpdateWork(Curw);
////										adapter.notifyDataSetChanged();
////										Toast.makeText(RecordActivity.this, "ɾ���ɹ�", 3000).show();
////										break;
////									case -1:
////										Toast.makeText(RecordActivity.this, "ɾ���Ķ����д�������,����ɾ��", 3000).show();
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
//						.show().setCanceledOnTouchOutside(true);// ��ʾ�Ի���
//				return true;
//			}
//		});

	}

	public void Layout2() {
		/** ����ڶ������Ŀؼ� */
		/** List�����д惦����View,��ȡ�����ϵĿؼ�,��List.get(0),0���Ǽ����е�һ������,1���Ǽ����еڶ������� */
		listView1 = (ListView) list.get(1).findViewById(R.id.record_lv_dibiao);

		adapter1 = new TestRecordAdapter(TestRecordActivity.this, infos1);
		listView1.setAdapter(adapter1);
//		// listview���е��
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
//								case 0:// �༭
//									Intent intent = new Intent(TestRecordActivity.this,
//											RecordNewActivity.class);
//									Bundle mBundle = new Bundle();  
//									mBundle.putInt(Constant.Select_RecordRowClickItemsName_Name, 4);
//							        mBundle.putParcelable(Constant.Select_RecordRowClickItemsName_Data, item);
//							        intent.putExtras(mBundle);
//									TestRecordActivity.this.startActivityForResult(intent,0);
//									//startActivity(intent);
//									break;
//								case 1:// ɾ��
////									WorkInfos Curw = CurApp.GetCurWork();
////									SubsidenceCrossSectionDaoImpl impl = new SubsidenceCrossSectionDaoImpl(TestRecordActivity.this,Curw.getProjectName());
////									int iRet = impl.DeleteSubsidenceCrossSection(item.getId());
////									switch (iRet) {
////									case 0:
////										Toast.makeText(TestRecordActivity.this, "ɾ��ʧ��", 3000).show();
////										break;
////									case 1:
////										Curw.DelSubsidenceCrossSectionInfo(item);
////										CurApp.UpdateWork(Curw);
////										adapter1.notifyDataSetChanged();
////										Toast.makeText(TestRecordActivity.this, "ɾ���ɹ�", 3000).show();
////										break;
////									case -1:
////										Toast.makeText(TestRecordActivity.this, "ɾ���Ķ����д�������,����ɾ��", 3000).show();
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
//						.show().setCanceledOnTouchOutside(true);// ��ʾ�Ի���
//				return true;
//			}
//		});
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN) {
			// TODO Auto-generated method stub
			if (keyCode == 82) {
				vie = new View(this);
				int num = 3;
				
				menuWindow = new SelectPicPopupWindow(this, itemsOnClick,3,currIndex);
				menuWindow.showAtLocation(vie, Gravity.BOTTOM
						| Gravity.CENTER_HORIZONTAL, 0, 0);
				
			}
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				this.finish();
			}
		}
		return true;
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
