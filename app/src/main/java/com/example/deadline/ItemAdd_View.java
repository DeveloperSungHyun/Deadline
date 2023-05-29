package com.example.deadline;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.deadline.DataBase.DataBaseManager;
import com.example.deadline.DataBase.UserDataset;
import com.example.deadline.SystemSettingsValue.StartSetting;

import java.util.Calendar;

public class ItemAdd_View extends AppCompatActivity {
    DataBaseManager dataBaseManager;
    Intent Intent_getData;

    Spinner Spinner_Type;
    CalendarView CalendarView_Data;
    EditText EditText_Title, EditText_Memo;

    TextView TextView_TimeSetting;

    TextView TextView_DataSave;

    ArrayAdapter<String> Type_list_ad;

    Calendar calendar;

    private int Type;
    private String Title;
    private String Memo;
    private int Data_Y, Data_M, Data_D;
    private int Time_h, Time_m;


    private int ItemNumber = -1;//추가 = -1, 수정 0 < n

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemadd_view);

        calendar = Calendar.getInstance();

        if(ItemNumber == -1) {
            Data_Y = calendar.get(Calendar.YEAR);
            Data_M = calendar.get(Calendar.MONTH);
            Data_D = calendar.get(Calendar.DATE);

            Time_h = calendar.get(Calendar.HOUR);
            Time_m = calendar.get(Calendar.MINUTE);
        }


        dataBaseManager = new DataBaseManager(getApplicationContext());

        Spinner_Type = findViewById(R.id.Spinner_Type);
        CalendarView_Data = findViewById(R.id.CalendarView_Data);
        EditText_Title = findViewById(R.id.EditText_Title);
        EditText_Memo = findViewById(R.id.EditText_Memo);
        TextView_TimeSetting = findViewById(R.id.TextView_TimeSetting);

        TextView_DataSave = findViewById(R.id.TextView_DataSave);

        Type_list_ad = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);

        Type_list_ad.add("시험");
        Type_list_ad.add("자격증");
        Type_list_ad.add("공모전");
        Type_list_ad.add("대회");
        Type_list_ad.add("프로젝트");
        Type_list_ad.add("과제");
        Type_list_ad.add("기타");

        Spinner_Type.setAdapter(Type_list_ad);


        Spinner_Type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Type = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        CalendarView_Data.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Data_Y = year;
                Data_M = month;
                Data_D = dayOfMonth;

            }
        });

        TextView_TimeSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(ItemAdd_View.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Time_h = hourOfDay;
                        Time_m = minute;

                        TextView_TimeSetting.setText(Time_h + " : " + Time_m);
                    }
                }, Time_h, Time_m, false);
                timePickerDialog.show();
            }
        });


        TextView_DataSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//======================================확이버튼을 눌렀을때
                Title = EditText_Title.getText().toString();
                Memo = EditText_Memo.getText().toString();

                if(!Title.equals("")){
                    if(ItemNumber == -1) {
                        dataBaseManager.setInsert(Type, Title, Memo, Data_Y, Data_M, Data_D, Time_h, Time_m);
                    }else{
                        dataBaseManager.setUpData(dataBaseManager.getDate().get(ItemNumber).getId(), Type, Title, Memo, Data_Y, Data_M, Data_D, Time_h, Time_m);
                    }

                    NotificationManagement notificationManagement = new NotificationManagement(getApplicationContext());
                    String LongContent = "";

                    Long PointDay, startDay;
                    Calendar Point = Calendar.getInstance();
                    Calendar start = Calendar.getInstance();
                    for (int i = 0; i < dataBaseManager.getDate().size(); i++){//UserDataset userDataset : dataBaseManager.getDate()
                        UserDataset userDataset = dataBaseManager.getDate().get(i);

                        Point.set(userDataset.getY(), userDataset.getM(), userDataset.getD(), userDataset.getTime_h(), userDataset.getTime_m());

                        PointDay = Point.getTimeInMillis();
                        startDay = start.getTimeInMillis();

                        LongContent += userDataset.getTitle() + "(D-" + (PointDay - startDay) / (24 * 60 * 60 * 1000) + ")";
                        if(i < dataBaseManager.getDate().size() - 1) LongContent += "\n";
                    }
                    notificationManagement.All_ListShow("Title", dataBaseManager.getDate().get(0).getTitle(), LongContent);

                    finish();
                }else{
                    Toast.makeText(ItemAdd_View.this, "타이틀을 입력해 주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent_getData = getIntent();

        StartSetting startSetting = new StartSetting(getApplicationContext());
        startSetting.Setting_Theme();

        ItemNumber = Intent_getData.getIntExtra("ItemNumber", -1);

    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("ItemNumber", ItemNumber + "");

        CalendarView_Data.setMinDate(System.currentTimeMillis());

        if(ItemNumber >= 0){
            UserDataset dataset = dataBaseManager.getDate().get(ItemNumber);
            Log.d("getTitle", dataset.getTitle() + "");

            Type = dataset.getType();
            Title = dataset.getTitle();
            Memo = dataset.getMemo();
            Data_Y = dataset.getY();
            Data_M = dataset.getM();
            Data_D = dataset.getD();
            Time_h = dataset.getTime_h();
            Time_m = dataset.getTime_m();

            //==========================================
            //

            calendar.set(Data_Y, Data_M, Data_D);

            Spinner_Type.setSelection(Type);
            EditText_Title.setText(Title);
            EditText_Memo.setText(Memo);
            CalendarView_Data.setDate(calendar.getTimeInMillis());
            TextView_TimeSetting.setText(Time_h + " : " + Time_m);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
