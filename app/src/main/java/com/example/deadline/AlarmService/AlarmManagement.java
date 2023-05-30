package com.example.deadline.AlarmService;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.TimePicker;

import com.example.deadline.DataBase.UserDataset;

import java.util.Calendar;

public class AlarmManagement {

    public static Intent intent;
    public static Context context;
    public static AlarmManager alarmManager;

    int TimerList[] = {8, 12, 19};

    public AlarmManagement(Context context){
        this.context = context;
    }

    public void Alarm_add(UserDataset userDataset, Calendar calendar){

        Intent Alarm_Intent = new Intent(context, AlarmService.class);
        Alarm_Intent.putExtra("Type", "Timer");

        Alarm_Intent.putExtra("Title", userDataset.getTitle());
        Alarm_Intent.putExtra("Id", userDataset.getId());

        alarmManager=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        PendingIntent alarmIntent = PendingIntent.getBroadcast(context.getApplicationContext(), userDataset.getId(), Alarm_Intent, PendingIntent.FLAG_IMMUTABLE);
        AlarmManager.AlarmClockInfo ac = new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(), alarmIntent);
        alarmManager.setAlarmClock(ac, alarmIntent);

    }

    public void DayLoop(){

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Intent Alarm_Intent = new Intent(context, AlarmService.class);
        Alarm_Intent.putExtra("Type", "Loop");

        alarmManager=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        PendingIntent alarmIntent = PendingIntent.getBroadcast(context.getApplicationContext(), 0, Alarm_Intent, PendingIntent.FLAG_IMMUTABLE);
        AlarmManager.AlarmClockInfo ac = new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(), alarmIntent);
        alarmManager.setAlarmClock(ac, alarmIntent);

    }

    public void CheckAlarm(){

        for (int i = 0; i < 3; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, TimerList[i]);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            Intent Alarm_Intent = new Intent(context, AlarmService.class);
            Alarm_Intent.putExtra("Type", "Check");

            alarmManager=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

            PendingIntent alarmIntent = PendingIntent.getBroadcast(context.getApplicationContext(), i - 4, Alarm_Intent, PendingIntent.FLAG_IMMUTABLE);
            AlarmManager.AlarmClockInfo ac = new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(), alarmIntent);
            alarmManager.setAlarmClock(ac, alarmIntent);
        }

    }
}
