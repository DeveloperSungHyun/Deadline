package com.example.deadline.SystemSettingsValue;

import android.content.Context;

import androidx.appcompat.app.AppCompatDelegate;

public class StartSetting {
    Context context;
    public StartSetting(Context context){
        this.context = context;

    }

    public void Setting_Theme(){
        SystemValue systemValue = new SystemValue(context);

        switch (systemValue.getTheme()){
            case 0:{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;
            }
            case 1:{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            }
            case 2:{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        }
    }
}
