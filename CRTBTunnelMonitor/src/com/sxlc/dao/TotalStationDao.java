package com.sxlc.dao;

import java.util.List;

import com.sxlc.entity.TotalStationInfo;
import com.sxlc.entity.WorkInfos;

/**
 * ȫվ�����Ӳ�����Ϣ���ݿ�ӿ�
 *����ʱ�䣺2014-3-24����13:50:00
 *@author ����
 *@since JDK1.6
 *@version 1.0
 */
public interface TotalStationDao {
	/**��ѯȫ��ȫվ�����Ӳ�����Ϣ*/
	public List<TotalStationInfo> SelectAllTotalStation();
	/**�½�ȫվ�����Ӳ�����Ϣ*/
	public Boolean InsertTotalStation(TotalStationInfo s);
	/**��ѯȫվ�����Ӳ�����Ϣ*/
	public TotalStationInfo SelectTotalStation(int id);
	/**ɾ��ȫվ�����Ӳ�����Ϣ*/
	public Boolean DeleteTotalStation(int id);
	/**�༭ȫվ�����Ӳ�����Ϣ*/
	public Boolean UpdateTotalStation(TotalStationInfo s);
	void GetTotalStationList(List<TotalStationInfo> list);
}
