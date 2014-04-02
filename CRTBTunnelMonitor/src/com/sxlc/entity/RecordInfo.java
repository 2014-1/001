package com.sxlc.entity;

import java.sql.Timestamp;import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.sxlc.db.DTMSDataCateGory;

/**
 * ��¼�����ʵ����
 *����ʱ�䣺2014-3-21����1:05:21
 *@author ����
 *@since JDK1.6
 *@version 1.0
 */
public class RecordInfo implements Parcelable{
	private String ChainageName;		//��������
	private int id;
	private int CrossSectionType;					/**����*/
	private Timestamp CreateTime;					/**Ĭ�ϵ�ǰʱ��*/
	private String Info;							/**��Ϣ*/
	private Double Facedk;							/**�����¼�������*/
	private String Facedescription;					/**ʩ�����*/
	private double Temperature;						/**�¶�ֵ*/
	private String CrossSectionIDs;					/**����id����*/
	//private List<SubsidenceTotalDataInfo> sectionlist=null;
	private boolean bUse;
	
	public boolean isbUse() {
		return bUse;
	}
	public void setbUse(boolean bUse) {
		this.bUse = bUse;
	}
	public String getChainageName() {
		return ChainageName;
	}
	public void setChainageName(String chainageName) {
		ChainageName = chainageName;
	}
//	public List<SubsidenceTotalDataInfo> getSectionlist() {
//		return sectionlist;
//	}
//	public void setSectionlist(List<SubsidenceTotalDataInfo> sectionlist) {
//		this.sectionlist = sectionlist;
//	}
	public int getId() {
		return id;
	}
	public int getCrossSectionType() {
		return CrossSectionType;
	}
	public void setCrossSectionType(int crossSectionType) {
		CrossSectionType = crossSectionType;
	}
	public Timestamp getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(Timestamp createTime) {
		CreateTime = createTime;
	}
	public Double getFacedk() {
		return Facedk;
	}
	public void setFacedk(Double facedk) {
		Facedk = facedk;
	}
	public String getFacedescription() {
		return Facedescription;
	}
	public void setFacedescription(String facedescription) {
		Facedescription = facedescription;
	}
	public double getTemperature() {
		return Temperature;
	}
	public void setTemperature(double temperature) {
		Temperature = temperature;
	}
	public void setId(int id) {
		this.id = id;
	}
	//	public void setCreateTime(String createTime) {
//		CreateTime = createTime;
//	}
//	public String getTunnelFaceChainage() {
//		return TunnelFaceChainage;
//	}
//	public void setTunnelFaceChainage(String tunnelFaceChainage) {
//		TunnelFaceChainage = tunnelFaceChainage;
//	}
//	public String getTemperature() {
//		return Temperature;
//	}
//	public void setTemperature(String temperature) {
//		Temperature = temperature;
//	}
//	public List<Integer> getCrossSectionIds() {
//		return CrossSectionIds;
//	}
//	public void setCrossSectionIds(List<Integer> crossSectionIds) {
//		CrossSectionIds = crossSectionIds;
//	}
//	public String getConstructionProcedure() {
//		return ConstructionProcedure;
//	}
//	public void setConstructionProcedure(String constructionProcedure) {
//		ConstructionProcedure = constructionProcedure;
//	}
//	public DTMSDataCateGory getCateGory() {
//		return CateGory;
//	}
//	public void setCateGory(DTMSDataCateGory cateGory) {
//		CateGory = cateGory;
//	}
	public String getInfo() {
		return Info;
	}
	public void setInfo(String info) {
		Info = info;
	}
	public String getCrossSectionIDs() {
		return CrossSectionIDs;
	}
	public void setCrossSectionIDs(String crossSectionIDs) {
		CrossSectionIDs = crossSectionIDs;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		// TODO Auto-generated method stub
		arg0.writeString(ChainageName);
		arg0.writeInt(id);
		arg0.writeInt(CrossSectionType);
		arg0.writeString(CreateTime.toString());
		arg0.writeString(Info);
		arg0.writeDouble(Facedk);
		arg0.writeString(Facedescription);
		arg0.writeDouble(Temperature);
		arg0.writeString(CrossSectionIDs);
		//arg0.writeList(sectionlist);
		arg0.writeBooleanArray(new boolean[]{bUse});
	}	
    // ʵ��Parcelable�ӿڵ������У�������һ��ʵ����Parcelable.Creator�ӿڵľ�̬������Ա�ֶΣ�
    // �����������ֱ���ΪCREATOR��
    public static final Parcelable.Creator<RecordInfo> CREATOR 
            = new Parcelable.Creator<RecordInfo>()
    {
        // From Parcelable.Creator
        @Override
        public RecordInfo createFromParcel(Parcel in)
        {
        	RecordInfo brief = new RecordInfo();
            
            // �Ӱ����ж�������
        	brief.ChainageName = in.readString();
        	brief.id = in.readInt();
        	brief.CrossSectionType = in.readInt();
        	brief.CreateTime = Timestamp.valueOf(in.readString());
        	brief.Info = in.readString();
        	brief.Facedk = in.readDouble();
        	brief.Facedescription = in.readString();
        	brief.Temperature = in.readDouble();
        	brief.CrossSectionIDs = in.readString();
        	//in.readList(brief.sectionlist, null);
        	boolean tmp[] = new boolean[1];
        	in.readBooleanArray(tmp);
        	brief.bUse = tmp[0];
            
            return brief;
        }



        // From Parcelable.Creator
        @Override
        public RecordInfo[] newArray(int size)
        {
            return new RecordInfo[size];
        }
    };
	
}
