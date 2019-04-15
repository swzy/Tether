package com.example.android.tether;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    private Button storeButton;
    private Button retrieveButton;

    //Simple landing page. Can set up a splash screen or auth in the future.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        storeButton = findViewById(R.id.enterButton);
        storeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(HomeActivity.this, LocationActivity.class);
                    startActivity(i);
                }
        });

        retrieveButton = findViewById(R.id.retrieveButton);
        retrieveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, RetrieveActivity.class);
                startActivity(i);
            }
        });
    }
}
