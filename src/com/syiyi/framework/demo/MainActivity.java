package com.syiyi.framework.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity  {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	public void onClickShortCommunication(View v){
		Intent intent=new Intent(MainActivity.this,	ShortCommunicationActivity.class);
		startActivity(intent);
	}
	public void onClickRemoteCommunication(View v){
		Intent intent=new Intent(MainActivity.this,	RemoteCommunicationActivity.class);
		startActivity(intent);
	}
}
