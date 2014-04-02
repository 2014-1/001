package com.sxlc.entity;

import java.sql.Timestamp;

import android.R.string;

/**
 * ���������¼��ʵ��
 *����ʱ�䣺2014-3-24����13:50:00
 *@author ����
 *@since JDK1.6
 *@version 1.0
 */
public class SubsidenceTotalDataInfo {
	private int type;			//���� 0�������¼��  1���ر��³���¼��
	private int id;				//id
	private int StationId;		//��վid
	private int ChainageId;		//�������id
	private int SheetId;		//��¼��id
	private String Coordinate;	//�������
	private String PntType;		//�������
	private Timestamp SurveyTime;	//����ʱ��
	private String Info;		//��ע
	private short MEASNo;		//�ڼ��β���
	private int SurveyorID;		//������Աid
	private short DataStatus;	//�쳣���ݱ�ʶ
	private float DataCorrection;	//�쳣��������ֵ
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStationId() {
		return StationId;
	}
	public void setStationId(int stationId) {
		StationId = stationId;
	}
	public int getChainageId() {
		return ChainageId;
	}
	public void setChainageId(int chainageId) {
		ChainageId = chainageId;
	}
	public int getSheetId() {
		return SheetId;
	}
	public void setSheetId(int sheetId) {
		SheetId = sheetId;
	}
	public String getCoordinate() {
		return Coordinate;
	}
	public void setCoordinate(String coordinate) {
		Coordinate = coordinate;
	}
	public String getPntType() {
		return PntType;
	}
	public void setPntType(String pntType) {
		PntType = pntType;
	}
	public Timestamp getSurveyTime() {
		return SurveyTime;
	}
	public void setSurveyTime(Timestamp surveyTime) {
		SurveyTime = surveyTime;
	}
	public String getInfo() {
		return Info;
	}
	public void setInfo(String info) {
		Info = info;
	}
	public short getMEASNo() {
		return MEASNo;
	}
	public void setMEASNo(short mEASNo) {
		MEASNo = mEASNo;
	}
	public int getSurveyorID() {
		return SurveyorID;
	}
	public void setSurveyorID(int surveyorID) {
		SurveyorID = surveyorID;
	}
	public short getDataStatus() {
		return DataStatus;
	}
	public void setDataStatus(short dataStatus) {
		DataStatus = dataStatus;
	}
	public float getDataCorrection() {
		return DataCorrection;
	}
	public void setDataCorrection(float dataCorrection) {
		DataCorrection = dataCorrection;
	}
	
}
