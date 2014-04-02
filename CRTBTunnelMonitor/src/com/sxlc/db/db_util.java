package com.sxlc.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class db_util {
	static SQLiteDatabase database;

	static public void add(SQLiteOpenHelper Database,String table,ContentValues values1){
		database = Database.getWritableDatabase();
		database.insert(table, null, values1);
		database.close();
	}

	static public void update(SQLiteOpenHelper Database, String table,
			String[] columns, String from_key, String from_value,
			String to_key, String to_value) {
		database = Database.getWritableDatabase();
		ContentValues values1 = new ContentValues();
		values1.put(to_key, to_value);
		database.update(table, values1, from_key + "=?",
			new String[] { String.valueOf(from_value) });
		database.close();
	}

	/*
	 * table ���� columns ���ű�����м�ֵ key ��ֵ value ���� ���Ҹñ�ĸü�ֵƥ�������
	 */
	static public String search(SQLiteOpenHelper Database, String table,
			String[] columns, String key, String value, String result_key) {
		database = Database.getWritableDatabase();
		Cursor cursor = database.query(table, columns, key + "?",
				new String[] { value }, null, null, null);
		String s = "null";
		while (cursor.moveToNext()) { // ������ȡ��ȡ��ÿ��name��Ϣ
			s = cursor.getString(cursor.getColumnIndex(result_key));
		}
		Database.close();
		return s;
	}

	/*
	 * table ���� columns ���ű�����м�ֵ key ��ֵ value ���� ɾ���ñ�ĺ͸ü�ֵƥ�������
	 */
	static public void detel(SQLiteOpenHelper Database, String table,
			String[] columns, String key, String value) {
		database = Database.getWritableDatabase();
		database.delete(table, key + "=?",
				new String[] { String.valueOf(value) });
		Database.close();
	}

}
