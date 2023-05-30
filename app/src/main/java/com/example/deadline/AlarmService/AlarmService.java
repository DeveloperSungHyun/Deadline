package com.example.deadline.AlarmService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.deadline.NotificationManagement;

public class AlarmService extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("test", "==================");

        NotificationManagement notificationManagement = new NotificationManagement(context);
        notificationManagement.AlarmShow(1, intent.getStringExtra("Title"), "수고하셨습니다");
    }
}
