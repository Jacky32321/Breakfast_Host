package com.example.test01;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.test01.Common.Common;
import com.example.test01.Interface.ItemClickListener;
import com.example.test01.Model.Food;

import com.example.test01.Model.Request;
import com.example.test01.ViewHolder.FoodViewHolder;
import com.example.test01.ViewHolder.StaticViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class StatisticList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference FoodList;
    DatabaseReference HistoryList;

    Map<String, Integer> statictisMap = new HashMap<>();

    String categoryId="";

    FirebaseRecyclerAdapter<Food, StaticViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic_list);
        //Firebase
        database= FirebaseDatabase.getInstance();
        FoodList=database.getReference("Food");
        HistoryList=database.getReference("History");
        //Init
        recyclerView=(RecyclerView)findViewById(R.id.recycler_statisticList);
        recyclerView.setHasFixedSize(true);
        layoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        HistoryList.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    DataSnapshot foods = ds.child("foods");
                    for(DataSnapshot fs : foods.getChildren())
                    {
                        //從history中取得所有餐點資料,並加到map中
                        if(statictisMap.containsKey(fs.child("productName").getValue(String.class))==false)
                             statictisMap.put(fs.child("productName").getValue(String.class),
                                     Integer.valueOf(fs.child("quantity").getValue(String.class)));
                        else
                            statictisMap.put(fs.child("productName").getValue(String.class),
                                    statictisMap.get(fs.child("productName").getValue(String.class))
                                            + Integer.valueOf(fs.child("quantity").getValue(String.class)));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if(getIntent()!=null)
            categoryId = getIntent().getStringExtra("CategoryId");

        if(!categoryId.isEmpty()) {
            loadStatisticFood(categoryId);
        }

    }

    private void loadStatisticFood(String categoryId) {

        adapter=new FirebaseRecyclerAdapter<Food, StaticViewHolder>(
                Food.class,
                R.layout.statistic_layout,
                StaticViewHolder.class,
                FoodList.orderByChild("menuId").equalTo(categoryId)
        ) {

            @Override
            protected void populateViewHolder(StaticViewHolder viewHolder, final Food model, int position) {
                int statistic=0;
                viewHolder.statisticFood_name.setText(model.getName()+":");
                if(statictisMap.containsKey(model.getName()))
                    statistic=statictisMap.get(model.getName());
                else
                    statistic=0;
                viewHolder.statistic.setText("   已售出 " + statistic + " 份");

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                    }
                });
            }
        };

        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }
}
