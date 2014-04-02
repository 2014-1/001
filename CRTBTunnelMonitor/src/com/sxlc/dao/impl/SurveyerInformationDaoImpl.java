package com.sxlc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sxlc.dao.SurveyerInformationDao;
import com.sxlc.db.SqliteHelperDTMS;
import com.sxlc.entity.SurveyerInformation;
/**
 *������Ա��Ϣ���ݿ�ӿ�ʵ��
 *����ʱ�䣺2014-3-24����13:50:00
 *@author ����
 *@since JDK1.6
 *@version 1.0
 */
public class SurveyerInformationDaoImpl implements SurveyerInformationDao{
	private SqliteHelperDTMS helper = null;
	private SQLiteDatabase db = null;

	public SurveyerInformationDaoImpl(Context c,String name) {
		helper = new SqliteHelperDTMS(c, name,null,0);
		db = helper.getReadableDatabase();

	}
	/**��ѯȫ��*/
	public List<SurveyerInformation> SelectAllSurveyerInfo() {
		List<SurveyerInformation> list = null;
		SurveyerInformation entity = null;
		int iIndex = 0;
		String sql = "select * from SurveyerInformation";
		try {
			Cursor c = db.rawQuery(sql, null);
			if(c!=null){
				while(c.moveToNext()){
					entity = new SurveyerInformation();
					iIndex = c.getColumnIndex("ID");
					entity.setId(c.getInt(iIndex));
					iIndex = c.getColumnIndex("SurveyerName");
					entity.setSurveyerName(c.getString(iIndex));
					iIndex = c.getColumnIndex("CertificateID");
					entity.setCertificateID(c.getString(iIndex));
					iIndex = c.getColumnIndex("Password");
					entity.setPassword(c.getString(iIndex));
					iIndex = c.getColumnIndex("Info");
					entity.setInfo(c.getString(iIndex));
					iIndex = c.getColumnIndex("ProjectID");
					entity.setProjectID(c.getInt(iIndex));
					if(list==null){
						list = new ArrayList<SurveyerInformation>();
					}
					list.add(entity);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("SelectAllSurveyerInfo:" + e.getLocalizedMessage());
		}
		return list;
	}

	/**�½�������Ա*/
	public Boolean InsertSurveyerInfo(SurveyerInformation s) {
		boolean result = false;
		if(s == null){
			return result;
		}
		String sql = "insert into SurveyerInformation(SurveyerName,CertificateID,Password,Info,ProjectID)" +
				" values(?,?,?,?,?)";
		try {
			Object[] obj = {s.getSurveyerName(),s.getCertificateID(),s.getPassword(),s.getInfo(),s.getProjectID()};
			db.execSQL(sql, obj);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			result = false;
			System.out.println("InsertSurveyerInfo:" + e.getLocalizedMessage());
		}
		return result;
	}

	/**��ѯ������Ա*/
	public SurveyerInformation SelectSurveyerInfo(int id) {
		SurveyerInformation entity = null;
		String sql = "select * from SurveyerInformation where id=" + Integer.toString(id);
		int iIndex = 0;
		try {
			Cursor c = db.rawQuery(sql, null);
			if(c!=null){
				while(c.moveToNext()){
					if(entity == null){
						entity = new SurveyerInformation();
					}
					iIndex = c.getColumnIndex("ID");
					entity.setId(c.getInt(iIndex));
					iIndex = c.getColumnIndex("SurveyerName");
					entity.setSurveyerName(c.getString(iIndex));
					iIndex = c.getColumnIndex("CertificateID");
					entity.setCertificateID(c.getString(iIndex));
					iIndex = c.getColumnIndex("Password");
					entity.setPassword(c.getString(iIndex));
					iIndex = c.getColumnIndex("Info");
					entity.setInfo(c.getString(iIndex));
					iIndex = c.getColumnIndex("ProjectID");
					entity.setProjectID(c.getInt(iIndex));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("SelectSurveyerInfo:" + e.getLocalizedMessage());
		}
		return entity;
	}

	/**ɾ��������Ա*/
	public Boolean DeleteSurveyerInfo(int id) {
		boolean result = false;
		String sql = "delete from SurveyerInformation where id=?" ;
		try {
			Object[] obj = {id};
			db.execSQL(sql, obj);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			result = false;
			System.out.println("DeleteSurveyerInfo:" + e.getLocalizedMessage());
		}
		return result;
	}

	/**�༭������Ա*/
	public Boolean UpdateSurveyerInfo(SurveyerInformation s) {
		boolean result = false;
		if(s ==  null){
			return result;
		}
		String sql = "update SurveyerInformation set SurveyerName=?,CertificateID=?," +
				"Password=?,Info=?,ProjectID=? where id =?";
		try {
			Object[] obj = {s.getSurveyerName(),s.getCertificateID(),s.getPassword(),s.getInfo(),s.getPassword()};
			db.execSQL(sql, obj);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			result = false;
			System.out.println("UpdateSurveyerInfo:" + e.getLocalizedMessage());
		}
		return result;
	}

}
