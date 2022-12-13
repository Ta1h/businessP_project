package com.example.business_p;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button signup,login, catt, newsFeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signup = findViewById(R.id.signup);
        login = findViewById(R.id.login);
        catt = findViewById(R.id.catt);
        newsFeed = findViewById(R.id.newsFeed);

        signup.setOnClickListener(view -> {
            Intent intent= new Intent(MainActivity.this,signup.class);
            startActivity(intent);
            finish();
        });

        login.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,login.class);
            startActivity(intent);
            finish();
        });

        catt.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,category.class);
            startActivity(intent);
            finish();
        });

        newsFeed.setOnClickListener(view -> {
            Intent intent = new Intent(this, Newsfeed.class);
            startActivity(intent);
            finish();
        });



    }
}