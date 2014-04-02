package com.sxlc.dao.impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.sxlc.dao.StationInfoDao;
import com.sxlc.db.SqliteHelperDTMS;

/**
 * ��վ��Ϣ���ݿ�ӿ�ʵ��
 *����ʱ�䣺2014-3-24����13:50:00
 *@author ����
 *@since JDK1.6
 *@version 1.0
 */

public class StationInfoDaoImpl implements StationInfoDao{
	private SqliteHelperDTMS helper = null;
	private SQLiteDatabase db = null;
	public StationInfoDaoImpl(Context c,String name){
		helper = new SqliteHelperDTMS(c, name,null,0);
		db = helper.getReadableDatabase();
	}
//	
//	//�鿴������վ��Ϣ
//	public List<StationInfo> GetAllStation() {
//		List<StationInfo> list = null;
//		String sql = "select * from StationInfoIndex";
//		StationInfo entity = null;
//		int iIndex = 0;
//		try {
//			Cursor c = db.rawQuery(sql, null);
//			while(c.moveToNext()){
//				entity = new StationInfo();
//				iIndex = c.getColumnIndex("ID");
//				entity.setId(c.getInt(iIndex));
//				iIndex = c.getColumnIndex("StationPointId");
//				entity.setStationPointId(c.getInt(iIndex));
//				iIndex = c.getColumnIndex("StationHeight");
//				entity.setStationHeight(c.getDouble(iIndex));
//				iIndex = c.getColumnIndex("BackSightPointIds");
//				entity.setBackSightPointIds(c.getString(iIndex));
//				iIndex = c.getColumnIndex("BackeSightHeight");
//				entity.setBackeSightHeight(c.getDouble(iIndex));
//				iIndex = c.getColumnIndex("CreateTime");
//				entity.setCreateTime(Timestamp.valueOf(c.getString(iIndex)));
//				iIndex = c.getColumnIndex("Info");
//				entity.setInfo(c.getString(iIndex));
//				if(list == null){
//					list = new ArrayList<StationInfo>();
//				}
//				list.add(entity);
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println("GetAllStation:" + e.getLocalizedMessage());
//		}
//		return list;
//	}
//
//	@Override
//	// �����վ��Ϣ
//	public boolean InsertStationInfo(StationInfo s) {
//		boolean result = false;
//		if(s == null){
//			return result;
//		}
//		String sql = "insert into StationInfoIndex(StationPointId,StationHeight,BackSightPointIds,BackeSightHeight," +
//				"CreateTime,Info) values(?,?,?,?,?,?)";
//		try {
//			Object[] obj = new Object[6];
//			obj[0] = s.getStationPointId();
//			obj[1] = s.getStationHeight();
//			obj[2] = s.getBackSightPointIds();
//			obj[3] = s.getBackeSightHeight();
//			obj[4] = s.getCreateTime();
//			obj[5] = s.getInfo();
//			db.execSQL(sql, obj);
//			result = true;
//			obj = null;
//		} catch (Exception e) {
//			// TODO: handle exception
//			result = false;
//			System.out.println("InsertStationInfo:" + e.getLocalizedMessage());
//		}
//		return result;
//	}
//
//	@Override
//	//������վ��Ϣ
//	public boolean UpdateStationInfo(StationInfo s) {
//		boolean result = false;
//		if(s == null){
//			return result;
//		}
//		String sql = "update StationInfoIndex set StationPointId=?,StationHeight=?,BackSightPointIds=?,BackeSightHeight=?," +
//				"CreateTime=?,Info=? where id = ?";
//		try {
//			Object[] obj = new Object[7];
//			obj[0] = s.getStationPointId();
//			obj[1] = s.getStationHeight();
//			obj[2] = s.getBackSightPointIds();
//			obj[3] = s.getBackeSightHeight();
//			obj[4] = s.getCreateTime();
//			obj[5] = s.getInfo();
//			obj[6] = s.getId();
//			db.execSQL(sql, obj);
//			result = true;
//			obj = null;
//		} catch (Exception e) {
//			// TODO: handle exception
//			result = false;
//			System.out.println("UpdateStationInfo:" + e.getLocalizedMessage());
//		}
//		return result;
//	}
//
//	@Override
//	//ɾ����վ��Ϣ
//	public boolean DeleteStationInfo(int id) {
//		boolean result = false;
//		String sql = "delete from StationInfoIndex where id=?";
//		try {
//			Object[] obj = new Object[1];
//			obj[0] = id;
//			db.execSQL(sql, obj);
//			result = true;
//			obj = null;
//		} catch (Exception e) {
//			// TODO: handle exception
//			result = false;
//			System.out.println("DeleteStationInfo:" + e.getLocalizedMessage());
//		}
//		return result;
//	}
}
