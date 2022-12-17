package com.example.business_p;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity {

    private EditText etUsername, etEmail, etPassword, etConfirmPassword;
    db_helper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        DB = new db_helper(this);
    }

    public void btnSignup(View view) {
        String username = etUsername.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();

        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)){
            Toast.makeText(this, "All fields required", Toast.LENGTH_SHORT).show();
        }else {
            if(password.equals(confirmPassword)){
                boolean checkEmail = DB.checkEmail(email);
                if(!checkEmail){
                    boolean insert = DB.insertData(username, email, password);
                    if (insert){
                        Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(signup.this, login.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this,"Email Already Exists", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this,"Password mismatch", Toast.LENGTH_SHORT).show();
            }

        }

    }
}