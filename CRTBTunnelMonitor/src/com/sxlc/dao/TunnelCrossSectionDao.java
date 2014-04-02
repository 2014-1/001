package com.sxlc.dao;

import java.util.List;

import com.sxlc.entity.TunnelCrossSectionInfo;
/**
 * ����ӿ�
 *����ʱ�䣺2014-3-21����9:53:52
 *@author ����
 *@since JDK1.6
 *@version 1.0
 */
public interface TunnelCrossSectionDao {
	/**��ѯȫ��*/
	public List<TunnelCrossSectionInfo> SectionAll();
	/**�½���¼��*/
	public Boolean InsertSection(TunnelCrossSectionInfo s);
	/**��ѯ��¼��*/
	public TunnelCrossSectionInfo SelectSection();
	/**ɾ����¼��*/
	public int DeleteSection(int id);
	/**�༭��¼��*/
	public void UpdateSection(TunnelCrossSectionInfo s);
	
	
	/**��ѯȫ��*/
	public List<TunnelCrossSectionInfo> SectiondibiaoAll();
	/**�½���¼��*/
	public Boolean InsertSectiondibiao(TunnelCrossSectionInfo s);
	/**��ѯ��¼��*/
	public TunnelCrossSectionInfo SelectSectiondibiao(TunnelCrossSectionInfo s);
	/**ɾ����¼��*/
	public void DeleteSectiondibiao(Double d);
	/**�༭��¼��*/
	public void UpdateSectiondibiao(TunnelCrossSectionInfo s);
	void GetTunnelCrossSectionList(List<TunnelCrossSectionInfo> lt);
}
