package com.sxlc.dao;

import java.util.List;

import com.sxlc.entity.TunnelCrossSectionExInfo;

/**
 * ����������ݿ�ӿ�
 *����ʱ�䣺2014-3-24����13:50:00
 *@author ����
 *@since JDK1.6
 *@version 1.0
 */

public interface TunnelCrossSectionExDao {
	/* �鿴����  */
	public List<TunnelCrossSectionExInfo> GetAllTunnelCrossSection();
	
	/* ���Ӳ�����¼ */
	public Boolean InsertTunnelCrossSection(TunnelCrossSectionExInfo t);
	
	/* �޸Ĳ�����¼ */
	public Boolean UpdateTunnelCrossSection(TunnelCrossSectionExInfo t);
	
	/* ɾ��������¼ */
	public Boolean DeleteTunnelCrossSection(int id);
	
	/* �鿴������¼ */
	public TunnelCrossSectionExInfo GetTunnelCrossSectionExInfo(int id);
}
