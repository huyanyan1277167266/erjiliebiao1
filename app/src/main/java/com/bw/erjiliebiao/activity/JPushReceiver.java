package com.bw.erjiliebiao.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import cn.jpush.android.api.JPushInterface;

/*
 *@Auther:cln
 *@Date: 2020/4/8
 *@Time:14:34
 *@Description:
 * */public class JPushReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(JPushInterface.ACTION_NOTIFICATION_OPENED)){
            Bundle bundle = intent.getExtras();
            String t = bundle.getString(JPushInterface.EXTRA_EXTRA);
            String c = bundle.getString(JPushInterface.EXTRA_ALERT);
            Intent intent1 = new Intent(context, ShopActivity.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent1);
        }else if (intent.getAction().equals(JPushInterface.ACTION_NOTIFICATION_RECEIVED)){
            Bundle extras = intent.getExtras();
            String title = extras.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
            String message = extras.getString(JPushInterface.EXTRA_MESSAGE);
            Log.i("abb",""+title);
            Log.i("abb",""+message);
        }

    }
}
