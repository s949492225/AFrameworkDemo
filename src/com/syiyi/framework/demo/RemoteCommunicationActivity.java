package com.syiyi.framework.demo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.syiyi.framework.demo.service.RemoteCommunctionService;

/**
 * @author user 短程通信 activity调用本地service 更新数字，最低级的，好无聊
 */
public class RemoteCommunicationActivity extends Activity implements
        OnClickListener {
    private Button btn_play;
    private Button btn_stop;
    private Button btn_exit;

    private TextView textView;

    private IBinder binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_communication);
        setViewID();
        setListenter();
        init();
    }

    private void setListenter() {
        btn_play.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
        btn_exit.setOnClickListener(this);
    }

    private void init() {
        bindService(new Intent(this, RemoteCommunctionService.class), connection, Context.BIND_AUTO_CREATE);
    }

    private void setViewID() {
        btn_play = (Button) findViewById(R.id.btn_play);
        btn_stop = (Button) findViewById(R.id.btn_stop);
        btn_exit = (Button) findViewById(R.id.btn_exit);
        textView = (TextView) findViewById(R.id.textView);
    }

    @Override
    public void onClick(View v) {
        Parcel pc;
        switch (v.getId()) {
            case R.id.btn_play:
                pc = Parcel.obtain();
                Parcel pc_reply = Parcel.obtain();
                pc.writeString("playing");
                try {
                    binder.transact(1, pc, pc_reply, 0);
                    textView.setText(pc_reply.readString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_stop:
                pc = Parcel.obtain();
                pc_reply = Parcel.obtain();
                pc.writeString("stop");
                try {
                    binder.transact(2, pc, pc_reply, 0);
                    textView.setText(pc_reply.readString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_exit:
                finish();
                break;
        }

    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            binder = iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            binder = null;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
