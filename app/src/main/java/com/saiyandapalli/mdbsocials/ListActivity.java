package com.saiyandapalli.mdbsocials;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.saiyandapalli.mdbsocials.MainActivity.mAuth;

public class ListActivity extends AppCompatActivity {
    final ArrayList<Social> socials = new ArrayList<>();
    final ListAdapter adapter = new ListAdapter(this, socials);
    public ArrayList<String> socialsImInterestedIn = new ArrayList<>();
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("/socials");
    DatabaseReference myUserRef = FirebaseDatabase.getInstance().getReference("/users").child(mAuth.getCurrentUser().getUid()).child("interested");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        RecyclerView recyclerAdapter = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerAdapter.setLayoutManager(new LinearLayoutManager(this));

        recyclerAdapter.setAdapter(adapter);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("yeetus", "in value event listener");
                socials.clear();
                for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                    socials.add(dataSnapshot2.getValue(Social.class));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("oh no it didnt work", "Failed to read value.", error.toException());
            }
        });
        myUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                socialsImInterestedIn.clear();
                for (DataSnapshot socialid : dataSnapshot.getChildren()) {
                    socialsImInterestedIn.add(socialid.getValue(String.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewSocialActivity.class);
                startActivity(intent);
            }
        });


    }
}
