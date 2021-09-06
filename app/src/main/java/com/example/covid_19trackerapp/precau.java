package com.example.covid_19trackerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class precau extends AppCompatActivity {
    ImageButton backButton;
    CardView cardphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_precau);


        cardphone = findViewById(R.id.cardphone);
        backButton = findViewById(R.id.back_btn1);

        backButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), home.class);
                startActivity(intent);
                setContentView(R.layout.activity_home);
            }
        });

        cardphone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:(+91)1123978046"));
                startActivity(intent);
            }
        });

    }
}