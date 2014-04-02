package com.sxlc.mydefine;



import com.sxlc.activity.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.TextView;

/**
 * �Զ���Ի���
 * @author   ������
 * ����ʱ��  2013-12-7   ����1:58:09
 * @version  1.0
 * @since     JDK  1.6
 */
public class UserDefinedDialog extends Dialog implements android.view.View.OnClickListener {
	/**������*/
	private Context ctx;
	/**��Ϣ*/
	private String msg;
	// ��ť
	private Button btnleft;
	private Button btncenter;
	private Button btnright;
	/**�ж�*/
	private boolean IsTwoButton = false;
	/**ȷ����ť*/
	private View.OnClickListener okListener;
	/**ȡ����ť*/
	private View.OnClickListener cancelListener;
	/**�Ի������*/
	private TextView tvtitle;
	/**�Ի�������*/
	private TextView tvcontent;

	public UserDefinedDialog(Context context, String message,View.OnClickListener onclicklistener,View.OnClickListener cancelListener) {
		super(context,R.style.Theme_Dialog);
		this.ctx = context;
		this.msg = message;
		if (onclicklistener != null) {
			this.okListener=onclicklistener;
		}
		if(cancelListener != null){
			IsTwoButton=true;
			this.cancelListener=cancelListener;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.alertdialog);
		
		
		tvtitle = (TextView) findViewById(R.id.dialogtitle);
		tvcontent = (TextView) findViewById(R.id.dialogcontent);

		btnleft = (Button) findViewById(R.id.btnleft);
		btnright = (Button) findViewById(R.id.btnright);

		btnleft.setOnClickListener(this);
		btnright.setOnClickListener(this);

		btncenter = (Button) findViewById(R.id.btncenter);
		btncenter.setOnClickListener(this);
		if(IsTwoButton){
			btnleft.setVisibility(View.VISIBLE);
			btnright.setVisibility(View.VISIBLE);
			btncenter.setVisibility(View.GONE);
		}
		tvtitle.setText("��ʾ");
		tvcontent.setText(msg);

		WindowManager m = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
		Display d = m.getDefaultDisplay();
		LayoutParams p = getWindow().getAttributes();
//		p.height = (int) (d.getHeight() * 0.3);
		p.width = (int) (d.getWidth() * 0.9);
		//p.alpha = 0.8f;
		//p.dimAmount = 0.0f;
		getWindow().setAttributes(p);
		getWindow().setGravity(Gravity.CENTER);
		
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnleft:
			if(cancelListener != null){
				cancelListener.onClick(v);
			}
			break;
		case R.id.btncenter:
			if(okListener != null){
				okListener.onClick(v);
			}
			break;
		case R.id.btnright:
			if(okListener != null){
				okListener.onClick(v);
			}
			break;
			
		}
		this.cancel();
	}
}
