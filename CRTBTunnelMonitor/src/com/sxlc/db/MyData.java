package com.sxlc.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

//���ݿ�����и��ݵ���project�ֱ��½����������ݿ�
public class MyData extends SQLiteOpenHelper {
	private String TAG = "MyData";
	private static final int VERSION = 1; // ��ʼĬ�ϰ汾
	Context mcontext;

	public MyData(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
		mcontext = context;
	}

	public MyData(Context context, String name, int version) {
		this(context, name, null, version);
	}

	public MyData(Context context, String name) {
		this(context, name, VERSION);
	}

	public void onCreate(SQLiteDatabase db) {

		// ����ProjectIndex��
		db.execSQL("create table if not exists ProjectIndex(id integer primary key,ProjectName varchar(255),"
				+ "CreateTime text,StartChainage double,EndChainage double,"
				+ "LastOpenTime text,Info text, ChainagePrefix varchar(255),"
				+ "GDLimitVelocity float,GDLimitTotalSettlement float,SLLimitVelocity float"
				+ "SLLimitTotalSettlement float,DBLimitVelocity float,DBLimitTotalSettlement float"
				+ ",ConstructionFirm varchar(255),LimitedTotalSubsidenceTime text)");
		// ����TunnelCrossSectionIndex�� ��������ڶ��������Ϣ��
		db.execSQL("create table if not exists TunnelCrossSectionIndex(Id INTEGER PRIMARY KEY AUTOINCREMENT,Chainage double,"
				+ "InbuiltTime text,Width float,ExcavateMethod integer,"
				+ "SurveyPntName VARCHAR(255),Info text,ChainagePrefix varchar(255),"
				+ "GDU0 FLOAT,"
				+ "GDVelocity FLOAT,"
				+ "GDU0Time DateTime,"
				+ "GDU0Description TEXT,"
				+ "SLU0 FLOAT,"
				+ "SLLimitVelocity FLOAT,"
				+ "SLU0Time DateTime,"
				+ "SLU0Description TEXT,"
				+ "Lithologic varchar(255),"
				+ "LAYVALUE FLOAT," + "ROCKGRADE varchar(255))");

		// ����SubsidenceCrossSectionIndex�� ���еر��³����������Ϣ��
		db.execSQL("create table if not exists SubsidenceCrossSectionIndex("
				+ "Id INTEGER PRIMARY KEY AUTOINCREMENT,Chainage DOUBLE,InbuiltTime DateTime,"
				+ "Width DOUBLE,SurveyPnts INTEGER,SurveyPntName varchar(255),Info TEXT,"
				+ "ChainagePrefix VARCHAR(255),DBU0 FLOAT,DBVelocity FLOAT,"
				+ "DBU0Time DateTime,DBU0Description TEXT,Lithologic VARCHAR(255),"
				+ "LAYVALUE FLOAT,ROCKGRADE VARCHAR(255))");

		// ���� TunnelCrossSectionExIndex�� ��������Ժ�ϴ��ӿ�Ҫ��Ķ��������Ϣ��
		db.execSQL("create table if not exists TunnelCrossSectionExIndex(Id INTEGER PRIMARY KEY AUTOINCREMENT"
				+ ",ZONECODE  varchar(64),"
				+ "SITECODE  varchar(64),"
				+ "SECTNAME  varchar(64),"
				+ "SECTCODE  varchar(64),"
				+ "SECTKILO  varchar(64),"
				+ "METHOD  varchar(10),"
				+ "WIDTH  float,"
				+ "MOVEVALUE_U0  float,"
				+ "UPDATEDATE  DateTime,"
				+ "REMARK_U0  varchar(255),"
				+ "HOLENAME  varchar(64),"
				+ "HOLESTARTKILO  varchar(64),"
				+ "HOLEENDKILO  varchar(64),"
				+ "INNERCODES  TEXT,"
				+ "LAYTIME  DateTime,"
				+ "UPLOAD  INTEGER,"
				+ "DESCRIPTION  TEXT)");

		// ����RawSheetIndex�� ����ڶ����¼���͵ر��³���¼��������
		db.execSQL("create table if not exists RawSheetIndex(Id INTEGER PRIMARY KEY AUTOINCREMENT"
				+ ",CrossSectionType  INTEGER,"
				+ "CreateTime  DateTime,"
				+ "Info  TEXT,"
				+ "FACEDK  Double,"
				+ "FACEDESCRIPTION  TEXT,"
				+ "TEMPERATURE  Double," + "CrossSectionIDs  TEXT)");

		// ����SubsidenceTotalData�� �ر��³������¼������������
		db.execSQL("create table if not exists SubsidenceTotalData(id integer primary key,"
				+ "StationId  INTEGER,"
				+ "ChainageId  INTEGER,"
				+ "SheetId  INTEGER,"
				+ "Coordinate  TEXT,"
				+ "PntType  TEXT,"
				+ "SurveyTime  DateTime,"
				+ "MEASNo  INTEGER,"
				+ "SurveyorID  INTEGER,"
				+ "DataStatus  INTEGER,"
				+ "DataCorrection  FLOAT," + "Info  TEXT"+")");

		// ���� TunnelSettlementTotalData�� ����ڶ����¼������������
		db.execSQL("create table if not exists TunnelSettlementTotalData(id integer primary key,"
				+ "StationId  INTEGER,"
				+ "ChainageId  INTEGER,"
				+ "SheetId  INTEGER,"
				+ "Coordinate  TEXT,"
				+ "PntType  TEXT,"
				+ "SurveyTime  DateTime,"
				+ "MEASNo  INTEGER,"
				+ "SurveyorID  INTEGER,"
				+ "DataStatus  INTEGER,"
				+ "DataCorrection  FLOAT," + "Info  TEXT"+")");

		// ���� StationInfoIndex�� ��վ��Ϣ��
		db.execSQL("create table if not exists TunnelSettlementTotalData(id integer primary key,StationPointId integer,"
				+ "StationHeight double,BackSightPointIds text,BackeSightHeight text,"
				+ "CreateTime text," + "Info text)");
		// ���� ControlPointsIndex�� ���Ƶ����
		db.execSQL("create table if not exists TunnelSettlementTotalData(id integer primary key,Name varchar(255),"
				+ "x double,y double,z double,Info text)");
		// ���� TotalStationIndex�� ȫվ�����Ӳ�����Ϣ
		db.execSQL("create table if not exists TunnelSettlementTotalData(id integer primary key,Name varchar(255),"
				+ "BaudRate integer,Port integer,Parity integer,Databits integer,Stopbits integer,Info text)");
	}

	@Override
	/**
	 * �����ݿ���Ҫ�����µ�ʱ��ִ�У�����ɾ���ñ������±�
	 */
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("alter table user add age int");
		System.out.println("����");
	}
}
