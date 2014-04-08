package com.crtb.tunnelmonitor.dao;
import java.util.List;

import com.crtb.tunnelmonitor.entity.SubsidenceCrossSectionInfo;

/**
 * 地表下沉断面Dao
 */
public interface SubsidenceCrossSectionDao {
	/**查询全部*/
	public List<SubsidenceCrossSectionInfo> SelectAllSection();
	/**新建记录单*/
	public Boolean InsertSubsidenceCrossSection(SubsidenceCrossSectionInfo s);
	/**查询记录单*/
	public SubsidenceCrossSectionInfo SelectSubsidenceCrossSection(int id);
	/**删除记录单*/
	public int DeleteSubsidenceCrossSection(int id);
	/**编辑记录单*/
	public Boolean UpdateSubsidenceCrossSection(SubsidenceCrossSectionInfo s);
	public void GetSubsidenceCrossSectionList(List<SubsidenceCrossSectionInfo> lt);
}
