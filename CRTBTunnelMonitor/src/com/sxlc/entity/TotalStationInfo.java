package com.sxlc.entity;

import java.sql.Timestamp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * ȫվ�����Ӳ�����Ϣ
 *����ʱ�䣺2014-3-24����13:50:00
 *@author ����
 *@since JDK1.6
 *@version 1.0
 */
public class TotalStationInfo implements Parcelable{
	private int id;						//id
	private String Name;				//��������
	private String TotalstationType;	//ȫվ��Ʒ��
	private int BaudRate;				//������
	private int Port;					//�˿ں�
	private int Parity;					//��żУ��
	private int Databits;				//����λ
	private int Stopbits;				//ֹͣλ
	private String Info;				//��ע
	private String Cmd;                 //����
	private boolean bUse;
	private boolean bCheck;
	
	public boolean isbCheck() {
		return bCheck;
	}
	public void setbCheck(boolean bCheck) {
		this.bCheck = bCheck;
	}
	public boolean isbUse() {
		return bUse;
	}
	public void setbUse(boolean bUse) {
		this.bUse = bUse;
	}
	public String getCmd() {
		return Cmd;
	}
	public void setCmd(String cmd) {
		Cmd = cmd;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getTotalstationType() {
		return TotalstationType;
	}
	public void setTotalstationType(String totalstationType) {
		TotalstationType = totalstationType;
	}
	public int getBaudRate() {
		return BaudRate;
	}
	public void setBaudRate(int baudRate) {
		BaudRate = baudRate;
	}
	public int getPort() {
		return Port;
	}
	public void setPort(int port) {
		Port = port;
	}
	public int getParity() {
		return Parity;
	}
	public void setParity(int parity) {
		Parity = parity;
	}
	public int getDatabits() {
		return Databits;
	}
	public void setDatabits(int databits) {
		Databits = databits;
	}
	public int getStopbits() {
		return Stopbits;
	}
	public void setStopbits(int stopbits) {
		Stopbits = stopbits;
	}
	public String getInfo() {
		return Info;
	}
	public void setInfo(String info) {
		Info = info;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeInt(id);
		dest.writeString(Name);
		dest.writeString(TotalstationType);
		dest.writeInt(BaudRate);
		dest.writeInt(Port);
		dest.writeInt(Parity);
		dest.writeInt(Databits);
		dest.writeInt(Stopbits);
		dest.writeString(Info);
		dest.writeString(Cmd);
		dest.writeBooleanArray(new boolean[]{bUse,bCheck});
	}
	
    // ʵ��Parcelable�ӿڵ������У�������һ��ʵ����Parcelable.Creator�ӿڵľ�̬������Ա�ֶΣ�
    // �����������ֱ���ΪCREATOR��
    public static final Parcelable.Creator<TotalStationInfo> CREATOR 
            = new Parcelable.Creator<TotalStationInfo>()
    {
        // From Parcelable.Creator
        @Override
        public TotalStationInfo createFromParcel(Parcel in)
        {
        	TotalStationInfo brief = new TotalStationInfo();
            
            // �Ӱ����ж�������
        	brief.id = in.readInt();
        	brief.Name = in.readString();
        	brief.TotalstationType = in.readString();
        	brief.BaudRate = in.readInt();
        	brief.Port = in.readInt();
        	brief.Parity = in.readInt();
        	brief.Databits = in.readInt();
        	brief.Stopbits = in.readInt();
        	brief.Info = in.readString();
        	brief.Cmd = in.readString();
        	boolean tmp[] = new boolean[2];
        	in.readBooleanArray(tmp);
   			brief.bUse = tmp[0];
   			brief.bCheck = tmp[1];
            
            return brief;
        }



        // From Parcelable.Creator
        @Override
        public TotalStationInfo[] newArray(int size)
        {
            return new TotalStationInfo[size];
        }
    };
	
}
