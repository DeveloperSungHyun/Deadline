package com.example.deadline;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationManagement {

    Context context;

    NotificationManager notificationManager;
    public NotificationManagement(Context context){
        this.context = context;

        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Channel_Add();
    }

    private void Channel_Add(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            //알림강도 "깅"
            NotificationChannel IMPORTANCE_HIGH = new NotificationChannel("IMPORTANCE_HIGH", "중요도 상", NotificationManager.IMPORTANCE_HIGH);//체널 생성
            IMPORTANCE_HIGH.setBypassDnd(true);
            IMPORTANCE_HIGH.setLightColor(0xFFFF0000);
            notificationManager.createNotificationChannel(IMPORTANCE_HIGH);

            NotificationChannel IMPORTANCE_DEFAULT = new NotificationChannel("IMPORTANCE_DEFAULT", "중요도 중", NotificationManager.IMPORTANCE_DEFAULT);//체널 생성
            IMPORTANCE_DEFAULT.setBypassDnd(true);
            IMPORTANCE_DEFAULT.setLightColor(0xFFFF0000);
            notificationManager.createNotificationChannel(IMPORTANCE_DEFAULT);

            NotificationChannel IMPORTANCE_LOW = new NotificationChannel("IMPORTANCE_LOW", "중요도 하", NotificationManager.IMPORTANCE_LOW);//체널 생성
            IMPORTANCE_LOW.setBypassDnd(true);
            IMPORTANCE_LOW.setLightColor(0xFFFF0000);
            notificationManager.createNotificationChannel(IMPORTANCE_LOW);

        }
    }

    public void All_ListShow(){
        NotificationCompat.Builder builder;
        builder = new NotificationCompat.Builder(context, "IMPORTANCE_LOW");

        builder.setSmallIcon(R.drawable.settings_icon);
        builder.setTicker("알람 간단한 설명");
        builder.setContentTitle("Title");
        builder.setContentText("Text");
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText("a\n a\n a\n"));
        builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        builder.setPriority(0);
        builder.setDefaults(Notification.PRIORITY_HIGH);
        builder.setOngoing(true);//알림 못지우기

        notificationManager.notify(0, builder.build());
    }
}
