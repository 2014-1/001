package com.sxlc.entity;

import java.sql.Timestamp;

/**
 * ��վ��Ϣʵ��
 *����ʱ�䣺2014-3-24����13:50:00
 *@author ����
 *@since JDK1.6
 *@version 1.0
 */
public class StationInfo {
	private int id;						//id
	private int StationPointId;			// ��վ��id
	private double StationHeight;		// ��վ�߶�ֵ
	private String BackSightPointIds;	// ���ӵ�id
	private double BackeSightHeight;	// ���Ӹ߶�ֵ
	private Timestamp CreateTime;		// ��վ����
	private String Info;				// ��ע
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStationPointId() {
		return StationPointId;
	}
	public void setStationPointId(int stationPointId) {
		StationPointId = stationPointId;
	}
	public double getStationHeight() {
		return StationHeight;
	}
	public void setStationHeight(double stationHeight) {
		StationHeight = stationHeight;
	}
	public String getBackSightPointIds() {
		return BackSightPointIds;
	}
	public void setBackSightPointIds(String backSightPointIds) {
		BackSightPointIds = backSightPointIds;
	}
	public double getBackeSightHeight() {
		return BackeSightHeight;
	}
	public void setBackeSightHeight(double backeSightHeight) {
		BackeSightHeight = backeSightHeight;
	}
	public Timestamp getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(Timestamp createTime) {
		CreateTime = createTime;
	}
	public String getInfo() {
		return Info;
	}
	public void setInfo(String info) {
		Info = info;
	}
}
