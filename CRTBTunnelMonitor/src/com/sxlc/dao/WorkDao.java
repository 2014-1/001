package com.sxlc.dao;

import java.util.List;

import com.sxlc.entity.WorkInfos;
import com.sxlc.entity.list_infos;
/**
 * ������Ľӿ�
 *����ʱ�䣺2014-3-21����9:23:23
 *@author ����
 *@since JDK1.6
 *@version 1.0
 */
public interface WorkDao {

	/**��ѯȫ��������*/
	public List<list_infos> SelectWork();
	public void GetWorkList(List<WorkInfos> lt);
	/**��ѯȫ����������Ϣ*/
	public WorkInfos SelectWorkMsg();
	/**�½�����*/
	public Boolean InsertWork(WorkInfos W);
	/**ɾ������*/
	public void DeleteWork(String name);
	/**ɾ������*/
	public void UpdateWork(WorkInfos w);
}
