package com.sxlc.activity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.sxlc.common.Constant;
import com.sxlc.dao.impl.SubsidenceCrossSectionDaoImpl;
import com.sxlc.dao.impl.TunnelCrossSectionDaoImpl;
import com.sxlc.entity.SubsidenceCrossSectionInfo;
import com.sxlc.entity.TunnelCrossSectionInfo;
import com.sxlc.entity.WorkInfos;
import com.sxlc.utils.Time;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * �༭�ر��²����
 * @author   ������
 * ����ʱ��:    2014-3-20   ����2:28:10
 * @version   1.0
 * @since       JDK  1.6
 *
 */
public class SectionEditActivity extends Activity implements OnClickListener
 {
    private ViewPager mPager;// ҳ������
    private List<View> listViews; // Tabҳ���б�
    private ImageView cursor;// ����ͼƬ
    private TextView t1, t2, t3, textView1;// ҳ��ͷ��
    private int offset = 0;// ����ͼƬƫ����
    private int currIndex = 0;// ��ǰҳ�����
    private int bmpW;// ����ͼƬ���
    private View View3;
    private TextView section_new_tv_diheader;  
    private int num;
    
    private EditText DSection_Chainage;
    private EditText DSection_name;
    private EditText DSection_createtime;
    private EditText DSection_Width;
    private EditText DSection_PointCount;
    private EditText DSection_Value1;
    private EditText DSection_Value2;
    private EditText DSection_SetTime;
    private EditText DSection_Info;

	/** ȷ����ť */
	private Button section_btn_queding;
	/** ȡ����ť */
	private Button section_btn_quxiao;
	
	private String sChainage = null;
	private SubsidenceCrossSectionInfo editInfo = null;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sectionedit);
        num = getIntent().getExtras().getInt("name");
        InitImageView();
        InitTextView();
        InitViewPager();
        InitMyTextView();
        
		sChainage = getIntent().getExtras().getString(Constant.Select_SectionRowClickItemsName_Name);
		double dChainage = CRTBTunnelMonitor.StrToDouble(sChainage, -1);
		InitImageView();
		InitTextView();
		InitViewPager();
		InitMyTextView();

		if (sChainage.length() > 0) {
        	section_new_tv_diheader.setText("�༭�ر��²����");
			CRTBTunnelMonitor CurApp = ((CRTBTunnelMonitor)getApplicationContext());
			WorkInfos Curw = CurApp.GetCurWork();
			List<SubsidenceCrossSectionInfo> infos = Curw.getScsiList();
			for(int i=0;i<infos.size();i++)
			{
				SubsidenceCrossSectionInfo tmp = infos.get(i);
				if(tmp.getChainage().equals(dChainage))
				{
					editInfo = tmp;
					break;
				}
			}
		}
    }

    /**
     * ��ʼ������
     */
    private void InitImageView() {
        cursor = (ImageView) findViewById(R.id.cursor);
        bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.a)
                .getWidth();// ��ȡͼƬ���
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// ��ȡ�ֱ��ʿ��
        offset = (screenW / 2 - bmpW) / 2;// ����ƫ����
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        cursor.setImageMatrix(matrix);// ���ö�����ʼλ��
    }

    /**
     * ��ʼ��ͷ��
     */
    private void InitTextView() {
        t1 = (TextView) findViewById(R.id.tev);
        t2 = (TextView) findViewById(R.id.tex);
        section_new_tv_diheader = (TextView) findViewById(R.id.section_new_tv_diheader);
        t1.setOnClickListener(new MyOnClickListener(0));
        t2.setOnClickListener(new MyOnClickListener(1));
		section_btn_queding = (Button) findViewById(R.id.work_btn_queding);
		section_btn_quxiao = (Button) findViewById(R.id.work_btn_quxiao);

		section_btn_queding.setOnClickListener(this);
		section_btn_quxiao.setOnClickListener(this);
		
	}
	// ����¼�
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.work_btn_quxiao:
			Intent IntentCancel = new Intent();
			IntentCancel.putExtra(Constant.Select_SectionRowClickItemsName_Name,2);
			setResult(RESULT_CANCELED, IntentCancel);
			this.finish();// �رյ�ǰ����
			break;
		case R.id.work_btn_queding: // ���ݿ�
			if(DSection_Chainage.getText().toString().trim().length() <= 0)
			{
				Toast.makeText(this, "������������Ϣ", 3000).show();
				return;
			}
			if(DSection_Width.getText().toString().trim().length() <= 0)
			{
				Toast.makeText(this, "������������Ϣ", 3000).show();
				return;
			}
			if(DSection_PointCount.getText().toString().trim().length() <= 0)
			{
				Toast.makeText(this, "������������Ϣ", 3000).show();
				return;
			}

			CRTBTunnelMonitor CurApp = ((CRTBTunnelMonitor)getApplicationContext());
			WorkInfos Curw = CurApp.GetCurWork();
			SubsidenceCrossSectionInfo ts = new SubsidenceCrossSectionInfo();
			if (editInfo != null) {
				ts.setId(editInfo.getId());
			}
			ts.setChainage(Double.valueOf(DSection_Chainage.getText().toString().trim()));
			ts.setChainagePrefix(Curw.getChainagePrefix());
			ts.setInbuiltTime(Timestamp.valueOf(DSection_createtime.getText().toString()));
			ts.setWidth(Integer.valueOf(DSection_Width.getText().toString().trim()));
			ts.setSurveyPnts(DSection_PointCount.getText().toString().trim());
			ts.setInfo(DSection_Info.getText().toString().trim());
			ts.setChainageName(CurApp.GetSectionName(ts.getChainage().doubleValue()));
			if(!CurApp.IsValidSubsidenceTunnelCrossSectionInfo(ts))
			{
				Toast.makeText(this, "������������Ϣ", 3000).show();
				return;
			}
			if ((ts.getChainage().doubleValue() < Curw.getStartChainage().doubleValue()) ||
					(ts.getChainage().doubleValue() > Curw.getEndChainage().doubleValue())){
				String sStart = CurApp.GetSectionName(Curw.getStartChainage().doubleValue());
				String sEnd = CurApp.GetSectionName(Curw.getEndChainage().doubleValue());
				String sMsg = "���������Ϊ"+sStart+"��"+sEnd+"֮������";
				Toast.makeText(this, sMsg, 3000).show();
				return;
			}
			List<SubsidenceCrossSectionInfo> infos = Curw.getScsiList();
			if(infos == null)
			{
				Toast.makeText(this, "���ʧ��", 3000).show();
			}
			else
			{
				boolean bHave = false;
				for(int i=0;i<infos.size();i++)
				{
					SubsidenceCrossSectionInfo tmp = infos.get(i);
					if(tmp.getChainage().equals(ts.getChainage()))
					{
						bHave = true;
						break;
					}
				}
				if(bHave)
				{
					if(editInfo == null)
					{
						Toast.makeText(this, "�Ѵ���", 3000).show();
						return;
					}
					else
					{
						SubsidenceCrossSectionDaoImpl impl = new SubsidenceCrossSectionDaoImpl(this,Curw.getProjectName());
						impl.UpdateSubsidenceCrossSection(ts);
						Curw.UpdateSubsidenceCrossSectionInfo(ts);
						CurApp.UpdateWork(Curw);
						Toast.makeText(this, "�༭�ɹ�", 3000).show();
					}
				}
				else
				{
					SubsidenceCrossSectionDaoImpl impl = new SubsidenceCrossSectionDaoImpl(this,Curw.getProjectName());
					if(impl.InsertSubsidenceCrossSection(ts))
					{
						infos.add(ts);
						CurApp.UpdateWork(Curw);
						Toast.makeText(this, "��ӳɹ�", 3000).show();
					}
					else
					{
						Toast.makeText(this, "���ʧ��", 3000).show();
					}
				}
			}
			Intent IntentOk = new Intent();
			IntentOk.putExtra(Constant.Select_SectionRowClickItemsName_Name,2);
			setResult(RESULT_OK, IntentOk);
			this.finish();
			break;
		default:
			break;
		}

	}

    /**
     * ��ʼ��ViewPager
     */
    private void InitViewPager() {
        mPager = (ViewPager) findViewById(R.id.vPager);
        listViews = new ArrayList<View>();
        LayoutInflater mInflater = getLayoutInflater();
        listViews.add(mInflater.inflate(R.layout.layout_4, null));
        listViews.add(mInflater.inflate(R.layout.layout_2, null));
        mPager.setAdapter(new MyPagerAdapter(listViews));
        mPager.setCurrentItem(0);
        mPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    /**
     * С����
     */
    public void InitMyTextView() {
     
    }

    /**
     * ViewPager������
     */
    public class MyPagerAdapter extends PagerAdapter {
        public List<View> mListViews;

        public MyPagerAdapter(List<View> mListViews) {
            this.mListViews = mListViews;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(mListViews.get(arg1));
        }

        @Override
        public void finishUpdate(View arg0) {
        }

        @Override
        public int getCount() {
            return mListViews.size();
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(mListViews.get(arg1), 0);

			CRTBTunnelMonitor CurApp = ((CRTBTunnelMonitor)getApplicationContext());
			WorkInfos Curw = CurApp.GetCurWork();
			
			DSection_Chainage = (EditText) findViewById(R.id.DSection_Chainage);
			DSection_name = (EditText) findViewById(R.id.DSection_name);
			DSection_createtime = (EditText) findViewById(R.id.DSection_createtime);
			DSection_Width = (EditText) findViewById(R.id.DSection_Width);
			DSection_PointCount = (EditText) findViewById(R.id.DSection_PointCount);

			DSection_Value1 = (EditText) findViewById(R.id.DSection_Value1);
			DSection_Value2 = (EditText) findViewById(R.id.DSection_Value2);
			DSection_SetTime = (EditText) findViewById(R.id.DSection_SetTime);
			DSection_Info = (EditText) findViewById(R.id.DSection_Info);
			
			DSection_Chainage.setOnFocusChangeListener(new View.OnFocusChangeListener() {
				
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					// TODO Auto-generated method stub
					//if(!hasFocus)
					{
						String sChainage = DSection_Chainage.getText().toString().trim();
						if (sChainage.length() > 0) {
							CRTBTunnelMonitor CurApp = ((CRTBTunnelMonitor)getApplicationContext());
							DSection_name.setText(CurApp.GetSectionName(Double.valueOf(sChainage).doubleValue()));
						}
						else {
							DSection_name.setText("");
						}
					}
				}
			});

			// ��ȡ�ֻ��ĵ�ǰʱ��
			final String time = Time.getDateEN();
			// �����ı�������������С
			DSection_createtime.setTextSize(11);
			if (arg1 == 0) {
				if (editInfo != null) {
					DSection_Chainage.setText(editInfo.getChainage().toString());
					DSection_Chainage.setFocusableInTouchMode(false);
					DSection_name.setText(CurApp.GetSectionName(editInfo.getChainage().doubleValue()));
					DSection_name.setFocusableInTouchMode(false);
					DSection_createtime.setText(editInfo.getInbuiltTime().toString());
					DSection_createtime.setFocusableInTouchMode(false);
					DSection_Width.setText(Integer.toString(editInfo.getWidth()));
					DSection_Width.setFocusableInTouchMode(false);
					DSection_PointCount.setText(editInfo.getSurveyPnts());
					DSection_PointCount.setFocusableInTouchMode(false);
				}
				else {
					// �����ı���ֵʱ��
					DSection_createtime.setText(time);
				}
			}
			else
			if (arg1 == 1) {
				DSection_Value1.setText(Curw.getGDLimitTotalSettlement().toString());
				DSection_Value2.setText(Curw.getGDLimitVelocity().toString());
				DSection_SetTime.setTextSize(11);
				DSection_SetTime.setText(time);
			}
            
            return mListViews.get(arg1);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == (arg1);
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
        }
    }

    /**
     * ͷ��������
     */
    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            mPager.setCurrentItem(index);
        }
    };

    /**
     * ҳ���л�����
     */
    public class MyOnPageChangeListener implements OnPageChangeListener {

        int one = offset * 2 + bmpW;// ҳ��1 -> ҳ��2 ƫ����
        int two = one * 2;// ҳ��1 -> ҳ��3 ƫ����

        @Override
        public void onPageSelected(int arg0) {
            Animation animation = null;
            switch (arg0) {
            case 0:
                if (currIndex == 1) {
                    animation = new TranslateAnimation(one, 0, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, 0, 0, 0);
                }
                break;
            case 1:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, one, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, one, 0, 0);
                }
                break;
            }
            currIndex = arg0;
            animation.setFillAfter(true);// True:ͼƬͣ�ڶ�������λ��
            animation.setDuration(300);
            cursor.startAnimation(animation);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }

}
