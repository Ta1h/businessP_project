package com.example.business_p;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    private EditText etEmail, etPassword;
    db_helper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        DB = new db_helper(this);
    }

    public void btnLogin(View view) {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if( TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(this, "All fields required", Toast.LENGTH_SHORT).show();
        }else{
            boolean checkEmailPassword = DB.checkEmailPassword(email, password);
            if(checkEmailPassword){
                Toast.makeText(this,"Login Successful",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, Newsfeed.class);
                startActivity(intent);
            }else {
                Toast.makeText(this,"Login Failed",Toast.LENGTH_SHORT).show();
            }
        }



    }
}