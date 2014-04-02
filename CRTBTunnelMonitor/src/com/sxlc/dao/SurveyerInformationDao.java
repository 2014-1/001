package com.sxlc.dao;

import java.util.List;

import com.sxlc.entity.SubsidenceCrossSectionInfo;
import com.sxlc.entity.SurveyerInformation;

/**
 *������Ա��Ϣ���ݿ�ӿ�
 *����ʱ�䣺2014-3-24����13:50:00
 *@author ����
 *@since JDK1.6
 *@version 1.0
 */
public interface SurveyerInformationDao {
	/**��ѯȫ��*/
	public List<SurveyerInformation> SelectAllSurveyerInfo();
	/**�½�������Ա*/
	public Boolean InsertSurveyerInfo(SurveyerInformation s);
	/**��ѯ������Ա*/
	public SurveyerInformation SelectSurveyerInfo(int id);
	/**ɾ��������Ա*/
	public Boolean DeleteSurveyerInfo(int id);
	/**�༭������Ա*/
	public Boolean UpdateSurveyerInfo(SurveyerInformation s);
}
