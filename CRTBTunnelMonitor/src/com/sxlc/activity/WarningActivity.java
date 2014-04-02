package com.sxlc.activity;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import com.sxlc.adapter.Myadapter;
import com.sxlc.entity.yujingInfors;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class WarningActivity extends Activity {
	
	private ListView listview;
	private	TextView baojing, yixiao;
	private	ArrayList<yujingInfors> listt;
	private	LinearLayout rela;
	private	Myadapter adapter;
	private	Random ran = new Random();
	private	String s[] = new String[20];
	private	String ss[] = { "����", "����s1", "����s2" };
	private	String sss[] = { "������", "���ڴ���" };
	private	String ssss[] = { "", "", "", "" };
	private	String s2[] = { "XXX����", "AAA����", "CCC����", "YYY����" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_warning);
		rela = (LinearLayout) findViewById(R.id.rela);
		// check1=(CheckBox) findViewById(R.id.checkBox1);
		// checkListen(check1);
		// check2=(CheckBox) findViewById(R.id.checkBox2);
		// checkListen(check2);
		initB();
		listviewInit();
		baojing = (TextView) findViewById(R.id.rizhi);
		baojing.setText("������־��(" + yujingInfors.count + ")" + ",");
		yixiao = (TextView) findViewById(R.id.yixiaojing);
		yixiao.setText("��������(" + yujingInfors.yixiao + ")");
	}

	public void checkListen(final CheckBox check) {
		check.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				switch (check.getId()) {
				case R.id.checkBox1:
					if (isChecked) {
						// check2.setChecked(false);
						for (int i = 0; i < listt.size(); i++) {
							if (listt.get(i).getState().equals("������")) {
								System.out.println(listt.get(i).getState()
										+ listt.size());
								listt.get(i).setState1(false);
							} else {
								listt.get(i).setState1(true);
							}
						}
						adapter.setList(listt);
						adapter.notifyDataSetChanged();
					}
					break;
				case R.id.checkBox2:
					if (isChecked) {
						// check1.setChecked(false);
						for (int i = 0; i < listt.size(); i++) {
							if (!listt.get(i).getState().equals("������")) {
								System.out.println(listt.get(i).getState()
										+ listt.size());
								listt.get(i).setState1(false);
							} else {
								listt.get(i).setState1(true);
							}
						}
						adapter.setList(listt);
						adapter.notifyDataSetChanged();
					}
					break;
				default:
					break;
				}

			}
		});
	}

	public void listviewInit() {
		listview = (ListView) findViewById(R.id.listView12);
		adapter = new Myadapter(WarningActivity.this, getdata());
		listt = (ArrayList<yujingInfors>) adapter.getList();
		listview.setAdapter(adapter);
		yujingInfors.count = adapter.getCount();

	}

	public void initB() {
		for (int i = 0; i < s.length; i++) {
			s[i] = "DK+"
					+ (10 + ran.nextInt(10))
					+ ((double) (Math.round(ran.nextDouble() + 100) / 100.0) + (ran
							.nextInt(100) + 100));
		}
	}

	public ArrayList<yujingInfors> getdata() {
		yujingInfors.yixiao = 0;
		ArrayList<yujingInfors> listt = new ArrayList<yujingInfors>();
		yujingInfors infor;
		for (int i = 0; i < s.length; i++) {
			infor = new yujingInfors();
			infor.setDate(getdate());
			infor.setXinghao(s[i]);
			infor.setDianhao(ss[ran.nextInt(3)]);
			infor.setChuliFangshi("���ɴ���");
			infor.setState(sss[ran.nextInt(2)]);
			infor.setMessage(s2[ran.nextInt(4)]);
			infor.setEdtState(ssss[ran.nextInt(4)]);
			infor.setState1(true);
			if (infor.getState().equals("������")) {
				yujingInfors.yixiao = yujingInfors.yixiao + 1;
				System.out.println(yujingInfors.yixiao);

			}
			listt.add(infor);
		}
		return listt;
	}

	public String getdate() {
		SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		return simp.format(new Date());
	}
}
