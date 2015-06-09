package com.syiyi.framework.demo;

import com.syiyi.framework.demo.service.ShortCommunicationService;
import com.syiyi.framework.demo.service.ShortCommunicationService.ChangeNumListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author user 短程通信 activity调用本地service 更新数字，最低级的，好无聊
 * 
 */
public class ShortCommunicationActivity extends Activity implements
		OnClickListener, ChangeNumListener {
	private TextView tv_num;
	private Button btn_start;
	private Button btn_stop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_short_communication);
		setViewID();
		setListenter();
		init();
	}

	private void setListenter() {
		btn_start.setOnClickListener(this);
		btn_stop.setOnClickListener(this);

	}

	private void init() {
		ShortCommunicationService.setChangeNumListener(this);
	}

	private void setViewID() {
		tv_num=(TextView) findViewById(R.id.tv_num);
		btn_start = (Button) findViewById(R.id.btn_start);
		btn_stop = (Button) findViewById(R.id.btn_stop);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(ShortCommunicationActivity.this,
				ShortCommunicationService.class);
		switch (v.getId()) {
		case R.id.btn_start:
			startService(intent);
			break;
		case R.id.btn_stop:
			stopService(intent);
			break;
		}

	}
	
	@Override
	public void changeNum(int num) {
		tv_num.setText("哈哈:" +num + "所有人快乐每一天");

	}

}
