package com.sxlc.dao;


import java.util.List;

import com.sxlc.entity.RecordInfo;
import com.sxlc.entity.WorkInfos;
/**
 * ��¼���Ľӿ�
 *����ʱ�䣺2014-3-21����1:56:59
 *@author ����
 *@since JDK1.6
 *@version 1.0
 */
public interface RecordDao{
	/**��ѯȫ��*/
	public List<RecordInfo> RecordAll(int type);
	/**�½���¼��*/
	public Boolean AddRecord(String type ,RecordInfo r);
	/**��ѯ��¼��*/
	public void SelectRecord(RecordInfo r);
	/**ɾ����¼��*/
	public void DeleteRecord(String name);
	/**�༭��¼��*/
	public void CompileRecord(RecordInfo r);
	public void GetRecordList(int type,WorkInfos w,List<RecordInfo> list);
	RecordInfo SelectRecord(int id);
	Boolean AddRecord(RecordInfo r);
	Boolean DeleteRecord(int id);
	Boolean UpdateRecord(RecordInfo r);
}