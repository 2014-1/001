package com.sxlc.entity;

import java.sql.Timestamp;


/**
 * �ر��³�����
 *����ʱ�䣺2014-3-24����11:50:00
 *@author ����
 *@since JDK1.6
 *@version 1.0
 */
public class SubsidenceCrossSectionInfo {
	private String ChainageName;		//��������
	private int id;				//��¼id
	private Double Chainage;	//��������ֵ
	private Timestamp InbuiltTime;		//����ʱ��
	private int Width;					//���
	private String SurveyPnts;			//�����
	private String Info;				//��ע
	private String ChainagePrefix;		//����ǰ׺
	private float DBU0;					//�ر�uoֵ
	private Timestamp DBU0Time;			//�޲��޸�ʱ��
	private String DBU0Description;		//�������ޱ�ע
	private float DBLimitVelocity;		//�ر��³�����
	private String Lithologic;			//����
	private float Layvalue;				//����ֵ
	private String Rockgrade;			//Χ�Ҽ���
	private boolean bUse;
	
	public boolean isbUse() {
		return bUse;
	}
	public void setbUse(boolean bUse) {
		this.bUse = bUse;
	}
	public int getId() {
		return id;
	}
	public String getChainageName() {
		return ChainageName;
	}
	public void setChainageName(String chainageName) {
		ChainageName = chainageName;
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
	public Timestamp getInbuiltTime() {
		return InbuiltTime;
	}
	public void setInbuiltTime(Timestamp inbuiltTime) {
		InbuiltTime = inbuiltTime;
	}
	public int getWidth() {
		return Width;
	}
	public void setWidth(int width) {
		Width = width;
	}
	public String getSurveyPnts() {
		return SurveyPnts;
	}
	public void setSurveyPnts(String surveyPnts) {
		SurveyPnts = surveyPnts;
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
	public float getDBU0() {
		return DBU0;
	}
	public void setDBU0(float dBU0) {
		DBU0 = dBU0;
	}
	public Timestamp getDBU0Time() {
		return DBU0Time;
	}
	public void setDBU0Time(Timestamp dBU0Time) {
		DBU0Time = dBU0Time;
	}
	public String getDBU0Description() {
		return DBU0Description;
	}
	public void setDBU0Description(String dBU0Description) {
		DBU0Description = dBU0Description;
	}
	public float getDBLimitVelocity() {
		return DBLimitVelocity;
	}
	public void setDBLimitVelocity(float dBLimitVelocity) {
		DBLimitVelocity = dBLimitVelocity;
	}
	public String getLithologic() {
		return Lithologic;
	}
	public void setLithologic(String lithologic) {
		Lithologic = lithologic;
	}
	public float getLayvalue() {
		return Layvalue;
	}
	public void setLayvalue(float layvalue) {
		Layvalue = layvalue;
	}
	public String getRockgrade() {
		return Rockgrade;
	}
	public void setRockgrade(String rockgrade) {
		Rockgrade = rockgrade;
	}
}
