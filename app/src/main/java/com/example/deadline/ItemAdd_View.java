package com.example.deadline;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;

public class ItemAdd_View extends Activity {

    Spinner Spinner_Type;
    CalendarView CalendarView_Data;
    EditText EditText_Title, EditText_Memo;

    ArrayAdapter<String> Type_list_ad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemadd_view);

        Spinner_Type = findViewById(R.id.Spinner_Type);
        CalendarView_Data = findViewById(R.id.CalendarView_Data);
        EditText_Title = findViewById(R.id.EditText_Title);
        EditText_Memo = findViewById(R.id.EditText_Memo);

        Type_list_ad = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);

        Type_list_ad.add("시험");
        Type_list_ad.add("자격증");
        Type_list_ad.add("공모전");
        Type_list_ad.add("대회");
        Type_list_ad.add("프로젝트");
        Type_list_ad.add("과제");
        Type_list_ad.add("기타");

        Spinner_Type.setAdapter(Type_list_ad);
    }
}
