package com.sxlc.entity;

import java.sql.Timestamp;

/**
 * ���������Ϣ
 *����ʱ�䣺2014-3-24����13:50:00
 *@author ����
 *@since JDK1.6
 *@version 1.0
 */

public class TunnelCrossSectionExInfo {
	private int id;
	private String zonecode;		// ZONECODE ��������
	private String sitecode;		// SITECODE �������
	private String sectname;		// SECTNAME ������
	private String sectcode;		// SECTCODE �������
	private String sectkilo;		// SECTKILO �������ֵ
	private String method;			// METHOD ���ڷ���
	private float width;			// ���
	private float movevalue_uo;		// MOVEVALUE_U0 uoֵ
	private Timestamp updateDate;	// UPDATEDATE uo����ʱ��
	private String remark_uo;		// REMARK_U0 uo��ע
	private String holename;		// HOLENAME ��������
	private String holestartkilo;	// HOLESTARTKILO ���������
	private String innercode;		// INNERCODES ��㼯��
	private Timestamp layDate;		// LAYTIME ����ʱ��
	private int upload;				// UPLOAD �ϴ���ʶ
	private String description;		// DESCRIPTION ��ע
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getZonecode() {
		return zonecode;
	}
	public void setZonecode(String zonecode) {
		this.zonecode = zonecode;
	}
	public String getSitecode() {
		return sitecode;
	}
	public void setSitecode(String sitecode) {
		this.sitecode = sitecode;
	}
	public String getSectname() {
		return sectname;
	}
	public void setSectname(String sectname) {
		this.sectname = sectname;
	}
	public String getSectcode() {
		return sectcode;
	}
	public void setSectcode(String sectcode) {
		this.sectcode = sectcode;
	}
	public String getSectkilo() {
		return sectkilo;
	}
	public void setSectkilo(String sectkilo) {
		this.sectkilo = sectkilo;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	public float getMovevalue_uo() {
		return movevalue_uo;
	}
	public void setMovevalue_uo(float movevalue_uo) {
		this.movevalue_uo = movevalue_uo;
	}
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	public String getRemark_uo() {
		return remark_uo;
	}
	public void setRemark_uo(String remark_uo) {
		this.remark_uo = remark_uo;
	}
	public String getHolename() {
		return holename;
	}
	public void setHolename(String holename) {
		this.holename = holename;
	}
	public String getHolestartkilo() {
		return holestartkilo;
	}
	public void setHolestartkilo(String holestartkilo) {
		this.holestartkilo = holestartkilo;
	}
	public String getInnercode() {
		return innercode;
	}
	public void setInnercode(String innercode) {
		this.innercode = innercode;
	}
	public Timestamp getLayDate() {
		return layDate;
	}
	public void setLayDate(Timestamp layDate) {
		this.layDate = layDate;
	}
	public int getUpload() {
		return upload;
	}
	public void setUpload(int upload) {
		this.upload = upload;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
