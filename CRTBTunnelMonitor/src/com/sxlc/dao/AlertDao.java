package com.sxlc.dao;

import java.util.List;

import com.sxlc.entity.AlertInfo;

/**
 * Ԥ���������ݿ�ӿ�
 *����ʱ�䣺2014-3-24����13:50:00
 *@author ����
 *@since JDK1.6
 *@version 1.0
 */
public interface AlertDao {
	/**��ѯȫ��*/
	public List<AlertInfo> SelectAllAlert();
	/**�½�Ԥ��*/
	public Boolean InsertAlert(AlertInfo s);
	/**��ѯԤ��*/
	public AlertInfo SelectAlert(int id);
	/**ɾ��Ԥ��*/
	public Boolean DeleteAlert(int id);
	/**�༭Ԥ��*/
	public Boolean UpdateAlert(AlertInfo s);
}
