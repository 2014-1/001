package com.sxlc.dao;

import java.util.List;

import com.sxlc.entity.SectionInfo;
/**
 * ����ӿ�
 *����ʱ�䣺2014-3-21����9:53:52
 *@author ����
 *@since JDK1.6
 *@version 1.0
 */
public interface SectionDao {

	/**��ѯȫ��*/
	public List<SectionInfo> SectionAll();
	/**�½���¼��*/
	public Boolean InsertSection(SectionInfo s);
	/**��ѯ��¼��*/
	public SectionInfo SelectSection();
	/**ɾ����¼��*/
	public void DeleteSection(Double d);
	/**�༭��¼��*/
	public void UpdateSection(SectionInfo s);
	
	
	/**��ѯȫ��*/
	public List<SectionInfo> SectiondibiaoAll();
	/**�½���¼��*/
	public Boolean InsertSectiondibiao(SectionInfo s);
	/**��ѯ��¼��*/
	public SectionInfo SelectSectiondibiao(SectionInfo s);
	/**ɾ����¼��*/
	public void DeleteSectiondibiao(Double d);
	/**�༭��¼��*/
	public void UpdateSectiondibiao(SectionInfo s);
	
	
}
