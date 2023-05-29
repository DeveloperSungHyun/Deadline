package com.example.deadline;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.deadline.DataBase.DataBaseManager;
import com.example.deadline.DataBase.UserDataBase;
import com.example.deadline.DataBase.UserDataDao;
import com.example.deadline.DataBase.UserDataset;
import com.example.deadline.SystemSettingsValue.StartSetting;
import com.example.deadline.SystemSettingsValue.SystemValue;
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

        ImageView_SettingsButton = findViewById(R.id.ImageView_SettingsButton);
        recyclerView = findViewById(R.id.recyclerView);
        FloatingButton_add = findViewById(R.id.FloatingButton_add);



        recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(scrollY > oldScrollY){//스크롤을 내린다면
                    FloatingButton_add.hide();
                }
                if(scrollY < oldScrollY){//스크롤을 내린다면
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