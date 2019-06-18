package com.example.test01;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class UserDate extends AppCompatActivity {
    private Button dateButton;
    private Calendar calendar;
    private Button totalButton;
    private int mYear, mMonth, mDay;
    private TextView dateText;
    private DatePickerDialog datePickerDialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getuserdate);

        calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);

        dateText = (TextView)findViewById(R.id.dateText);
        dateButton = (Button)findViewById(R.id.dateButton);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(0);
                datePickerDialog.updateDate(mYear, mMonth, mDay);
            }

        });
        totalButton = (Button)findViewById(R.id.starttotal);
        totalButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent();
                intent.setClass(UserDate.this , Days_Total.class);
                String temp;
                temp = setDateFormat(mYear,mMonth,mDay);
                intent.putExtra("temp",temp);
                startActivity(intent);

            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month,
                                  int day) {
                mYear = year;
                mMonth = month;
                mDay = day;
                dateText.setText("你設定的日期為"+setDateFormat2(year,month,day));
            }

        }, mYear,mMonth, mDay);

        return datePickerDialog;
    }

    private String setDateFormat(int year,int monthOfYear,int dayOfMonth){
        return String.valueOf(year) +
                String.format("%02d",monthOfYear+1)+
                String.format("%02d",dayOfMonth);
    }
    private String setDateFormat2(int year,int monthOfYear,int dayOfMonth){
        return String.valueOf(year) + " - " +
                String.format("%02d",monthOfYear+1) + " - " +
                String.format("%02d",dayOfMonth);
    }
}
