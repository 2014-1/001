package com.sxlc.dao;
import java.util.List;

import com.sxlc.entity.SubsidenceCrossSectionInfo;

/**
 * �ر��³�����Dao
 *����ʱ�䣺2014-3-24����11:50:00
 *@author ����
 *@since JDK1.6
 *@version 1.0
 */
public interface SubsidenceCrossSectionDao {
	/**��ѯȫ��*/
	public List<SubsidenceCrossSectionInfo> SelectAllSection();
	/**�½���¼��*/
	public Boolean InsertSubsidenceCrossSection(SubsidenceCrossSectionInfo s);
	/**��ѯ��¼��*/
	public SubsidenceCrossSectionInfo SelectSubsidenceCrossSection(int id);
	/**ɾ����¼��*/
	public int DeleteSubsidenceCrossSection(int id);
	/**�༭��¼��*/
	public Boolean UpdateSubsidenceCrossSection(SubsidenceCrossSectionInfo s);
	public void GetSubsidenceCrossSectionList(List<SubsidenceCrossSectionInfo> lt);
}
