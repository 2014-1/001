package com.crtb.tunnelmonitor.service;

import java.util.HashMap;
import java.util.Map;

import org.ksoap2.serialization.SoapObject;

import android.util.Log;

class GetZoneAndSiteCodeRpc extends AbstractRpc {
	private static final String LOG_TAG = "GetZoneAndSiteCodeRpc";
	private static final String KEY_RANDOM_CODE = "随机码";
	private static final String KEY_ACTION = "getZoneAndSiteCode";
	
	private Map<String, Long> mParameters = new HashMap<String, Long>();
	private RpcCallback mCallback;
	
	GetZoneAndSiteCodeRpc(long randomCode, RpcCallback callback) {
		mParameters.put(KEY_RANDOM_CODE, randomCode);
		mCallback = callback;
	}
	
	@Override
	public SoapObject getRpcMessage(String namesapce) {
		SoapObject message = new SoapObject(namesapce, KEY_ACTION);
		for(String key : mParameters.keySet()) {
			message.addProperty(key, mParameters.get(key));
		}
		return message;
	}

	@Override
	public void onResponse(Object response) {
		if (response == null) {
			notifyFailed("Response is NULL.");
			return;
		}
		if (!(response instanceof SoapObject)) {
			notifyFailed("Unknown reponse type: " + response.getClass().getName());
			return;
		}
		Log.d(LOG_TAG, "response: " + response);
		try {
			//getZoneAndSiteCodeResponse{return=anyType{item=XPCL01SG05GQ01#一工区; item=XPCL01SD0001#跃龙门隧道; }; }
			SoapObject result = (SoapObject) response;
			SoapObject data = (SoapObject) result.getProperty(0);
			final int count = data.getPropertyCount();
			String[][] zoneAndSiteCodes = new String[count][2];
			for(int i = 0 ; i < count; i++) {
				String[] codes = data.getPropertyAsString(i).split("#");
				zoneAndSiteCodes[i][0] = codes[0];
				zoneAndSiteCodes[i][1] = codes[1];
			}
			notifySuccess(zoneAndSiteCodes);
		} catch (Exception e) {
			notifyFailed("Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void notifySuccess(Object[] data) {
		if (mCallback != null) {
			mCallback.onSuccess(data);
		}
	}
	
	private void notifyFailed(String reason) {
		if (mCallback != null) {
			mCallback.onFailed(reason);
		}
	}
}
