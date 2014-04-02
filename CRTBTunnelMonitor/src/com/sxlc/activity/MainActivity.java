package com.sxlc.activity;

import java.util.ArrayList;
import java.util.List;

import com.sxlc.common.Constant;
import com.sxlc.entity.TotalStationInfo;
import com.sxlc.entity.WorkInfos;


import com.sxlc.infors.ProjectInformation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * ������ ����ʱ�䣺2014-3-18����3:52:30
 * 
 * @author ����
 * @since JDK1.6
 * @version 1.0
 */
public class MainActivity extends Activity implements OnClickListener {

	/** ������ */
	private RelativeLayout main_rl_work;
	/** ���� */
	private RelativeLayout main_rl_fracturesurface;
	/** ��¼�� */
	private RelativeLayout main_rl_record;
	/** ȫվ�� */
	private RelativeLayout main_rl_total;
	/** ���� */
	private RelativeLayout main_rl_measure;
	/** Ԥ�� */
	private RelativeLayout main_rl_warning;
	/** ������ */
	private RelativeLayout main_rl_servers;
	/** ���� */
	private RelativeLayout main_rl_asregards;
	/** ��ͼ */
	private Intent intent;
	/**
	 * �û�����ѡ��״̬
	 */
	public static List<TotalStationInfo> list = new ArrayList<TotalStationInfo>();
	private TotalStationInfo info = new TotalStationInfo();
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		//����
		info.setName("leon0");
		info.setInfo("δѡ��");
		info.setBaudRate(1);
		info.setDatabits(1);
		info.setId(1);
		info.setParity(1);
		info.setCmd("cmd2");
		info.setStopbits(1);
		info.setTotalstationType("sa");
		list.add(info);
		info = new TotalStationInfo();
		info.setName("leon2");
		info.setInfo("δѡ��");
		info.setBaudRate(12);
		info.setDatabits(12);
		info.setId(12);
		info.setParity(12);
		info.setCmd("cmd4");
		info.setStopbits(12);
		info.setTotalstationType("sa1");
		list.add(info);
	}

	/** ��ʼ���ؼ� */
	private void initView() {
		main_rl_work = (RelativeLayout) findViewById(R.id.main_rl_work);
		main_rl_fracturesurface = (RelativeLayout) findViewById(R.id.main_rl_fracturesurface);
		main_rl_record = (RelativeLayout) findViewById(R.id.main_rl_record);
		main_rl_total = (RelativeLayout) findViewById(R.id.main_rl_total);
		main_rl_measure = (RelativeLayout) findViewById(R.id.main_rl_measure);
		main_rl_warning = (RelativeLayout) findViewById(R.id.main_rl_warning);
		main_rl_servers = (RelativeLayout) findViewById(R.id.main_rl_servers);
		main_rl_asregards = (RelativeLayout) findViewById(R.id.main_rl_asregards);
		// �ж��Ƿ���ʾ������ͼ��
		int num = getIntent().getExtras().getInt(Constant.Select_LoginName_Name);
		if (num == Constant.Select_LoginValue_Local) {
			// Ӱ�ؿؼ�
			main_rl_servers.setVisibility(View.GONE);
		}
		// ����¼�
		main_rl_work.setOnClickListener(this);
		main_rl_fracturesurface.setOnClickListener(this);
		main_rl_record.setOnClickListener(this);
		main_rl_total.setOnClickListener(this);
		main_rl_measure.setOnClickListener(this);
		main_rl_warning.setOnClickListener(this);
		main_rl_servers.setOnClickListener(this);
		main_rl_asregards.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		 case R.id.main_rl_work://������
		 {
			 intent = new Intent(MainActivity.this,WorkActivity.class);
			 startActivity(intent);
		 }
		 break;
		 case R.id.main_rl_fracturesurface: // ����
		 {
			CRTBTunnelMonitor CurApp = ((CRTBTunnelMonitor)getApplicationContext());
			WorkInfos CurW = CurApp.GetCurWork();
			if(CurW == null)
			{
				Toast.makeText(MainActivity.this, "���ȴ򿪹�����", 3000).show();
				return;
			}
			intent = new Intent(MainActivity.this,SectionActivity.class);
			startActivity(intent);
		 }
		 break;
		 case R.id.main_rl_record: // ��¼��
		 {
				CRTBTunnelMonitor CurApp = ((CRTBTunnelMonitor)getApplicationContext());
				WorkInfos CurW = CurApp.GetCurWork();
				if(CurW == null)
				{
					Toast.makeText(MainActivity.this, "���ȴ򿪹�����", 3000).show();
					return;
				}
    			intent = new Intent(MainActivity.this,RecordActivity.class);
				startActivity(intent);
		 }
		 break;
		 case R.id.main_rl_total: // ȫվ��
		 {
			CRTBTunnelMonitor CurApp = ((CRTBTunnelMonitor)getApplicationContext());
			WorkInfos CurW = CurApp.GetCurWork();
			if(CurW == null)
			{
				Toast.makeText(MainActivity.this, "���ȴ򿪹�����", 3000).show();
				return;
			}
			 intent = new Intent(MainActivity.this,TotalStationActivity.class);
			 startActivity(intent);
		 }
		 break;
		 case R.id.main_rl_measure: // ����
		 {
			CRTBTunnelMonitor CurApp = ((CRTBTunnelMonitor)getApplicationContext());
			WorkInfos CurW = CurApp.GetCurWork();
			if(CurW == null)
			{
				Toast.makeText(MainActivity.this, "���ȴ򿪹�����", 3000).show();
				return;
			}
			 intent = new Intent(MainActivity.this,TestRecordActivity.class);
			 startActivity(intent);
		 }
		 break;
		 case R.id.main_rl_warning: // Ԥ��
		 {
			CRTBTunnelMonitor CurApp = ((CRTBTunnelMonitor)getApplicationContext());
			WorkInfos CurW = CurApp.GetCurWork();
			if(CurW == null)
			{
				Toast.makeText(MainActivity.this, "���ȴ򿪹�����", 3000).show();
				return;
			}
			intent = new Intent(MainActivity.this,WarningActivity.class);
			startActivity(intent);
		 }
		 break;
		 case R.id.main_rl_servers: // ������
		 {
			 intent = new Intent(MainActivity.this,ServersActivity.class);
			 startActivity(intent);
		 }
		 break;
		 case R.id.main_rl_asregards: // ����
		 {
			 intent = new Intent(MainActivity.this,AsregardsActivity.class);
			 startActivity(intent);
		 }
		 break;
		}

	}
}
