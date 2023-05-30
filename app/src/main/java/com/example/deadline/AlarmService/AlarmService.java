package com.example.deadline.AlarmService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.deadline.DataBase.DataBaseManager;
import com.example.deadline.DataBase.UserDataset;
import com.example.deadline.NotificationManagement;

import java.util.Calendar;

public class AlarmService extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManagement notificationManagement = new NotificationManagement(context);
        switch (intent.getStringExtra("Type")){
            case "Loop":{
                notificationManagement.All_ListShow();
                Log.d("=================", "Loop");
                AlarmManagement alarmManagement = new AlarmManagement(context);
                alarmManagement.CheckAlarm();//매일 3번씩 울리는 알람 재 설정

                DataBaseManager dataBaseManager = new DataBaseManager(context);
                for(UserDataset userDataset : dataBaseManager.getDate()){//안전을 위한 재설정
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(userDataset.getY(), userDataset.getM(), userDataset.getD(), userDataset.getTime_h(), userDataset.getTime_m(), 0);
                    alarmManagement.Alarm_add(userDataset, calendar);
                }

                break;
            }
            case "Check":{
                notificationManagement.AlarmShow(0, "일하세요", "설마 놀고있는건 아니죠?");
                Log.d("=================", "Check");
                break;
            }
            case "Timer":{
                DataBaseManager dataBaseManager = new DataBaseManager(context);
                notificationManagement.AlarmShow(1, intent.getStringExtra("Title"), "수고하셨습니다");
                dataBaseManager.setDelete(intent.getIntExtra("Id", 0));
                notificationManagement.All_ListShow();
                break;
            }
        }
    }
}
