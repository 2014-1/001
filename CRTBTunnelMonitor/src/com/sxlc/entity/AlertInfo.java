package com.sxlc.entity;

import java.sql.Timestamp;

/**
 * Ԥ������ʵ��
 *����ʱ�䣺2014-3-24����13:50:00
 *@author ����
 *@since JDK1.6
 *@version 1.0
 */
public class AlertInfo {
	private int id;						//id
	private Timestamp SheetID;			//��¼��id
	private String CrossSectionID;		//����Ψһid
	private int PntType;				//�������
	private Timestamp AlertTime;		//Ԥ��ʱ��
	private int AlertLeverl;			//Ԥ���ȼ�
	private int Utype;					//��������
	private double UValue;				//������ֵ
	private double UMax;				//����������ֵ
	private String OriginalDataID;		//ԭʼ����id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getSheetID() {
		return SheetID;
	}
	public void setSheetID(Timestamp sheetID) {
		SheetID = sheetID;
	}
	public String getCrossSectionID() {
		return CrossSectionID;
	}
	public void setCrossSectionID(String crossSectionID) {
		CrossSectionID = crossSectionID;
	}
	public int getPntType() {
		return PntType;
	}
	public void setPntType(int pntType) {
		PntType = pntType;
	}
	public Timestamp getAlertTime() {
		return AlertTime;
	}
	public void setAlertTime(Timestamp alertTime) {
		AlertTime = alertTime;
	}
	public int getAlertLeverl() {
		return AlertLeverl;
	}
	public void setAlertLeverl(int alertLeverl) {
		AlertLeverl = alertLeverl;
	}
	public int getUtype() {
		return Utype;
	}
	public void setUtype(int utype) {
		Utype = utype;
	}
	public double getUValue() {
		return UValue;
	}
	public void setUValue(double uValue) {
		UValue = uValue;
	}
	public double getUMax() {
		return UMax;
	}
	public void setUMax(double uMax) {
		UMax = uMax;
	}
	public String getOriginalDataID() {
		return OriginalDataID;
	}
	public void setOriginalDataID(String originalDataID) {
		OriginalDataID = originalDataID;
	}
	
}
