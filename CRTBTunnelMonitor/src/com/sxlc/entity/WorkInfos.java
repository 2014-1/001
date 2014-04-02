package com.sxlc.entity;

import java.util.ArrayList;
import java.util.List;

import com.sxlc.dao.impl.ControlPointsDaoImpl;
import com.sxlc.dao.impl.RecordDaoImpl;
import com.sxlc.dao.impl.SubsidenceCrossSectionDaoImpl;
import com.sxlc.dao.impl.TotalStationDaoImpl;
import com.sxlc.dao.impl.TunnelCrossSectionDaoImpl;

import android.R.integer;
import android.content.Context;

/**
 * ����������ʵ����
 * ����ʱ�䣺2014-3-21����2:55:32
 * 
 * @author ����
 * @since JDK1.6
 * @version 1.0
 */
public class WorkInfos {

	/** ���� */
	private String ProjectName;
	/** ʱ�� */
	private String CreateTime;
	/** ���ǰ׺ */
	private String ChainagePrefix;
	/** ��ʼ��� */
	private Double StartChainage;
	/** ������� */
	private Double EndChainage;
	/** ʩ����λ���� */
	private String ConstructionFirm;
	/** �������������޲� */
	private Float GDLimitVelocity;
	/** ���� �ۼƳ������޲�*/
	private Float GDLimitTotalSettlement;
	/** ���� ���α�������*/
	private Float SLLimitVelocity;
	/** ���� �ۼƱ����޲�*/
	private Float SLLimitTotalSettlement;
	/** �ر��³� ��������*/
	private Float DBLimitVelocity;
	/**�ر��³� �ۼ��³��޲�*/
	private String DBLimitTotalSettlement;
	/** ��ע */
	private String info;
	private String LastOpenTime;

	private List<TunnelCrossSectionInfo> tcsiList = null;
	private List<SubsidenceCrossSectionInfo> scsiList = null;
	private List<RecordInfo> tcsirecordList = null,scsirecordList = null;	
	private List<TotalStationInfo> tsList = null;
	private List<ControlPointsInfo> cpList = null;
	
	public void InitData(Context c) {
		if(tcsiList == null){
			tcsiList = new ArrayList<TunnelCrossSectionInfo>();
		}
		else {
			tcsiList.clear();
		}
		TunnelCrossSectionDaoImpl impl = new TunnelCrossSectionDaoImpl(c,ProjectName);
		impl.GetTunnelCrossSectionList(tcsiList);
		
		if(scsiList == null){
			scsiList = new ArrayList<SubsidenceCrossSectionInfo>();
		}
		else {
			scsiList.clear();
		}
		SubsidenceCrossSectionDaoImpl impl1 = new SubsidenceCrossSectionDaoImpl(c,ProjectName);
		impl1.GetSubsidenceCrossSectionList(scsiList);
		

		if(tcsirecordList == null){
			tcsirecordList = new ArrayList<RecordInfo>();
		}
		else {
			tcsirecordList.clear();
		}
		RecordDaoImpl imp2 = new RecordDaoImpl(c,ProjectName);
		imp2.GetRecordList(1,this, tcsirecordList);
		if(scsirecordList == null){
			scsirecordList = new ArrayList<RecordInfo>();
		}
		else {
			scsirecordList.clear();
		}
		imp2.GetRecordList(2,this, scsirecordList);

		if(tsList == null){
			tsList = new ArrayList<TotalStationInfo>();
		}
		else {
			tsList.clear();
		}
		TotalStationDaoImpl impl3 = new TotalStationDaoImpl(c,ProjectName);
		impl3.GetTotalStationList(tsList);

		if(cpList == null){
			cpList = new ArrayList<ControlPointsInfo>();
		}
		else {
			cpList.clear();
		}
		ControlPointsDaoImpl impl4 = new ControlPointsDaoImpl(c,ProjectName);
		impl4.GetControlPointsList(cpList);
	}
	
	public List<ControlPointsInfo> getCpList() {
		return cpList;
	}
	public void setCpList(List<ControlPointsInfo> cpList) {
		this.cpList = cpList;
	}
	public List<TotalStationInfo> getTsList() {
		return tsList;
	}
	public void setTsList(List<TotalStationInfo> tsList) {
		this.tsList = tsList;
	}
	public List<RecordInfo> getTcsirecordList() {
		return tcsirecordList;
	}
	public void setTcsirecordList(List<RecordInfo> tcsirecordList) {
		this.tcsirecordList = tcsirecordList;
	}
	public List<RecordInfo> getScsirecordList() {
		return scsirecordList;
	}
	public void setScsirecordList(List<RecordInfo> scsirecordList) {
		this.scsirecordList = scsirecordList;
	}
	public List<SubsidenceCrossSectionInfo> getScsiList() {
		return scsiList;
	}
	public void setScsiList(List<SubsidenceCrossSectionInfo> scsiList) {
		this.scsiList = scsiList;
	}
	public String getLastOpenTime() {
		return LastOpenTime;
	}
	public void setLastOpenTime(String lastOpenTime) {
		LastOpenTime = lastOpenTime;
	}
	/**�޲�ʱ��*/
	private String LimitedTotalSubsidenceTime;
	
	
	public String getLimitedTotalSubsidenceTime() {
		return LimitedTotalSubsidenceTime;
	}
	public void setLimitedTotalSubsidenceTime(String limitedTotalSubsidenceTime) {
		LimitedTotalSubsidenceTime = limitedTotalSubsidenceTime;
	}
	
