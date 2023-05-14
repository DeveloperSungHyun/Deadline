package com.example.deadline;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.deadline.DataBase.DataBaseManager;

public class ItemAdd_View extends Activity {

    Spinner Spinner_Type;
    CalendarView CalendarView_Data;
    EditText EditText_Title, EditText_Memo;

    TextView TextView_TimeSetting;

    TextView TextView_DataSave;

    ArrayAdapter<String> Type_list_ad;

    int Type;
    String Title;
    String Memo;
    int Data_Y, Data_M, Data_D;
    int Time_h, Time_m;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemadd_view);

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
                Data_M = month + 1;
                Data_D = dayOfMonth;

            }
        });

        TextView_TimeSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getApplicationContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Time_h = hourOfDay;
                        Time_m = minute;
                    }
                }, 0, 0, false);
            }
        });





        TextView_DataSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Title = EditText_Title.getText().toString();
                Memo = EditText_Memo.getText().toString();
                DataBaseManager dataBaseManager = new DataBaseManager(getApplicationContext());

                dataBaseManager.setInsert(Type, Title, Memo, Data_Y, Data_M, Data_D, Time_h, Time_m);
                finish();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
