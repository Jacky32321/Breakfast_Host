package com.example.test01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.test01.Model.Comment;

import java.util.List;

public class NewFoodActivity extends AppCompatActivity {

    private EditText mName;
    private EditText mDescription;
    private Button mAdd_btn;
    private Button mBack_btn;
    private RatingBar mRatingbar;
    private float score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_food);
        mName = (EditText) findViewById(R.id.Name_editTxt);
        mDescription = (EditText) findViewById(R.id.Description_editTxt);

        mAdd_btn = (Button) findViewById(R.id.add_btn);
        mBack_btn = (Button) findViewById(R.id.back_btn);

        mRatingbar = (RatingBar) findViewById(R.id.Score_ratingBar);

        mAdd_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Comment food = new Comment();
                score = mRatingbar.getRating();
                food.setCustomer_Name(mName.getText().toString());
                food.setDescription(mDescription.getText().toString());
                food.setScore("" + (int)score);
                new FirebaseDatabaseHelper().addComment(food, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Comment> foods, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {
                        Toast.makeText(NewFoodActivity.this, "The Comment record has been inserted successfully", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
                mBack_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish(); return;
                    }
                });
            }
        });
    }
}
