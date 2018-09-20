package com.saiyandapalli.mdbsocials;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class NewSocialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_social);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            //Part 3: Here we will get the image and the code from the switch and create a new
            // message while also storing the image
            //Question 1: add Firebase Storage to your project
            //Question 2: create a DatabaseReference below
            final DatabaseReference ref = FirebaseDatabase.getInstance().getReference();


            //Question 3: generate a key below to use as a unique identifier for the message, and
            // for the image filename
            final String key = ref.child("socials").push().getKey();
            StorageReference storageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://fir-demo-df3ae.appspot.com");
            StorageReference picref = storageRef.child(key + ".png");


            //Question 4: create a StorageReference below (hint: the url you need can be found in
            // your console at firebase.google.com
            picref.putFile(data.getData()).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(NewSocialActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    int interest =;
                    String firebaseImageUrl=;
                    String email=;
                    String name =;
                    String description =;
                    Social social = new Social(interest, firebaseImageUrl, email, name, description);
                    ref.child("socials").child(key).setValue(social);
                    startActivity(new Intent(NewSocialActivity.this, ListActivity.class));
                }
            });

            //Question 5: add a png file to the storage using the key as the filename. If it fails,
            // write a toast. If it works, add the message. Get the value of the switch using this line:
            // (((Switch) findViewById(R.id.switch1)).isChecked() ? "1" : "0")
            // Then go back to the ListActivity

            //Last part in ListAdapter
        }


    }
}