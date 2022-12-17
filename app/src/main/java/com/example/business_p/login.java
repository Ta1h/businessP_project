package com.example.business_p;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
    
public class login extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private String email, password;
    private final String URL = "http://10.0.2.2/business_p/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = password = "";
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
    }

    public void btnLogin(View view) {
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        if(!email.equals("") && !password.equals("")){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, response -> {
                if (response.equals("success")) {
                    startActivity(new Intent(login.this, Newsfeed.class));
                    finish();
                } else if (response.equals("Failure")) {
                    Toast.makeText(login.this, "Invalid login email/password", Toast.LENGTH_SHORT).show();
                }
            }, error -> Toast.makeText(login.this, error.toString().trim(), Toast.LENGTH_SHORT).show()){
                @NonNull
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> data = new HashMap<>();
                    data.put("user_email", email);
                    data.put("user_password", password);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }else{
            Toast.makeText(login.this, "Field can not be empty", Toast.LENGTH_SHORT).show();
        }
    }
}