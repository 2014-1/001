package com.sxlc.service;

import org.ksoap2.serialization.SoapObject;

import com.sxlc.common.Constant;

public class LoginRpc extends AbstractRpc {
	private static final String LOG_TAG = "LoginRpc";
	private static final String KEY_ACCOUNT = "��½�ʺ�";
	private static final String KEY_PASSWORD = "��½����";
	private static final String KEY_MAC_ADDRESS = "�豸�����ַ";
	private static final String KEY_PUBLIC_KEY = "���ܺ���Կ";
	private static final String KEY_ACTION = "getPublicKey";
	
	public LoginRpc() {
		SoapObject soapObject = new SoapObject(Constant.NameSpace,"/verifyAppUser");
		soapObject.addProperty("��½�˺�", username);
		soapObject.addProperty("��½����", pwd);
		soapObject.addProperty("�豸�����ַ", Constant.testPhysical);
		soapObject.addProperty("���ܺ���Կ", CurApp.getPublickey());
	}
	@Override
	public SoapObject getRpcMessage(String namesapce) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onResponse(Object response) {
		// TODO Auto-generated method stub
		
	}


}
