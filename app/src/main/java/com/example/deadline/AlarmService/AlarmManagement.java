package com.example.deadline.AlarmService;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.deadline.DataBase.UserDataset;

import java.util.Calendar;

public class AlarmManagement {
    public static Intent intent;
    public static Context context;
    public static AlarmManager alarmManager;
    public static PendingIntent alarmIntent;

    public AlarmManagement(Context context){
        this.context = context;
    }

    public void Alarm_add(UserDataset userDataset, Calendar calendar){

        Intent Alarm_Intent = new Intent(context, AlarmService.class);
        Alarm_Intent.putExtra("Title", userDataset.getTitle());

        alarmManager=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        PendingIntent alarmIntent = PendingIntent.getBroadcast(context.getApplicationContext(), userDataset.getId(), Alarm_Intent, PendingIntent.FLAG_IMMUTABLE);
        AlarmManager.AlarmClockInfo ac = new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(), alarmIntent);
        alarmManager.setAlarmClock(ac, alarmIntent);

    }
}
