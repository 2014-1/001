package com.sxlc.dao;

import java.util.List;

import com.sxlc.entity.ControlPointsInfo;

/**
 * ���Ƶ����ݿ�ӿ�
 *����ʱ�䣺2014-3-24����13:50:00
 *@author ����
 *@since JDK1.6
 *@version 1.0
 */
public interface ControlPointsDao {
	/* �鿴������վ��Ϣ */
	public List<ControlPointsInfo> GetAllStation();
	
	/* �����վ��Ϣ */
	public boolean InsertStationInfo(ControlPointsInfo s);
	
	/* �޸���վ��Ϣ */
	public boolean UpdateStationInfo(ControlPointsInfo s);
	
	/* ��ȡ������վ��Ϣ */
	public ControlPointsInfo GetControlPoints(int id);
	
	/* ɾ����վ��Ϣ */
	public boolean DeleteStationInfo(int id);

	void GetControlPointsList(List<ControlPointsInfo> list);
}
