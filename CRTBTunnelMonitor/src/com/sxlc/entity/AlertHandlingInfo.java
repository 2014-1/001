package com.sxlc.entity;

import java.sql.Timestamp;

/**
 * Ԥ����־ʵ��
 *����ʱ�䣺2014-3-24����13:50:00
 *@author ����
 *@since JDK1.6
 *@version 1.0
 */
public class AlertHandlingInfo {
	private int id;						//id
	private int AlertID;				//Ԥ��id
	private int Handling;				//��������
	private Timestamp HandlingTime;		//����ʱ��
	private String DuePerson;			//������
	private int AlertStatus;			//Ԥ����Ϣ״̬
	private int HandlingInfo;		//��ע
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAlertID() {
		return AlertID;
	}
	public void setAlertID(int alertID) {
		AlertID = alertID;
	}
	public int getHandling() {
		return Handling;
	}
	public void setHandling(int handling) {
		Handling = handling;
	}
	public Timestamp getHandlingTime() {
		return HandlingTime;
	}
	public void setHandlingTime(Timestamp handlingTime) {
		HandlingTime = handlingTime;
	}
	public String getDuePerson() {
		return DuePerson;
	}
	public void setDuePerson(String duePerson) {
		DuePerson = duePerson;
	}
	public int getAlertStatus() {
		return AlertStatus;
	}
	public void setAlertStatus(int alertStatus) {
		AlertStatus = alertStatus;
	}
	public int getHandlingInfo() {
		return HandlingInfo;
	}
	public void setHandlingInfo(int handlingInfo) {
		HandlingInfo = handlingInfo;
	}
	
}
