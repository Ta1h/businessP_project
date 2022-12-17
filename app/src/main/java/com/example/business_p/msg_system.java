package com.example.business_p;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class msg_system extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_system);
    }

    public void backMsg(View view) {
        startActivity(new Intent(this, Newsfeed.class));
    }

    public void message_pop(View view) {
        startActivity(new Intent(this, chat.class));
    }

}