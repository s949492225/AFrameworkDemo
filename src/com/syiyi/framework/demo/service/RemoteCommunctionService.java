package com.syiyi.framework.demo.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

public class RemoteCommunctionService extends Service {

    private IBinder mBinder = null;

    public RemoteCommunctionService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBinder = new MyBinder(getApplicationContext());
    }

    @Override
    public IBinder onBind(Intent intent) {

        return mBinder;
    }

    public class MyBinder extends Binder {

        private Context ctx;

        public MyBinder(Context cx) {
            ctx = cx;
        }

        @Override
        protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            reply.writeString(data.readString() + " mp3");
            if (code == 1)
                this.play();
            else if (code == 2)
                this.stop();
            return true;
        }

        public void play() {
            Log.e("Remote------","PLAY");
        }

        public void stop() {
            Log.e("Remote------","STOP");
        }

    }
}
