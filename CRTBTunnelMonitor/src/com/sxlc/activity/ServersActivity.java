package com.sxlc.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
/**
 * ������
 *����ʱ�䣺2014-3-16����10:38:01
 *@author ����
 *@since JDK1.6
 *@version 1.0
 */
public class ServersActivity extends Activity implements OnClickListener{

	/**��ͼ*/
	private Intent intent;
	/**��������"*/
	private RelativeLayout rl_parameter;
	/**�����ϴ�"*/
	private RelativeLayout rl_datauploading;
	/**Ԥ���ϴ�"*/
	private RelativeLayout rl_warninguploading;
	/**���ع�����"*/
	private RelativeLayout rl_downloadwork;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_servers);
	    initView();
	}
	/** ��ʼ���ؼ� */
	private void initView() {
		rl_parameter = (RelativeLayout) findViewById(R.id.rl_parameter);
		rl_datauploading = (RelativeLayout) findViewById(R.id.rl_datauploading);
		rl_warninguploading = (RelativeLayout) findViewById(R.id.rl_warninguploading);
		rl_downloadwork = (RelativeLayout) findViewById(R.id.rl_downloadwork);
		// 
		rl_parameter.setOnClickListener(this);
		rl_datauploading.setOnClickListener(this);
		rl_warninguploading.setOnClickListener(this);
		rl_downloadwork.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		default:
			break;
		}
		
	}
}
