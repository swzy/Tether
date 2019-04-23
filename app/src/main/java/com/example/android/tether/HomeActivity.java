package com.example.android.tether;

import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {
    private Button storeButton;
    private Button retrieveButton;
    // Firebase instance variables
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseRef;

    private Toast toast;

    //Simple landing page. Can set up a splash screen or auth in the future.
    //Retrieve button checks Firebase container for deviceId and retrieves location.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseRef = mDatabase.getReference();

        toast = new Toast(HomeActivity.this);
        toast.makeText(this, "", Toast.LENGTH_LONG);

        storeButton = findViewById(R.id.enterButton);
        storeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toast.makeText(HomeActivity.this, "Let's store your car!", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(HomeActivity.this, StoreActivity.class);
                    startActivity(i);
                }
        });

        String id = getDeviceID();
        retrieveButton = findViewById(R.id.retrieveButton);
        retrieveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Checks Firebase container for device id
                mDatabaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child("users").hasChild(id)) {
                            toast.makeText(HomeActivity.this, "Retrieving car location from database!", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(HomeActivity.this, RetrieveActivity.class);
                            startActivity(i);
                        } else {
                            toast.makeText(HomeActivity.this, "Could not find location instance! Store it!", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });

    }

    private String getDeviceID() {
        return Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
    }
}
