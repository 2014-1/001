package com.sxlc.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.sxlc.common.Constant;
import com.sxlc.entity.SurveyerInformation;
/**
 * �û�ѡ�� ����
 *����ʱ�䣺2014-3-18����4:12:05
 *@author ����
 *@since JDK1.6
 *@version 1.0
 */
public class SelecActivity extends Activity implements OnClickListener{

	/**�����û����*/
	private ImageView select_img_place;
	/**�������û����*/
	private ImageView select_img_service;
	/** ��ͼ */
	private Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select);
		initView();
		CRTBTunnelMonitor CurApp = ((CRTBTunnelMonitor)getApplicationContext());
		CurApp.GetDB().ConnectDB();
		/*���Դ���*/
		List<SurveyerInformation> testList = new ArrayList<SurveyerInformation>();
		SurveyerInformation test = new SurveyerInformation();
		test.setCertificateID("511325197812155213");
		test.setId(1);
		test.setInfo("����");
		test.setProjectID(1);
		test.setSurveyerName("����Ա");
		testList.add(test);
		CurApp.setPersonList(testList);
		CurApp.setCurPerson(test);
		/*���Դ���*/
	}

	/** ��ʼ���ؼ� */
	private void initView() {
		select_img_place = (ImageView) findViewById(R.id.select_img_place);
		select_img_service = (ImageView) findViewById(R.id.select_img_service);
		// ����¼�
		select_img_place.setOnClickListener(this);
		select_img_service.setOnClickListener(this);
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.select_img_place:// ֱ����ת������
			intent = new Intent(SelecActivity.this,MainActivity.class);
			intent.putExtra(Constant.Select_LoginName_Name, Constant.Select_LoginValue_Local);
			startActivity(intent);
			break;
		case R.id.select_img_service: // ��ת����ĵ�¼����
			intent = new Intent(SelecActivity.this,LoginActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
		
	}

}
