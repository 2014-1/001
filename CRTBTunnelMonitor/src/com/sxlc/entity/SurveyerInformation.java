package com.sxlc.entity;
/**
 *������Ա��Ϣʵ��
 *����ʱ�䣺2014-3-24����13:50:00
 *@author ����
 *@since JDK1.6
 *@version 1.0
 */
public class SurveyerInformation {
	private int id;
	private String SurveyerName;			//����Ա��
	private String CertificateID;			//���֤id
	private String Password;				//����
	private String Info;					//��ע
	private int ProjectID;					//������id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSurveyerName() {
		return SurveyerName;
	}
	public void setSurveyerName(String surveyerName) {
		SurveyerName = surveyerName;
	}
	public String getCertificateID() {
		return CertificateID;
	}
	public void setCertificateID(String certificateID) {
		CertificateID = certificateID;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getInfo() {
		return Info;
	}
	public void setInfo(String info) {
		Info = info;
	}
	public int getProjectID() {
		return ProjectID;
	}
	public void setProjectID(int projectID) {
		ProjectID = projectID;
	}
	
}
