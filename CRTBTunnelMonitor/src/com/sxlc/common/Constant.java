
package com.sxlc.common;

/**
 * ��������
 * 
 * @author �ľ�
 * @since JDK1.6
 * @version 1.0
 *
 */
public class Constant {

	/**
	 * 
	 */
	/*DTMSDB���ݿ�汾��Ϣ*/
	public static final int DB_VERSION_DTMSDB = 1;
	/*DTMSDB���ݿ�����*/
	public static final String DB_NAME_DTMSDB = "DTMSDB";
	/*��¼ѡ��name��value*/
	public static final String Select_LoginName_Name = "name";
	public static final int Select_LoginValue_Local = 1;
	public static final int Select_LoginValue_Server = 2;
	
	/*�������е���˵���*/
	public static final CharSequence WorkRowClickItems[] = { "��", "�༭", "����", "ɾ��" };
	public static final CharSequence SectionRowClickItems[] = {"�༭","ɾ��" };
	public static final CharSequence RecordRowClickItems[] = {"�༭","ɾ��" };
	/**ȫվ�ǹ���˵���*/
	public static final CharSequence TotalStationItems[] = { "Leica", "Trimble", "LeicaTPS",
		"Topcon","Pentax","Sokkia",
		"Nikon","South","South302",
		"KTS","SanDing","RuiDe",
		"Foif","GeMax","Kovan"};
	public static final CharSequence TotalStationNameItems[] = { "�⿨", "�챦", "�⿨TPS",
		"���տ�","����","����",
		"�῵","�Ϸ�","�Ϸ�302",
		"������","����","���",
		"��һ��","��γ","��ά"};
	public enum TotalStationType
	{
		Leica("�⿨"),	
		Trimble("�챦"),
		LeicaTPS("�⿨TPS"),
		Topcon("���տ�"),
		Pentax("����"),
		Sokkia("����"),
		Nikon("�῵"),
		South("�Ϸ�"),
		South302("�Ϸ�302"),
		KTS("������"),
		SanDing("����"),
		RuiDe("���"),
		Foif("��һ��"),
		GeMax("��γ"),
		Kovan("��ά");
		private final String desc; 

        private TotalStationType(String desc) { 
                this.desc = desc; 
        } 

        public String getDesc() { 
                return desc; 
        }	
    }
	public static final CharSequence ControlPointsItems[] = { "��������", "��������", "�Ͽ�����"};
	/*�������е���˵���ѡ��name��value*/
	public static final String Select_WorkRowClickItemsName_Name = "name";
	public static final String Select_SectionRowClickItemsName_Name = "name";
	public static final String Select_RecordRowClickItemsName_Name = "name";
	public static final String Select_RecordRowClickItemsName_Data = "data";
	public static final String Select_TotalStationRowClickItemsName_Name = "name";
	public static final String Select_TotalStationRowClickItemsName_Data = "data";
	public static final String Select_ControlPointsRowClickItemsName_Name = "name";
	public static final String Select_ControlPointsRowClickItemsName_Data = "data";
	public static final String Select_WorkRowClickItemsValue_Open = "���ݿ�����";
	public static final String Select_WorkRowClickItemsValue_Edit = "�༭";
	

	//�����ռ�
	public static final String NameSpace="webservice.riskcontrol.com";
	//�û���֤
	public static final String UserSelect = "http://61.237.239.144/fxkz/basedown";
	//�ϴ�
	public static final String Update = "http://61.237.239.144/fxkz/testdata";
	
	//������
	public static final String testUsername = "cl19h1";
	public static final String testPassword = "123";
	public static final String testPhysical= "04:4b:ff:07:de:23";
	public static final String testDeskey = "crtb1234";
	public static final String testPublicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAMFUtGx6lOnO5dLxy/1uNqUAzG7mhRKkWFJEZ9QWup+Y1+bgRoz2xdlL1ZqwpFi3AYbFrCa37zK1A5WbCvq37j0CAwEAAQ==";


	public Constant() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
