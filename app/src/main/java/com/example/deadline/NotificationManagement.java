package com.example.deadline;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.deadline.DataBase.DataBaseManager;
import com.example.deadline.DataBase.UserDataset;

import java.util.Calendar;

public class NotificationManagement {

    Context context;

    NotificationManager notificationManager;
    NotificationCompat.Builder builder;
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

        DataBaseManager dataBaseManager = new DataBaseManager(context);

        String LongContent = "";

        Long PointDay, startDay;
        Calendar Point = Calendar.getInstance();
        Calendar start = Calendar.getInstance();
        for (int i = 0; i < dataBaseManager.getDate().size(); i++){//UserDataset userDataset : dataBaseManager.getDate()
            UserDataset userDataset = dataBaseManager.getDate().get(i);

            Point.set(userDataset.getY(), userDataset.getM(), userDataset.getD(), userDataset.getTime_h(), userDataset.getTime_m(), 0);

            PointDay = Point.getTimeInMillis();
            startDay = start.getTimeInMillis();


            if(userDataset.getY() == start.get(Calendar.YEAR) && userDataset.getM() == start.get(Calendar.MONTH) && userDataset.getD() == start.get(Calendar.DATE)){
                LongContent += userDataset.getTitle() + "(D-Day)";
            }else {
                LongContent += userDataset.getTitle() + "(D-" + (PointDay - startDay) / (24 * 60 * 60 * 1000) + ")";
            }

            if(i < dataBaseManager.getDate().size() - 1) LongContent += "\n";
        }

        builder = new NotificationCompat.Builder(context, "IMPORTANCE_LOW");

        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setTicker("알람 간단한 설명");
        builder.setContentTitle("목록");
        if(dataBaseManager.getDate().size() > 0)
            builder.setContentText(dataBaseManager.getDate().get(0).getTitle());
        else
            builder.setContentText("목록이 없습니다.");
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(LongContent));
        builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        builder.setPriority(0);
        builder.setDefaults(Notification.PRIORITY_HIGH);
        builder.setOngoing(true);//알림 못지우기

        notificationManager.notify(0, builder.build());
    }

    public void AlarmShow(int level, String Title, String Content){
        switch (level){
            case 0:{
                builder = new NotificationCompat.Builder(context, "IMPORTANCE_DEFAULT");//일반 알림
                break;
            }
            case 1:{
                builder = new NotificationCompat.Builder(context, "IMPORTANCE_HIGH");//중요 알림
                break;
            }
        }

        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setTicker("알람 간단한 설명");
        builder.setContentTitle(Title);
        builder.setContentText(Content);
        builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        builder.setPriority(0);
        builder.setDefaults(Notification.PRIORITY_HIGH);
        builder.setOngoing(false);//알림 못지우기

        notificationManager.notify(1, builder.build());
    }

}
