package com.sxlc.infors;

/*
 * ��¼��ʵ����
 */
public class RecordBase {
	
	public int StationId;// ;��վID
	public int ChainageId;// ;�������ID
	public int SheetId;// ��¼��ID
	public String Coordinate;// �������
	public String PntType;// �������
	public String SurveyTime;// ����ʱ��
	public String Info;// ��ע
	public int MEASNo;// �ڼ���
	public int SurveyorID;// ������ԱID
	public int DataStatus;// �쳣���ݱ�ʶ
	public float DataCorrection;// �쳣��������ֵ
	
	public int CrossSectionType;//��������
	public String CreateTime;//����ʱ��
	//String Info;//��ע
	public Double FACEDK;//���������ֵ
	public String FACEDESCRIPTION;//ʩ������
	public Double TEMPERATURE;//�¶�ֵ
	public String CrossSectionIDs;//����ID����
	public  int  Width ;//������
	public  String SurveyPnts = null;//�����
	public  String InbuiltTime = null;//����ʱ��
	public String ChainagePrefix;//���ǰ׺
	public String DBU0;//�ر�U0ֵ
	public String DBU0Description;//�������ޱ�ע
	public float Lithologic=5;//�ر��²�����
	public float LAYVALUE;//����ֵ
	public String ROCKGRADE;//Χ�Ҽ���
	
}
