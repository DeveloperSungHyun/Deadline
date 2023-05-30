package com.example.deadline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.deadline.AlarmService.AlarmManagement;
import com.example.deadline.DataBase.DataBaseManager;
import com.example.deadline.DataBase.UserDataset;
import com.example.deadline.SystemSettingsValue.StartSetting;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Intent intent;

    DataBaseManager dataBaseManager;
    List<UserDataset> date;

    ImageView ImageView_SettingsButton;

    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;

    ArrayList<ItemList_View> itemList_views;

    FloatingActionButton FloatingButton_add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int permission = ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.POST_NOTIFICATIONS);
        if (permission == PackageManager.PERMISSION_DENIED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 1000);
            }
            return;
        }

        ImageView_SettingsButton = findViewById(R.id.ImageView_SettingsButton);
        recyclerView = findViewById(R.id.recyclerView);
        FloatingButton_add = findViewById(R.id.FloatingButton_add);



        recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(scrollY > oldScrollY){//스크롤을 내린다면
                    FloatingButton_add.hide();
                }
                if(scrollY < oldScrollY){//스크롤을 올린다면
                    FloatingButton_add.show();
                }


            }
        });

        FloatingButton_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, ItemAdd_View.class);
                startActivity(intent);
            }
        });



        ImageView_SettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, SystemSettings.class);
                startActivity(intent);
            }
        });


        AlarmManagement alarmManagement = new AlarmManagement(getApplicationContext());
        alarmManagement.DayLoop();
        alarmManagement.CheckAlarm();
    }


    @Override
    protected void onStart() {
        super.onStart();

        StartSetting startSetting = new StartSetting(getApplicationContext());
        startSetting.Setting_Theme();

        dataBaseManager = new DataBaseManager(getApplicationContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        itemList_views = new ArrayList<>();

        itemList_views.clear();

        for (UserDataset dataset : dataBaseManager.getDate()){
            itemList_views.add(new
                    ItemList_View(0, dataset.getTitle(), dataset.getMemo(), dataset.getY(), dataset.getM(), dataset.getD(), dataset.getTime_h(), dataset.getTime_m(), 10));
        }

        recyclerViewAdapter = new RecyclerViewAdapter(itemList_views, getApplicationContext());
        recyclerView.setAdapter(recyclerViewAdapter);

    }
}