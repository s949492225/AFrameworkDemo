package com.syiyi.framework.demo.service;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.IBinder;
import android.os.Message;

public class ShortCommunicationService extends Service implements Callback {
	private Handler handler;
	public static  ChangeNumListener listener;
	private Timer timer=new Timer();
	private int num=0;
	
	@Override
	public void onCreate() {
		super.onCreate();
		handler=new Handler(this);
		TimerTask task=new TimerTask() {
			
			@Override
			public void run() {
				handler.sendEmptyMessage(0);
			}
		};
		timer.schedule(task,0, 1000);
	}
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	public interface ChangeNumListener{
		public abstract void changeNum(int num);
	}
	public static void setChangeNumListener(ChangeNumListener listener){
		ShortCommunicationService.listener=listener;
	}
	@Override
	public boolean handleMessage(Message msg) {
		if (msg.what==0) {
			listener.changeNum(num++);
		}
		return false;
	}
}
