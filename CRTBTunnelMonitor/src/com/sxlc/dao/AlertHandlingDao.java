package com.sxlc.dao;

import java.util.List;

import com.sxlc.entity.AlertHandlingInfo;


/**
 * Ԥ����־���ݿ�ӿ�
 *����ʱ�䣺2014-3-24����13:50:00
 *@author ����
 *@since JDK1.6
 *@version 1.0
 */
public interface AlertHandlingDao {
	/**��ѯȫ��*/
	public List<AlertHandlingInfo> SelectAllAlertHandling();
	/**�½�Ԥ��*/
	public Boolean InsertAlertHandling(AlertHandlingInfo s);
}
