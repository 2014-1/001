package com.sxlc.activity;


import java.sql.Timestamp;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import ICT.utils.RSACoder;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
//import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.sxlc.common.Constant;

/**
 * ��������¼���� ����ʱ�䣺2014-3-18����4:11:55
 * 
 * @author ����
 * @since JDK1.6
 * @version 1.0
 */
public class LoginActivity extends Activity implements OnClickListener {

	/**��¼��ť */
	private Button login_btn;
	/** ��ͼ��ת���� */
	private Intent intent;
	/**��¼�û���Ϣ�б�*/
	private RelativeLayout login_rl_listview;
	//�û��������
	private EditText et_username;
	//���������
	private EditText et_password;
	//APPʵ��
	private CRTBTunnelMonitor CurApp;
	private String veri;
	private String rsal;
	private String[] sZoneAndSiteCode = null;
	private int Result = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
	}

	/** ��ʼ���ؼ� */
	private void initView() {
		login_btn = (Button) findViewById(R.id.login_btn);
		login_rl_listview = (RelativeLayout) findViewById(R.id.login_rl_listview);
		et_username = (EditText) findViewById(R.id.et_username);
		et_password = (EditText) findViewById(R.id.et_password);
		CurApp = ((CRTBTunnelMonitor)getApplicationContext());
		//
		login_rl_listview.setVisibility(View.GONE);
		// ����¼�
		login_btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_btn:
			//��ȡ�û���������
			String name = et_username.getText().toString().trim();
			String pwd = et_password.getText().toString().trim();
			String verify = "0";
			//�û���֤
			if(name==null||pwd==null){
				verify = loginTest(Constant.testUsername,Constant.testPassword);
			}else{
				verify = loginTest(name,pwd);
			}
			//��֤ʧ��ʱ��ʾ������
			if("0".equals(verify)){
				Toast.makeText(LoginActivity.this, "�û���֤ʧ�ܣ�", Toast.LENGTH_LONG).show();
				break;
			}else if("-1".equals(verify)){
				break;
			}
			//��֤�ɹ�����ת��������
			CurApp.setVerify(verify);
			intent = new Intent(LoginActivity.this, MainActivity.class);
			intent.putExtra("name", 2);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

	private String loginTest(String username, String password) {
		String ver = String.valueOf(0);
		//��ȡ��Կ
		String publicKey = getPub(username,Constant.testPhysical);
		if("0".equals(publicKey)){
			//��ȡʧ��ʱ��ʾ������0
			Toast.makeText(LoginActivity.this, "��ȡ��Կʧ�ܣ�", Toast.LENGTH_LONG).show();
			ver = String.valueOf(-1);
			return ver;
		}
		//�ɹ���ͨ��˽Կ����
		CurApp.setPublickey(publicKey);
		ver = loginSelect(username,password);
		return ver;
	}

	private String loginSelect(final String username, String password) {
		veri = String.valueOf(0);
		//�������
		final String pwd = RSACoder.encnryptDes(password, Constant.testDeskey);
		new Thread(){
			public void run() {
				//����HttpTransportSe����
				HttpTransportSE ht=new HttpTransportSE(Constant.UserSelect);
				ht.debug=true;
				SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER12);
				//ʵ����SoapObject����
				SoapObject soapObject = new SoapObject(Constant.NameSpace,"/verifyAppUser");
				soapObject.addProperty("��½�˺�", username);
				soapObject.addProperty("��½����", pwd);
				soapObject.addProperty("�豸�����ַ", Constant.testPhysical);
				soapObject.addProperty("���ܺ���Կ", CurApp.getPublickey());

				envelope.bodyOut = soapObject;
				try {
					//���� web Service	
					ht.call(Constant.NameSpace+"verifyAppUser",envelope);
					if(envelope.getResponse()!=null){
						SoapObject result=(SoapObject)envelope.bodyIn;
						SoapObject detail=(SoapObject)result.getProperty("verifyAppUserResponse");
						veri = detail.getProperty(0).toString();
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			};
		}.start();
		return veri;
	}

	private String getPub(final String username,final String testShebei) {
		rsal = String.valueOf(0);
		//���� web Service
		new Thread(){
			public void run() {
				//����HttpTransportSe����
				HttpTransportSE ht=new HttpTransportSE(Constant.UserSelect);
				ht.debug=true;
				SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
				//ʵ����SoapObject����
				SoapObject soapObject = new SoapObject(Constant.NameSpace,"getPublicKey");
				soapObject.addProperty("��½�˺�", username);
				soapObject.addProperty("�����ַ", testShebei);
				envelope.bodyOut = soapObject;

				try {
					ht.call(Constant.NameSpace+"getPublicKey",envelope);
					if(envelope.getResponse()!=null){
						SoapObject result=(SoapObject)envelope.bodyIn;
						String pub= result.getProperty(0).toString();
						if(!"0".equals(pub)){
							rsal = RSACoder.encnryptRSA(Constant.testDeskey, pub);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
		}.start();
		return rsal;
	}
	
	//��ȡ������������
	public String[] getZoneAndSiteCode(final String Randomcode){
		sZoneAndSiteCode = null;
		new Thread(){
			public void run() {
				//����HttpTransportSe����
				HttpTransportSE ht=new HttpTransportSE(Constant.UserSelect);
				ht.debug=true;
				SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER12);
				//ʵ����SoapObject����
				SoapObject soapObject = new SoapObject(Constant.NameSpace,"/getZoneAndSiteCode");
				soapObject.addProperty("�����", Randomcode);

				envelope.bodyOut = soapObject;
				try {
					//���� web Service	
					ht.call(Constant.NameSpace+"getZoneAndSiteCode",envelope);
					if(envelope.getResponse()!=null){
						SoapObject result=(SoapObject)envelope.bodyIn;
//						SoapObject detail=(SoapObject)result.getProperty("verifyAppUserResponse");
						sZoneAndSiteCode = (String[])result.getProperty(0);
					}
				}catch(Exception e) {
					e.printStackTrace();
					System.out.println("getZoneAndSiteCode:" + e.getLocalizedMessage());
				}
			};
		}.start();
		
		return sZoneAndSiteCode;
	}
	
	//����������漰���ӿ�
	public int getSectionPointInfo(final String zonecode,final String Sitecode,final String Sectname,final String Sectcode,final String Sectkilo,
			final String Sectmethod,final float Sectwidth,final float Movevalueuo,final String Updatetime,final String Uoremark,final int Rocklevel,
			final String Testcodes,final String Objlaytime,final String Remark,final String Randomcode){
		int iResult = 0;
		
		new Thread(){
			public void run() {
				//����HttpTransportSe����
				HttpTransportSE ht=new HttpTransportSE(Constant.UserSelect);
				ht.debug=true;
				SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER12);
				//ʵ����SoapObject����
				SoapObject soapObject = new SoapObject(Constant.NameSpace,"/getZoneAndSiteCode");
				soapObject.addProperty("��������", zonecode);
				soapObject.addProperty("",Sitecode);
				soapObject.addProperty("",Sectname);
				soapObject.addProperty("",Sectcode);
				soapObject.addProperty("",Sectkilo);
				soapObject.addProperty("",Sectmethod);
				soapObject.addProperty("",Sectwidth);
				soapObject.addProperty("",Movevalueuo);
				soapObject.addProperty("",Updatetime);
				soapObject.addProperty("",Uoremark);
				soapObject.addProperty("",Rocklevel);
				soapObject.addProperty("",Testcodes);
				soapObject.addProperty("",Objlaytime);
				soapObject.addProperty("",Remark);
				soapObject.addProperty("",Randomcode);

				envelope.bodyOut = soapObject;
				try {
					//���� web Service	
					ht.call(Constant.NameSpace+"getZoneAndSiteCode",envelope);
					if(envelope.getResponse()!=null){
						SoapObject result=(SoapObject)envelope.bodyIn;
//						SoapObject detail=(SoapObject)result.getProperty("verifyAppUserResponse");
						Result = Integer.valueOf(result.getProperty(0).toString());
					}
				}catch(Exception e) {
					e.printStackTrace();
					System.out.println("getZoneAndSiteCode:" + e.getLocalizedMessage());
				}
			};
		}.start();
		iResult = Result;
		return iResult;
	}
}
