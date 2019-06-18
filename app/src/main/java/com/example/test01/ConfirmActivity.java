package com.example.test01;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.test01.Common.Common;
import com.example.test01.Interface.ItemClickListener;
import com.example.test01.Model.OrderFood;
import com.example.test01.Model.Requests;
import com.example.test01.ViewHolder.ConfirmViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ConfirmActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;
    public Button btn;
    FirebaseDatabase database;
    DatabaseReference confirm;
    DatabaseReference history;
    DatabaseReference request;
    AppCompatSpinner spinner;
    FirebaseRecyclerAdapter<Requests, ConfirmViewHolder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        FirebaseApp.initializeApp(ConfirmActivity.this);
        database = FirebaseDatabase.getInstance();
        confirm = database.getReference("confirm");
        history = database.getReference("History");
        request = database.getReference("Request");
        recyclerView = (RecyclerView)findViewById(R.id.listConfirm);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadOrder();
    }

    private void loadOrder() {
        adapter = new FirebaseRecyclerAdapter<Requests, ConfirmViewHolder>(
                Requests.class,
                R.layout.order_layout,
                ConfirmViewHolder.class,
                confirm
        ) {
            @Override
            protected void populateViewHolder(ConfirmViewHolder viewHolder, Requests model, int position) {

                viewHolder.textOrderid.setText("訂單編號:" + adapter.getRef(position).getKey());
                viewHolder.textOrderName.setText("顧客:" + model.getName());
                viewHolder.textOrderphone.setText("顧客電話:" + model.getphone());
                viewHolder.textOrderPrice.setText("總金額:" + model.getTotal());
                viewHolder.textOrderStatus.setText("訂單狀態:" + Common.convertCodeToStatus(model.getStatus()));

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        // Toast.makeText(ConfirmActivity.this,"第幾筆訂單"+position,Toast.LENGTH_SHORT).show();

                        String key = adapter.getRef(position).getKey();
                        ClickItem(key);
                    }
                });
            }
        };
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }
    public void ClickItem(String key)
    {
        Intent intent = new Intent(this,OrderFoodList.class);
        intent.putExtra("localKey",key);
        startActivity(intent);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getTitle().equals("已完成"))
            OrderFinish(adapter.getRef(item.getOrder()).getKey(),adapter.getItem(item.getOrder()));
        return super.onContextItemSelected(item);
    }

    private void OrderFinish(String key, Requests item) {

        final String localkey = key;
        confirm.child(localkey).child("status").setValue("2");
        request.child(localkey).child("status").setValue("2");
        item.setStatus("2");
        history.child(localkey).setValue(item);

        confirm.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds: dataSnapshot.child(localkey).child("foods").getChildren())
                {
                    history.child(localkey).child("foods").push().setValue(ds.getValue(OrderFood.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        confirm.child(localkey).removeValue();
    }
}
