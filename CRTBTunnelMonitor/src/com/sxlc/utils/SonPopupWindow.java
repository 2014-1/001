package com.sxlc.utils;

import java.util.List;

import com.sxlc.activity.CRTBTunnelMonitor;
import com.sxlc.activity.ControlNewActivity;
import com.sxlc.activity.MainActivity;
import com.sxlc.activity.R;
import com.sxlc.activity.RecordActivity;
import com.sxlc.activity.StationActivity;
import com.sxlc.activity.TotalStationActivity;
import com.sxlc.common.Constant;
import com.sxlc.entity.TotalStationInfo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class SonPopupWindow extends PopupWindow {
	private RelativeLayout xinjian;
	public RelativeLayout bianji;
	public RelativeLayout delete;
	private View mMenuView;
	private Intent intent;
	public Context c;
	AlertDialog dlg = null;
	public SonPopupWindow(Activity context, OnClickListener itemsOnClick,
			final int num, final int currIndex) {
		super(context);
		this.c = context;
		dlg = new AlertDialog.Builder(c).create();
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.son_dialog, null);
		xinjian = (RelativeLayout) mMenuView.findViewById(R.id.cr1);
		bianji = (RelativeLayout) mMenuView.findViewById(R.id.cr2);
		delete = (RelativeLayout) mMenuView.findViewById(R.id.cr3);

		xinjian.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(c, ControlNewActivity.class);
				Bundle mBundle = new Bundle();  
		        mBundle.putParcelable(Constant.Select_TotalStationRowClickItemsName_Data,null);
		        intent.putExtras(mBundle);
				((Activity) c).startActivityForResult(intent,0);
			}
		});
		bianji.setOnClickListener(new OnClickListener() {

			//@SuppressLint("ShowToast")
			@Override
			public void onClick(View v) {
				List<TotalStationInfo> tmpList = ((StationActivity)c).list;
				TotalStationInfo tmp = null;
				if (tmpList == null) {
					Toast.makeText((Activity) c, "��ѡ����Ҫ�༭��ȫվ��", 3000).show();
				}
				else {
					for (int i = 0; i < tmpList.size(); i++) {
						if (tmpList.get(i).isbCheck()) {
							tmp = tmpList.get(i);
							break;
						}
					}
				}
				if (tmp == null) {
					Toast.makeText((Activity) c, "��ѡ����Ҫ�༭��ȫվ��", 3000).show();
				}
				Intent intent = new Intent(c, ControlNewActivity.class);
				Bundle mBundle = new Bundle();  
		        mBundle.putParcelable(Constant.Select_TotalStationRowClickItemsName_Data,tmp);
		        intent.putExtras(mBundle);
				((Activity) c).startActivityForResult(intent,0);
			}
		});
		delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showExitGameAlert();
			}
		});
		this.setContentView(mMenuView);
		this.setWidth(LayoutParams.FILL_PARENT);
		this.setHeight(LayoutParams.WRAP_CONTENT);
		// ����SelectPicPopupWindow��������ɵ��
		this.setFocusable(true);
		// ʵ����һ��ColorDrawable��ɫΪ��͸��
		ColorDrawable dw = new ColorDrawable(0xFF000000);
		// ����SelectPicPopupWindow��������ı���
		this.setBackgroundDrawable(dw);
		// mMenuView���OnTouchListener�����жϻ�ȡ����λ�������ѡ������������ٵ�����
		mMenuView.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {

				int height = mMenuView.findViewById(R.id.pop_layout).getTop();
				int y = (int) event.getY();
				if (event.getAction() == MotionEvent.ACTION_UP) {
					if (y < height) {
						dismiss();
					}
				}
				return true;
			}
		});
	}

	private void showExitGameAlert() {
		dismiss();
		dlg.show();
		Window window = dlg.getWindow();
		// *** ��Ҫ����������ʵ������Ч����.
		window.setContentView(R.layout.dialog);
		// Ϊȷ�ϰ�ť����¼�,ִ���˳�Ӧ�ò���
		ImageButton delete2 = (ImageButton) window.findViewById(R.id.delete2);
		Button qd = (Button) window.findViewById(R.id.qd);
		Button qx = (Button) window.findViewById(R.id.qx);
		delete2.setOnClickListener(listener);
		qd.setOnClickListener(listener);
		qx.setOnClickListener(listener);

	}

	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.delete2:
				dlg.cancel();
				
				break;
			case R.id.qd:
				// ����
				for (int i = 0; i < MainActivity.list.size(); i++) {
					if (MainActivity.list.get(i).getInfo().equals("ѡ��")) {
						MainActivity.list.remove(i);
						StationActivity.adapter.notifyDataSetChanged();
					}
					dlg.cancel();
				}
				break;
			case R.id.qx:
				dlg.cancel();
				break;
			}

		}
	};
}
