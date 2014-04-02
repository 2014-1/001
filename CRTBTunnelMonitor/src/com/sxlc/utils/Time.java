package com.sxlc.utils;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * ��ȡ�ֻ���ǰʱ��
 *����ʱ�䣺2013-11-1����3:46:32
 *@author ����
 *@since JDK1.6
 *@version 1.0
 */
public class Time {
	public static String getDateCN() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy��MM��dd��  HH:mm:ss");
		String date = format.format(new Date(System.currentTimeMillis()));
		return date;// 2012��10��03�� 23:41:31
	}

	public static String getDateEN() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1 = format1.format(new Date(System.currentTimeMillis()));
		return date1;// 2012-10-03 23:41:31
	}

	public static String getDate() {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		String date = format.format(new Date(System.currentTimeMillis()));
		return date;
	}
}
