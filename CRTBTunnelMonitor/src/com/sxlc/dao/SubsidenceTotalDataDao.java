package com.sxlc.dao;

import java.util.List;

import com.sxlc.entity.SubsidenceTotalDataInfo;

/**
 * ���������¼�����ݿ�ӿ�
 *����ʱ�䣺2014-3-24����13:50:00
 *@author ����
 *@since JDK1.6
 *@version 1.0
 */
public interface SubsidenceTotalDataDao {
	/*������ѯ��¼��*/
	public List<SubsidenceTotalDataInfo> GetAllSubsidenceTotalData(int stationId,int sectionId,int rawsheetId,int type);
	
	/* ��Ӳ�����¼�� */
	public Boolean InsertSubsidenceTotalData(SubsidenceTotalDataInfo s);
	
	/*�޸Ĳ�����¼��*/
	public Boolean UpdateSubsidenceTotalData(SubsidenceTotalDataInfo s);
	
	/* ɾ��������¼�� */
	public Boolean DeleteSubsidenceTotalData(int id,int level,int type);
	
	/* ����id��ȡ����������¼ */
	public SubsidenceTotalDataInfo GetSubsidenceTotalData(int id,int type);
}
