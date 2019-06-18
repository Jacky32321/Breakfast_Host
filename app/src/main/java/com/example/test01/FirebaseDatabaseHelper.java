package com.example.test01;

import android.support.annotation.NonNull;

import com.example.test01.Model.Comment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceFood;
    private List<Comment> foods = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<Comment> foods, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }
    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceFood = mDatabase.getReference("Comment");
    }

    public void readMenu(final DataStatus dataStatus){
        mReferenceFood.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                foods.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Comment food = keyNode.getValue(Comment.class);
                    foods.add(food);
                }
                dataStatus.DataIsLoaded(foods, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addComment(Comment comment, final DataStatus dataStatus){
        String key = mReferenceFood.push().getKey();
        mReferenceFood.child(key).setValue(comment).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsInserted();
            }
        });
    }
}
