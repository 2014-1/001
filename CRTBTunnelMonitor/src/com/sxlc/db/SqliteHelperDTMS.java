package com.sxlc.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteHelperDTMS extends SQLiteOpenHelper {

	/** �汾�� */
	private static final int VERSION = 1;

	/**
	 * ���ι��췽��
	 * 
	 * @param context
	 *            ������
	 * @param name
	 *            ���ݿ�
	 * @param factory
	 *            ����
	 * @param version
	 *            �汾��
	 */
	public SqliteHelperDTMS(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name.concat(".db"), null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		// ����ProjectIndex��
		db.execSQL("create table if not exists ProjectIndex(id INTEGER PRIMARY KEY AUTOINCREMENT ,ProjectName varchar(255),"
				+ "CreateTime text,StartChainage double,EndChainage double,"
				+ "LastOpenTime text,Info text, ChainagePrefix varchar(255),"
				+ "GDLimitVelocity float,GDLimitTotalSettlement float,SLLimitVelocity float,"
				+ "SLLimitTotalSettlement float,DBLimitVelocity float,DBLimitTotalSettlement float"
				+ ",ConstructionFirm varchar(255),LimitedTotalSubsidenceTime text)");
		//����ProjectManage��
		String sql = "create table if not exists ProjectManage(Id INTEGER PRIMARY KEY AUTOINCREMENT ," +
				"CurrentProject integer," +
				"LastOpenProject integer," +
				"Info text)";
		db.execSQL(sql);
		
		//���� ProjectSettingIndex ��
		sql = "create table if not exists ProjectSettingIndex(Id INTEGER PRIMARY KEY AUTOINCREMENT ," +
				"ProjectName VARCHAR(255)," +
				"ProjectID INTEGER," +
				"YMDFormat INTEGER," +
				"HMSFormat INTEGER," +
				"ChainagePrefix varchar(255)," +
				"MaxDeformation DOUBLE," +
				"Info TEXT)";
		db.execSQL(sql);
		
		//����TotalStationSettingIndex��
		sql = "create table if not exists TotalStationSettingIndex(Id INTEGER PRIMARY KEY AUTOINCREMENT ," +
				"Name varchar(255)," +
				"Port INTEGER," +
				"Baudrate INTEGER," +
				"ReconnectTimes INTEGER," +
				"DataBits INTEGER," +
				"DataStops INTEGER," +
				"Parity varchar(255)," +
				"Info TEXT)";
		db.execSQL(sql);
		
		//����DTMSVersion��
		sql = "create table if not exists DTMSVersion(Id INTEGER PRIMARY KEY AUTOINCREMENT ," +
				"AppVer varchar(255)," +
				"DBVer INTEGER," +
				"Info TEXT)";
		db.execSQL(sql);
		
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
		sql = "create table if not exists SubsidenceCrossSectionIndex("
				+ "Id INTEGER PRIMARY KEY AUTOINCREMENT,Chainage DOUBLE,InbuiltTime DateTime,"
				+ "Width DOUBLE,SurveyPnts varchar(255),Info TEXT,"
				+ "ChainagePrefix VARCHAR(255),DBU0 FLOAT,DBLimitVelocity FLOAT,"
				+ "DBU0Time DateTime,DBU0Description TEXT,Lithologic VARCHAR(255),"
				+ "LAYVALUE FLOAT,ROCKGRADE VARCHAR(255))";
		db.execSQL(sql);

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
		db.execSQL("create table if not exists SubsidenceTotalData(id integer primary key AUTOINCREMENT,"
				+ "StationId  INTEGER,"
				+ "ChainageId  INTEGER,"
				+ "SheetId  INTEGER,"
				+ "Coordinate  TEXT,"
				+ "PntType  TEXT,"
				+ "SurveyTime  DateTime,"
				+ "MEASNo  INTEGER,"
				+ "SurveyorID  INTEGER,"
				+ "DataStatus  INTEGER,"
				+ "DataCorrection  FLOAT," + "Info  TEXT" + ")");

		// ���� TunnelSettlementTotalData�� ����ڶ����¼������������
		db.execSQL("create table if not exists TunnelSettlementTotalData(id integer primary key AUTOINCREMENT,"
				+ "StationId  INTEGER,"
				+ "ChainageId  INTEGER,"
				+ "SheetId  INTEGER,"
				+ "Coordinate  TEXT,"
				+ "PntType  TEXT,"
				+ "SurveyTime  DateTime,"
				+ "MEASNo  INTEGER,"
				+ "SurveyorID  INTEGER,"
				+ "DataStatus  INTEGER,"
				+ "DataCorrection  FLOAT," + "Info  TEXT" + ")");
		
		//����SubsidenceRecord ��
		sql = "create table if not exists SubsidenceRecord(Id integer primary key AUTOINCREMENT," +
				"StationId INTEGER," +
				"ChainageId INTEGER," +
				"SheetId INTEGER," +
				"Coordinate TEXT," +
				"PntType TEXT," +
				"SurveyTime DateTime," +
				"ConstructionFirm varchar(255)," +
				"Surveyer varchar(255)," +
				"Recorder varchar(255)," +
				"Info TEXT)";
		db.execSQL(sql);
		
		//����TunnelCrossSectionRecord��
		sql = "create table if not exists TunnelCrossSectionRecord(Id integer primary key AUTOINCREMENT," +
				"StationId INTEGER," +
				"ChainageId INTEGER," +
				"SheetId INTEGER," +
				"Coordinate TEXT," +
				"PntType TEXT," +
				"SurveyTime DateTime," +
				"ConstructionFirm varchar(255)," +
				"Surveyer varchar(255)," +
				"Recorder varchar(255)," +
				"Info TEXT)";
		db.execSQL(sql);
		
		//����TotalStationIndex��
		sql = "create table if not exists TotalStationIndex(Id integer primary key AUTOINCREMENT," +
				"Name varchar(255)," +
				"TotalstationType TEXT," +
				"BaudRate INTEGER," +
				"Port INTEGER," +
				"Parity INTEGER," +
				"Databits INTEGER," +
				"Stopbits INTEGER," +
				"Info TEXT)";
		db.execSQL(sql);
		
		//����StationInfoIndex��
		sql = "create table if not exists StationInfoIndex(Id integer primary key AUTOINCREMENT," +
				"StationPointIndex INTEGER," +
				"StationHeight DOUBLE," +
				"BackSightPointIds TEXT," +
				"BackeSightHeight DOUBLE," +
				"CreateTime DateTime," +
				"Info TEXT)";
		db.execSQL(sql);
		
		//���� ControlPointsIndex ��
		sql = "create table if not exists ControlPointsIndex(Id integer primary key AUTOINCREMENT," +
				"Name varchar(255)," +
				"X double," +
				"Y double," +
				"Z double," +
				"Info TEXT)";
		db.execSQL(sql);
		
		//���� DTMSProjectVersion ��
		sql = "create table if not exists DTMSProjectVersion(Id integer primary key AUTOINCREMENT," +
				"AppVer varchar(255)," +
				"DBVer INTEGER," +
				"Info TEXT)";
		db.execSQL(sql);
		
		//���� CrownSettlementArching ��
		sql = "create table if not exists CrownSettlementArching(Id integer primary key AUTOINCREMENT," +
				"OriginalDataId INTEGER," +
				"SheetId INTEGER," +
				"CurrentSettlement DOUBLE," +
				"TotalSettlement DOUBLE," +
				"CurrentVelocity FLOAT," +
				"TotalVelocity FLOAT," +
				"CurrnetTimeSpan FLOAT," +
				"TotalTimeSpan FLOAT," +
				"TunnelFaceDistance DOUBLE," +
				"ManageLevel USHORT," +
				"Info TEXT)";
		db.execSQL(sql);
		
		// ���� SubsidenceSettlementArching ��
		sql = "create table if not exists SubsidenceSettlementArching(Id integer primary key AUTOINCREMENT," +
				"OriginalDataId INTEGER," +
				"SheetId INTEGER," +
				"CurrentSettlement DOUBLE," +
				"TotalSettlement DOUBLE," +
				"CurrentVelocity FLOAT," +
				"TotalVelocity FLOAT," +
				"CurrnetTimeSpan FLOAT," +
				"TotalTimeSpan FLOAT," +
				"TunnelFaceDistance DOUBLE," +
				"ManageLevel USHORT," +
				"Info TEXT)";
		db.execSQL(sql);
		
		//���� ConvergenceSettlementArching ��
		sql = "create table if not exists ConvergenceSettlementArching(Id integer primary key AUTOINCREMENT," +
				"OriginalDataId_One INTEGER," +
				"OriginalDataId_Two INTEGER," +
				"ChainageId INTEGER," +
				"SheetId INTEGER," +
				"CurrnetConvergence DOUBLE," +
				"TotalConvergence DOUBLE," +
				"CurrentVelocity FLOAT," +
				"TotalVelocity FLOAT," +
				"CurrnetTimeSpan FLOAT," +
				"TotalTimeSpan FLOAT," +
				"TunnelFaceDistance DOUBLE," +
				"ManageLevel USHORT," +
				"Info TEXT)";
		db.execSQL(sql);
		
		//���� SurveyerInformation ��
		sql = "create table if not exists SurveyerInformation(Id integer primary key AUTOINCREMENT," +
				"SurveyerName varchar(100)," +
				"CertificateID varchar(20)," +
				"Password varchar(64)," +
				"ProjectID INTEGER," +
				"Info TEXT)";
		db.execSQL(sql);
		
		//����RawSheetIndex_Index��
		sql = "create table if not exists RawSheetIndex_Index(Id integer primary key AUTOINCREMENT," +
				"CrossSectionType INTEGER," +
				"CreateTime DateTime)";
		db.execSQL(sql);
		
		//���� TunnelCrossSectionIndex_Index ��
		sql = "create table if not exists TunnelCrossSectionIndex_Index(Id integer primary key AUTOINCREMENT," +
				"Chainage DOUBLE)";
		db.execSQL(sql);
		
		//����SubsidenceCrossSectionIndex_Index��
		sql = "create table if not exists SubsidenceCrossSectionIndex_Index(Id integer primary key AUTOINCREMENT," +
		"Chainage DOUBLE)";
		db.execSQL(sql);
		
		//����TunnelSettlementTotalData_Index��
		sql = "create table if not exists TunnelSettlementTotalData_Index(Id INTEGER primary key AUTOINCREMENT," +
				"StationId INTEGER," +
				"ChainageId INTEGER," +
				"SheetId INTEGER," +
				"Coordinate TEXT)";
		db.execSQL(sql);
		
		//����SubsidenceTotalData_Index��
		sql = "create table if not exists SubsidenceTotalData_Index(Id INTEGER primary key AUTOINCREMENT," +
				"StationId INTEGER," +
				"ChainageId INTEGER," +
				"SheetId INTEGER)";
		db.execSQL(sql);
		
		//����StationInfoIndex_Index��
		sql = "create table if not exists StationInfoIndex_Index(Id INTEGER primary key AUTOINCREMENT," +
				"CreateTime DateTime)";
		db.execSQL(sql);
		
		//���� ControlPointsIndex_Index��
		sql = "create table if not exists ControlPointsIndex_Index(Id INTEGER primary key AUTOINCREMENT," +
				"Name varchar(255))";
		db.execSQL(sql);
		
		//���� CrownSettlementArching_Index ��
		sql = "create table if not exists CrownSettlementArching_Index(Id INTEGER primary key AUTOINCREMENT," +
				"OriginalDataId INTEGER," +
				"SheetId INTEGER)";
		db.execSQL(sql);
		
		//���� SubsidenceSettlementArching_Index ��
		sql = "create table if not exists SubsidenceSettlementArching_Index(Id INTEGER primary key AUTOINCREMENT," +
				"OriginalDataId INTEGER," +
				"SheetId INTEGER)";
		db.execSQL(sql);
		
		//���� ConvergenceSettlementArching_Index ��
		sql = "create table if not exists ConvergenceSettlementArching_Index(Id INTEGER primary key AUTOINCREMENT," +
				"SheetId INTEGER," +
				"OriginalDataId_One INTEGER," +
				"OriginalDataId_Two INTEGER," +
				"ChainageId INTEGER)";
		db.execSQL(sql);
		
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
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// ���ݿ���������ò���
	}
}
