package com.sxlc.activity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.sxlc.common.Constant;
import com.sxlc.common.Constant.TotalStationType;
import com.sxlc.dao.impl.RecordDaoImpl;
import com.sxlc.dao.impl.TotalStationDaoImpl;
import com.sxlc.entity.RecordInfo;
import com.sxlc.entity.TotalStationInfo;
import com.sxlc.entity.WorkInfos;
import com.sxlc.utils.Time;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * �½�ȫվ�Ǵ���
 * 
 * @author ��
 * 
 */
public class ControlNewActivity extends Activity implements OnClickListener {
	private TextView ts_new_tv_header;
	
	private Spinner pps, xyws, btls, cks;
	private EditText pp, name, xyw, btl, ck, sjw, tzw;
	private ArrayAdapter adapter;
	private List<String> pplist = null;
	private List<String> xylist = null;
	private List<String> btllist = null;
	private List<String> cklist = null;
	private TotalStationInfo editInfo = null;
	/** ȷ����ť */
	private Button section_btn_queding;
	/** ȡ����ť */
	private Button section_btn_quxiao;

	private CRTBTunnelMonitor CurApp = null;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_controlnew);
		
		CurApp = ((CRTBTunnelMonitor)getApplicationContext());
		
		pplist = new ArrayList<String>();
		xylist = new ArrayList<String>();
		btllist = new ArrayList<String>();
		cklist = new ArrayList<String>();

		editInfo = (TotalStationInfo)getIntent().getExtras().getParcelable(Constant.Select_TotalStationRowClickItemsName_Data);
		
		initUI();
		name.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				 EditText _v=(EditText)v;
			        if (!hasFocus) {// ʧȥ����
			            _v.setHint(_v.getTag().toString());
			        } else {
			            String hint=_v.getHint().toString();
			            _v.setTag(hint);
			            _v.setHint("");
			        }
			}
		});
		adap(pps,pplist);
		adap(xyws,xylist);
		adap(btls,btllist);
		adap(cks,cklist);
		onCli();
		
		initData();
	}

	private void initUI() {
		for (TotalStationType type : TotalStationType.values()){
			pplist.add(type.getDesc());
		}
		
		xylist.add("0");
		xylist.add("1");
		xylist.add("2");
		
		btllist.add("19200");
		btllist.add("9600");
		btllist.add("4800");
		btllist.add("2400");
		btllist.add("1200");
		
		cklist.add("COM1");
		cklist.add("COM2");
		cklist.add("COM3");
		
		ts_new_tv_header = (TextView) findViewById(R.id.ts_new_tv_header);
		
		pps = (Spinner) findViewById(R.id.pps);
		xyws = (Spinner) findViewById(R.id.xyws);
		btls = (Spinner) findViewById(R.id.btls);
		cks = (Spinner) findViewById(R.id.cks);
		pp = (EditText) findViewById(R.id.pp);
		name = (EditText) findViewById(R.id.name);
		xyw = (EditText) findViewById(R.id.xyw);
		btl = (EditText) findViewById(R.id.btl);
		ck = (EditText) findViewById(R.id.ck);
		sjw = (EditText) findViewById(R.id.sjw);
		tzw = (EditText) findViewById(R.id.tzw);
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
			IntentCancel.putExtra(Constant.Select_TotalStationRowClickItemsName_Name,"");
			setResult(RESULT_CANCELED, IntentCancel);
			this.finish();// �رյ�ǰ����
			break;
		case R.id.work_btn_queding: // ���ݿ�
			if(pp.getText().toString().trim().length() <= 0)
			{
				Toast.makeText(this, "������������Ϣ", 3000).show();
				return;
			}
			if(name.getText().toString().trim().length() <= 0)
			{
				Toast.makeText(this, "������������Ϣ", 3000).show();
				return;
			}
			WorkInfos Curw = CurApp.GetCurWork();
			TotalStationInfo ts = new TotalStationInfo();
			if (editInfo != null) {
				ts.setId(editInfo.getId());
			}
			for (TotalStationType type : TotalStationType.values()){
				if(type.getDesc().equals(pp.getText().toString()))
				{
					ts.setTotalstationType(type.name());
					break;					
				}
			}
			ts.setName(name.getText().toString().trim());
			ts.setParity(Integer.valueOf(xyw.getText().toString().trim()));
			ts.setBaudRate(Integer.valueOf(btl.getText().toString().trim()));
			ts.setPort(cklist.indexOf(ck.getText().toString().trim()));
			ts.setDatabits(Integer.valueOf(sjw.getText().toString().trim()));
			ts.setStopbits(Integer.valueOf(tzw.getText().toString().trim()));

			if(!CurApp.IsValidTotalStationInfo(ts))
			{
				Toast.makeText(this, "������������Ϣ", 3000).show();
				return;
			}
			List<TotalStationInfo> tsinfos = null;
			tsinfos = Curw.getTsList();
			if(tsinfos == null)
			{
				Toast.makeText(this, "���ʧ��", 3000).show();
			}
			else
			{
				if(editInfo == null)
				{
					TotalStationDaoImpl impl = new TotalStationDaoImpl(this,Curw.getProjectName());
					if(impl.InsertTotalStation(ts))
					{
						tsinfos.add(ts);
						CurApp.UpdateWork(Curw);
						Toast.makeText(this, "��ӳɹ�", 3000).show();
					}
					else
					{
						Toast.makeText(this, "���ʧ��", 3000).show();
					}
				}
				else
				{
					TotalStationDaoImpl impl = new TotalStationDaoImpl(this,Curw.getProjectName());
					impl.UpdateTotalStation(ts);
					Curw.UpdateTotalStationInfo(ts);
					CurApp.UpdateWork(Curw);
					Toast.makeText(this, "�༭�ɹ�", 3000).show();
				}
			}
			Intent IntentOk = new Intent();
			IntentOk.putExtra(Constant.Select_TotalStationRowClickItemsName_Name,"");
			setResult(RESULT_OK, IntentOk);
			this.finish();
			break;
		default:
			break;
		}

	}

	private void initData() {
		if (editInfo != null) {
			ts_new_tv_header.setText("�༭ȫվ��");
			for (TotalStationType type : TotalStationType.values()){
				if(type.name().equals(editInfo.getTotalstationType()))
				{
					pps.setSelection(pplist.indexOf(type.getDesc()));
					break;					
				}
			}
			name.setText(editInfo.getName());
			xyws.setSelection(xylist.indexOf(Integer.toString(editInfo.getParity())));
			btls.setSelection(btllist.indexOf(Integer.toString(editInfo.getBaudRate())));
			cks.setSelection(editInfo.getPort());
			sjw.setText(Integer.toString(editInfo.getDatabits()));
			tzw.setText(Integer.toString(editInfo.getStopbits()));
		}
		else {
			ts_new_tv_header.setText("�½�ȫվ��");
			xyws.setSelection(xylist.indexOf("1"));
			btls.setSelection(btllist.indexOf("19200"));
			cks.setSelection(0);
			sjw.setText("8");
			tzw.setText("1");
		}
	}
	
	private void adap(Spinner spinner, List<String> list) {
		// ����ѡ������ArrayAdapter��������
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		// ���������б�ķ��
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
	}

	private void onCli() {
		// ����¼�Spinner�¼�����
		pps.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				pp.setText(pplist.get(position).toString());
			}
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		pps.setVisibility(View.VISIBLE);

		// ����¼�Spinner�¼�����
		xyws.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				xyw.setText(xylist.get(position).toString());
			}

			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		// ����¼�Spinner�¼�����
		btls.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				btl.setText(btllist.get(position).toString());
			}

			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		btls.setVisibility(View.VISIBLE);

		// ����¼�Spinner�¼�����
		cks.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				ck.setText(cklist.get(position).toString());
			}

			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		cks.setVisibility(View.VISIBLE);
	}
}
