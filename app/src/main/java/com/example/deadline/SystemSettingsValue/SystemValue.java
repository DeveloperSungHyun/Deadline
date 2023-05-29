package com.example.deadline.SystemSettingsValue;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.deadline.R;

public class SystemValue {
    Context context;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    public SystemValue(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("Deadline", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    public void setTheme(int theme_num){
        editor.putInt("theme", theme_num);
        editor.commit();
    }
    public int getTheme(){
        return sharedPreferences.getInt("theme", 0);
    }
}
