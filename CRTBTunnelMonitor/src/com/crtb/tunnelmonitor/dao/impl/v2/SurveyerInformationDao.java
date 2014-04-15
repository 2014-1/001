package com.crtb.tunnelmonitor.dao.impl.v2;

import java.util.List;

import com.crtb.tunnelmonitor.entity.SurveyerInformation;

public class SurveyerInformationDao extends AbstractDao<SurveyerInformation> {

	private static SurveyerInformationDao _instance ;
	
	private SurveyerInformationDao(){
		
	}
	
	public static SurveyerInformationDao defaultDao(){
		
		if(_instance == null){
			_instance	= new SurveyerInformationDao() ;
		}
		
		return _instance ;
	}
	
	public void deleteAll(){
		mDatabase.deleteAll(SurveyerInformation.class);
	}
	
	public List<SurveyerInformation> queryAllSurveyerInformation(){
		
		String sql = "select * from SurveyerInformation" ;
		
		return mDatabase.queryObjects(sql, SurveyerInformation.class);
	}
}