	public String getProjectName() {
		return ProjectName;
	}
	public void setProjectName(String projectName) {
		ProjectName = projectName;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getChainagePrefix() {
		return ChainagePrefix;
	}
	public void setChainagePrefix(String chainagePrefix) {
		ChainagePrefix = chainagePrefix;
	}
	public Double getStartChainage() {
		return StartChainage;
	}
	public void setStartChainage(Double startChainage) {
		StartChainage = startChainage;
	}
	public Double getEndChainage() {
		return EndChainage;
	}
	public void setEndChainage(Double endChainage) {
		EndChainage = endChainage;
	}
	public String getConstructionFirm() {
		return ConstructionFirm;
	}
	public void setConstructionFirm(String constructionFirm) {
		ConstructionFirm = constructionFirm;
	}
	public Float getGDLimitVelocity() {
		return GDLimitVelocity;
	}
	public void setGDLimitVelocity(Float gDLimitVelocity) {
		GDLimitVelocity = gDLimitVelocity;
	}
	public Float getGDLimitTotalSettlement() {
		return GDLimitTotalSettlement;
	}
	public void setGDLimitTotalSettlement(Float gDLimitTotalSettlement) {
		GDLimitTotalSettlement = gDLimitTotalSettlement;
	}
	public Float getSLLimitVelocity() {
		return SLLimitVelocity;
	}
	public void setSLLimitVelocity(Float sLLimitVelocity) {
		SLLimitVelocity = sLLimitVelocity;
	}
	public Float getSLLimitTotalSettlement() {
		return SLLimitTotalSettlement;
	}
	public void setSLLimitTotalSettlement(Float sLLimitTotalSettlement) {
		SLLimitTotalSettlement = sLLimitTotalSettlement;
	}
	public Float getDBLimitVelocity() {
		return DBLimitVelocity;
	}
	public void setDBLimitVelocity(Float dBLimitVelocity) {
		DBLimitVelocity = dBLimitVelocity;
	}
	public String getDBLimitTotalSettlement() {
		return DBLimitTotalSettlement;
	}
	public void setDBLimitTotalSettlement(String dBLimitTotalSettlement) {
		DBLimitTotalSettlement = dBLimitTotalSettlement;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public void SetTunnelCrossSectionInfoList(List<TunnelCrossSectionInfo> Value)
	{
		tcsiList = Value;
	}
	public List<TunnelCrossSectionInfo> GetTunnelCrossSectionInfoList()
	{
		return tcsiList;
	}
	public void UpdateTunnelCrossSectionInfo(TunnelCrossSectionInfo Value)
	{
		if(tcsiList == null)
		{
			return;
		}
		for(int i=0;i<tcsiList.size();i++)
		{
			TunnelCrossSectionInfo tmp = tcsiList.get(i);
			if(tmp.getChainage().equals(Value.getChainage()))
			{
				tcsiList.set(i, Value);
				break;
			}
		}
	}
	public void DelTunnelCrossSectionInfo(TunnelCrossSectionInfo Value)
	{
		if(tcsiList == null)
		{
			return;
		}
		for(int i=0;i<tcsiList.size();i++)
		{
			TunnelCrossSectionInfo tmp = tcsiList.get(i);
			if(tmp.getChainage().equals(Value.getChainage()))
			{
				tcsiList.remove(i);
				break;
			}
		}
	}
	public void UpdateSubsidenceCrossSectionInfo(SubsidenceCrossSectionInfo Value)
	{
		if(scsiList == null)
		{
			return;
		}
		for(int i=0;i<scsiList.size();i++)
		{
			SubsidenceCrossSectionInfo tmp = scsiList.get(i);
			if(tmp.getChainage().equals(Value.getChainage()))
			{
				scsiList.set(i, Value);
				break;
			}
		}
	}
	public void DelSubsidenceCrossSectionInfo(SubsidenceCrossSectionInfo Value)
	{
		if(scsiList == null)
		{
			return;
		}
		for(int i=0;i<scsiList.size();i++)
		{
			SubsidenceCrossSectionInfo tmp = scsiList.get(i);
			if(tmp.getChainage().equals(Value.getChainage()))
			{
				scsiList.remove(i);
				break;
			}
		}
	}
	public void UpdateRecordInfo(int iType, RecordInfo Value)
	{
		List<RecordInfo> tmpList = null;
		if (iType == 1) {
			tmpList = tcsirecordList;
		}
		else {
			tmpList = scsirecordList;
		}
		if(tmpList == null)
		{
			return;
		}
		for(int i=0;i<tmpList.size();i++)
		{
			if(tmpList.get(i).getId() == Value.getId())
			{
				tmpList.set(i, Value);
				break;
			}
		}
	}
	public void UpdateTotalStationInfo(TotalStationInfo Value)
	{
		if(tsList == null)
		{
			return;
		}
		for(int i=0;i<tsList.size();i++)
		{
			if(tsList.get(i).getId() == Value.getId())
			{
				tsList.set(i, Value);
				break;
			}
		}
	}
	public void UpdateContrlPointsInfo(ControlPointsInfo Value)
	{
		if(cpList == null)
		{
			return;
		}
		for(int i=0;i<cpList.size();i++)
		{
			if(cpList.get(i).getId() == Value.getId())
			{
				cpList.set(i, Value);
				break;
			}
		}
	}
}
