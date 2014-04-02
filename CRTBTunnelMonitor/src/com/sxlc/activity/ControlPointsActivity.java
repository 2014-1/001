package com.sxlc.activity;

import java.util.ArrayList;
import java.util.List;

import com.sxlc.adapter.ControlPonitsListAdapter;
import com.sxlc.adapter.ControlPonitsListAdapter2;
import com.sxlc.common.Constant;
import com.sxlc.dao.impl.ControlPointsDaoImpl;
import com.sxlc.dao.impl.TotalStationDaoImpl;
import com.sxlc.entity.ControlPointsInfo;
import com.sxlc.entity.TotalStationInfo;
import com.sxlc.entity.WorkInfos;
import com.sxlc.utils.ConPopuWindow;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class ControlPointsActivity extends Activity {
	/**
	 * ��ʾ�û�����ѡ��״̬
	 */
	private ListView listview;
	private int iItemPos = -1;
	public List<ControlPointsInfo> list = null;
	public static ControlPonitsListAdapter2 adapter;
	private OnClickListener itemsOnClick;
	private View vie;
	private ConPopuWindow menuWindow;
	private CRTBTunnelMonitor CurApp = null;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_controlpoints);
		CurApp = ((CRTBTunnelMonitor)getApplicationContext());
		listview=(ListView) findViewById(R.id.control_sonlist);
		getadapter();
		/** ���� */
		listview.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				iItemPos = position; 
				// ʵ�����Ի�
				new AlertDialog.Builder(ControlPointsActivity.this)
						.setItems(/* items */new String[]{"ʹ�øõ�"},
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										switch (which) {
										case 0: // ��������
											ControlPointsInfo item = list.get(iItemPos);
											item.setbUse(true);
											list.set(iItemPos, item);
											for (int i = 0; i < list.size(); i++) {
												if (i != iItemPos) {
													list.get(i).setbUse(false);
												}
											WorkInfos curW = CurApp.GetCurWork();
											curW.setCpList(list);
											}
											break;
										}
									}
								}).setCancelable(false).show()
						.setCanceledOnTouchOutside(true);// ��ʾ�Ի���
				return true;
			}
		});
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				ControlPointsInfo item = list.get(arg2);	
				boolean bCheck = !item.isbCheck();
				item.setbCheck(!item.isbCheck());
				list.set(arg2, item);
				if (bCheck) {
					for (int i = 0; i < list.size(); i++) {
						if (i != arg2) {
							list.get(i).setbCheck(false);
						}
					}
				}
				adapter.notifyDataSetChanged();
			}
		});
	}
	public void setdata() {
		WorkInfos CurW = CurApp.GetCurWork();
		if(CurW == null)
		{
			return;
		}
		list = CurW.getCpList();
		boolean bLoadDB = true;
		if(list!=null)
		{
			if(list.size()>0)
			{
				bLoadDB = false;
			}
		}
		if(bLoadDB)
		{
			if(list == null)
			{
				list = new ArrayList<ControlPointsInfo>();
			}
			ControlPointsDaoImpl impl = new ControlPointsDaoImpl(this, CurW.getProjectName());
			impl.GetControlPointsList(list);
			CurW.setCpList(list);
			CurApp.UpdateWork(CurW);
		}
	}
	
	public void getadapter() {
		setdata();
		adapter = new ControlPonitsListAdapter2(ControlPointsActivity.this, list);
		listview.setAdapter(adapter);
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN) {
			// TODO Auto-generated method stub
			if (keyCode == 82) {
				vie = new View(this);
				int num = 1;
				menuWindow = new ConPopuWindow(this, itemsOnClick, 1, 0);
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
			adapter.notifyDataSetChanged();
			break;

		default:
			break;
		}
	}
}
