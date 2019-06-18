package com.example.test01;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;

public class Days_Total extends AppCompatActivity {

    FirebaseDatabase mdatabase;
    DatabaseReference mreference;
    TextView texttotal,mydate;
    Calendar cal = Calendar.getInstance();
    String today = "";
    String v_year, v_month , v_day;
    private Button lastDay,nextDay;
    private TextView textView01;
    DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    Date date = new Date();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daystotal);
        Intent intent = this.getIntent();
        String temp = intent.getStringExtra("temp");
        today = temp;
        texttotal = (TextView) findViewById(R.id.total);
        mydate = (TextView) findViewById(R.id.mydatetext);
        v_year = today.substring(0,4);
        v_month = today.substring(4,6);
        v_day = today.substring(6,8);
        mydate.setText(v_year + " - " + v_month + " - " + v_day);
        ListView listView = (ListView) findViewById(R.id.total_list);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1);
        listView.setAdapter(adapter);
        lastDay = (Button)findViewById(R.id.Lastday);
        lastDay.setOnClickListener(new Button.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            // TODO Auto-generated method stub
                                            Date date = null;
                                            try{
                                                date = sdf.parse(today);
                                            }catch (ParseException e)
                                            {
                                                e.printStackTrace();;
                                            }
                                            cal.setTime(date);
                                            int day = cal.get(Calendar.DATE);
                                            cal.set(Calendar.DATE, day - 1);
                                            today = sdf.format(cal.getTime());
                                            v_year = today.substring(0,4);
                                            v_month = today.substring(4,6);
                                            v_day = today.substring(6,8);
                                            mydate.setText(v_year + " - " + v_month + " - " + v_day);
                                            mdatabase = FirebaseDatabase.getInstance();
                                            mreference = mdatabase.getReference("History");
                                            mreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                adapter.clear();
                int i=1,count=0;
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    String Thedate;
                    Thedate = ds.getKey().toString();
                    Thedate = Thedate.substring(0,8);
                    if(Thedate.compareTo(today)==0){
                        count += Integer.valueOf(ds.child("total").getValue().toString());
                        adapter.add(Integer.toString(i));
                        adapter.add("訂購人姓名 ：    "+ds.child("name").getValue().toString());
                        adapter.add("訂單總消費 ：    $"+ds.child("total").getValue().toString());
                        i++;
                    }
                }
                String string = Integer.toString(count);
                texttotal.setText(string);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
                                        }
                                    });
        nextDay = (Button) findViewById(R.id.Nextday);
        nextDay.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Date date = null;
                try{
                    date = sdf.parse(today);
                }catch (ParseException e)
                {
                    e.printStackTrace();;
                }
                cal.setTime(date);
                int day = cal.get(Calendar.DATE);
                cal.set(Calendar.DATE, day + 1);
                today = sdf.format(cal.getTime());
                v_year = today.substring(0,4);
                v_month = today.substring(4,6);
                v_day = today.substring(6,8);
                mydate.setText(v_year + " - " + v_month + " - " + v_day);
                mdatabase = FirebaseDatabase.getInstance();
                mreference = mdatabase.getReference("History");
                mreference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        adapter.clear();
                        int i=1,count=0;
                        for(DataSnapshot ds: dataSnapshot.getChildren()){
                            String Thedate;
                            Thedate = ds.getKey().toString();
                            Thedate = Thedate.substring(0,8);
                            if(Thedate.compareTo(today)==0){
                                count += Integer.valueOf(ds.child("total").getValue().toString());
                                adapter.add(Integer.toString(i));
                                adapter.add("訂購人姓名 ：    "+ds.child("name").getValue().toString());
                                adapter.add("訂單總消費 ：    $"+ds.child("total").getValue().toString());
                                i++;
                            }
                        }
                        String string = Integer.toString(count);
                        texttotal.setText(string);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        mdatabase = FirebaseDatabase.getInstance();
        mreference = mdatabase.getReference("History");
        mreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                adapter.clear();
                int i=1,count=0;
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    String Thedate;
                    Thedate = ds.getKey().toString();
                    Thedate = Thedate.substring(0,8);
                    if(Thedate.compareTo(today)==0){
                        count += Integer.valueOf(ds.child("total").getValue().toString());
                        adapter.add(Integer.toString(i));
                        adapter.add("訂購人姓名 ：    "+ds.child("name").getValue().toString());
                        adapter.add("訂單總消費 ：    $"+ds.child("total").getValue().toString());
                        i++;
                    }
                }
                String string = Integer.toString(count);
                texttotal.setText(string);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
