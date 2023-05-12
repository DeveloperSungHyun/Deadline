package com.example.deadline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.deadline.DataBase.DataBaseManager;
import com.example.deadline.DataBase.UserDataBase;
import com.example.deadline.DataBase.UserDataDao;
import com.example.deadline.DataBase.UserDataset;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;

    ArrayList<ItemList_View> itemList_views;

    FloatingActionButton FloatingButton_add;

    boolean recyclerView_Scroll = false;//true = up, false = down

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        FloatingButton_add = findViewById(R.id.FloatingButton_add);


        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        itemList_views = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            itemList_views.add(new ItemList_View("모바일 프로그래밍 프로젝트", "a", 2023, 5, 12, 0, 0));
        }

        recyclerViewAdapter = new RecyclerViewAdapter(itemList_views);

        recyclerView.setAdapter(recyclerViewAdapter);

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
                Intent intent = new Intent(MainActivity.this, ItemAdd_View.class);
                startActivity(intent);
            }
        });

        DataBaseManager dataBaseManager = new DataBaseManager(getApplicationContext());

        List<UserDataset> date = dataBaseManager.getDate();

        Toast.makeText(this, date.get(1).getTitle(), Toast.LENGTH_SHORT).show();
    }
}