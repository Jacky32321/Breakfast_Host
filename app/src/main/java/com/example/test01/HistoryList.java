package com.example.test01;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.test01.Common.Common;
import com.example.test01.Interface.ItemClickListener;
import com.example.test01.Model.Category;
import com.example.test01.ViewHolder.HistoryViewHolder;
import com.example.test01.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.squareup.picasso.Picasso;

import java.util.UUID;

import static com.example.test01.Common.Common.PICK_IMAGE_REQUEST;

public class HistoryList extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference categories;
    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseRecyclerAdapter<Category, HistoryViewHolder>adapter;
    //View
    RecyclerView recycler_historyList;
    RecyclerView.LayoutManager layoutManager;

    Category newCategory;

    Uri saveUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);

        //Init Firebase
        database= FirebaseDatabase.getInstance();
        categories =database.getReference("Category");

        storage= FirebaseStorage.getInstance();
        storageReference=storage.getReference();

        //Init View
        recycler_historyList=(RecyclerView)findViewById(R.id.recycler_historyList);
        recycler_historyList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_historyList.setLayoutManager(layoutManager);

        loadMenu();

    }
    private void loadMenu() {
        adapter = new FirebaseRecyclerAdapter<Category, HistoryViewHolder>(
                Category.class,
                R.layout.menu_item,
                HistoryViewHolder.class,
                categories
        ) {
            protected void populateViewHolder(HistoryViewHolder viewHolder, Category model, int position) {
                viewHolder.txtMenuName.setText(model.getName());
                Picasso.with(HistoryList.this).load(model.getImage())
                        .into(viewHolder.imageView);

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //send Category Id and start new activity
                        Intent StatisticList = new Intent(HistoryList.this,StatisticList.class);
                        StatisticList.putExtra("CategoryId",adapter.getRef(position).getKey());
                        startActivity(StatisticList);
                    }
                });
            }
        };
        adapter.notifyDataSetChanged();
        recycler_historyList.setAdapter(adapter);
    }
}