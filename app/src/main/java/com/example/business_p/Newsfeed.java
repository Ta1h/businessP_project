package com.example.business_p;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Newsfeed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfeed);
    }

    public void menu(View view) {
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
        finish();
    }
}