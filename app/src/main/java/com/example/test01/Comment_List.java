package com.example.test01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.test01.Model.Comment;
import com.example.test01.Model.RecyclerView_Config;

import java.util.List;

public class Comment_List extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_foods);
        new FirebaseDatabaseHelper().readMenu(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Comment> foods, List<String> keys) {
                new RecyclerView_Config().setConfig(mRecyclerView, Comment_List.this, foods, keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.foodlist_activity_menu, menu);
        //return super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.new_comment:
                startActivity(new Intent(new Intent(this, NewFoodActivity.class)));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
