package com.sxlc.entity;
/**
 * ����ڶ������
 *����ʱ�䣺2014-3-21����6:16:22
 *@author ����
 *@since JDK1.6
 *@version 1.0
 */
public class TunnelCrossSectionInfo {

	private String ChainageName;		//��������
	private String sExcavateMethod;			//ʩ������
	private int id;						//����
	private Double Chainage;			//�������ֵ
	private String InBuiltTime;			//����ʱ��
	private Float Width;				//������
	private int ExcavateMethod;			//ʩ������
	private String SurveyPntName;		//�����
	private String Info;				//��ע
	private String ChainagePrefix;		//���ǰ׺
	private Float GDU0;					//����uoֵ
	private Float GDVelocity;			//���������³�����
	private String GDU0Time;			//�����޲��޸�ʱ��
	private String GDU0Description;		//�������ޱ�ע
	private Float SLU0;					//����uoֵ
	private Float SLLimitVelocity;		//���������³�����
	private String SLU0Time;			//�����޲��޸�ʱ��
	private String SLU0Description;		//�������ޱ�ע
	private String Lithologic;			//����
	private Float LAYVALUE;				//����ֵ
	private String ROCKGRADE;			//Χ�Ҽ���
	private boolean bUse = false;
	public boolean isbUse() {
		return bUse;
	}
	public void setbUse(boolean bUse) {
		this.bUse = bUse;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getChainage() {
		return Chainage;
	}
	public void setChainage(Double chainage) {
		Chainage = chainage;
	}
	public String getInBuiltTime() {
		return InBuiltTime;
	}
	public void setInBuiltTime(String inBuiltTime) {
		InBuiltTime = inBuiltTime;
	}
	public Float getWidth() {
		return Width;
	}
	public void setWidth(Float width) {
		Width = width;
	}
	public int getExcavateMethod() {
		return ExcavateMethod;
	}
	public void setExcavateMethod(int excavateMethod) {
		ExcavateMethod = excavateMethod;
	}
	public String getSurveyPntName() {
		return SurveyPntName;
	}
	public void setSurveyPntName(String surveyPntName) {
		SurveyPntName = surveyPntName;
	}
	public String getInfo() {
		return Info;
	}
	public void setInfo(String info) {
		Info = info;
	}
	public String getChainagePrefix() {
		return ChainagePrefix;
	}
	public void setChainagePrefix(String chainagePrefix) {
		ChainagePrefix = chainagePrefix;
	}
	public Float getGDU0() {
		return GDU0;
	}
	public void setGDU0(Float gDU0) {
		GDU0 = gDU0;
	}
	public Float getGDVelocity() {
		return GDVelocity;
	}
	public void setGDVelocity(Float gDVelocity) {
		GDVelocity = gDVelocity;
	}
	public String getGDU0Time() {
		return GDU0Time;
	}
	public void setGDU0Time(String gDU0Time) {
		GDU0Time = gDU0Time;
	}
	public String getGDU0Description() {
		return GDU0Description;
	}
	public void setGDU0Description(String gDU0Description) {
		GDU0Description = gDU0Description;
	}
	public Float getSLU0() {
		return SLU0;
	}
	public void setSLU0(Float sLU0) {
		SLU0 = sLU0;
	}
	public Float getSLLimitVelocity() {
		return SLLimitVelocity;
	}
	public void setSLLimitVelocity(Float sLLimitVelocity) {
		SLLimitVelocity = sLLimitVelocity;
	}
	public String getSLU0Time() {
		return SLU0Time;
	}
	public void setSLU0Time(String sLU0Time) {
		SLU0Time = sLU0Time;
	}
	public String getSLU0Description() {
		return SLU0Description;
	}
	public void setSLU0Description(String sLU0Description) {
		SLU0Description = sLU0Description;
	}
	public String getLithologic() {
		return Lithologic;
	}
	public void setLithologic(String lithologic) {
		Lithologic = lithologic;
	}
	public Float getLAYVALUE() {
		return LAYVALUE;
	}
	public void setLAYVALUE(Float lAYVALUE) {
		LAYVALUE = lAYVALUE;
	}
	public String getROCKGRADE() {
		return ROCKGRADE;
	}
	public void setROCKGRADE(String rOCKGRADE) {
		ROCKGRADE = rOCKGRADE;
	}
	public String getChainageName() {
		return ChainageName;
	}
	public void setChainageName(String chainageName) {
		ChainageName = chainageName;
	}
	public String getsExcavateMethod() {
		return sExcavateMethod;
	}
	public void setsExcavateMethod(String sExcavateMethod) {
		this.sExcavateMethod = sExcavateMethod;
	}
	
}
