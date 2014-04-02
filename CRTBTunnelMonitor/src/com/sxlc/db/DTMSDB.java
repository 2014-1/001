/**
 * 
 */
package com.sxlc.db;

import com.sxlc.common.Constant;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Administrator
 *
 */
public class DTMSDB extends SQLiteOpenHelper {

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
	public DTMSDB(Context context, String name, CursorFactory factory,
			int version) {
		super(context, Constant.DB_NAME_DTMSDB+".db", null, Constant.DB_VERSION_DTMSDB);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 * @param errorHandler
	 */
	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		// ����ProjectIndex�� --�������й�����Ϣ�б����̹������롢����ʹ�ã�
		db.execSQL("create table if not exists ProjectIndex(id INTEGER PRIMARY KEY AUTOINCREMENT ,ProjectName varchar(255),"
				+ "CreateTime text,StartChainage double,EndChainage double,"
				+ "LastOpenTime text,Info text, ChainagePrefix varchar(255),"
				+ "GDLimitVelocity float,GDLimitTotalSettlement float,SLLimitVelocity float,"
				+ "SLLimitTotalSettlement float,DBLimitVelocity float,DBLimitTotalSettlement float"
				+ ",ConstructionFirm varchar(255),LimitedTotalSubsidenceTime text)");
		// ����SurveyerIndex�� --���������в�����Ա��Ϣ
		db.execSQL("create table if not exists SurveyerIndex(Id INTEGER PRIMARY KEY AUTOINCREMENT,SurveyerName VARCHAR(100),"
				+ "CertificateID VARCHAR(20),Password VARCHAR(64),Info text,"
				+ "ProjectID INTEGER)");
		db.execSQL("create table if not exists ProjectSettingIndex(Id INTEGER PRIMARY KEY AUTOINCREMENT,ProjectName VARCHAR(255),"
				+ "YMDFormat INTEGER,HMSFormat INTEGER,ChainagePrefix VARCHAR(255),MaxDeformation INTEGER,"
				+ "Info TEXT)");
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
