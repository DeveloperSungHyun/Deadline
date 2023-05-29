package com.example.deadline;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.deadline.SystemSettingsValue.SystemValue;

public class SystemSettings extends AppCompatActivity {

    LinearLayout LinearLayout_theme;
    TextView TextView_Theme;
    SystemValue systemValue;

    int theme_num;
    String theme_list[] = {"시스템 모드", "라이트 모드", "다크 모드"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.system_settings_view);

        LinearLayout_theme = findViewById(R.id.LinearLayout_theme);
        TextView_Theme = findViewById(R.id.TextView_Theme);

        systemValue = new SystemValue(getApplicationContext());

        TextView_Theme.setText(theme_list[systemValue.getTheme()]);
        LinearLayout_theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(SystemSettings.this);
                builder.setTitle("테마선택");

                builder.setSingleChoiceItems(theme_list, systemValue.getTheme(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        theme_num = which;
                    }
                });
                builder.setPositiveButton("완료", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (theme_num){
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

                        systemValue.setTheme(theme_num);//데이터 저장
                        TextView_Theme.setText(theme_list[systemValue.getTheme()]);
                    }
                });
                builder.show();
            }
        });
    }
}
