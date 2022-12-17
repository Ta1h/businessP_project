package com.example.business_p;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    public void home(View view) {
        startActivity(new Intent(this, Newsfeed.class));
    }

    public void back(View view) {
        startActivity(new Intent(this, Newsfeed.class));
    }

    public void logout(View view) {
        startActivity(new Intent(this, login.class));
    }

    public void login(View view) {
        startActivity(new Intent(this, login.class));
    }
}