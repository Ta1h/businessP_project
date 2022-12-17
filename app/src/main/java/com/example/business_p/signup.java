package com.example.business_p;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class signup extends AppCompatActivity {

    private EditText etUsername, etEmail, etPassword, etConfirmPassword;
    private final String URL = "http://10.0.2.2/business_p/register.php";
    private String username, email, password, confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        Button btnSignup = findViewById(R.id.btnSignup);
        username = email = password = confirmPassword = "";

        btnSignup.setOnClickListener(View -> {
            username = etUsername.getText().toString().trim();
            email = etEmail.getText().toString().trim();
            password = etPassword.getText().toString().trim();
            confirmPassword = etConfirmPassword.getText().toString().trim();

            if(!password.equals(confirmPassword)){
                Toast.makeText(this, "Password Mismatch", Toast.LENGTH_SHORT).show();
            }else if(!username.equals("") && !email.equals("") && !password.equals("")){
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, response -> {
                    Log.d("","on response sign up");
                    if (response.equals("success")) {
                        Log.d("","response is success");
                        Toast.makeText(signup.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(signup.this, login.class));
                        finish();
                    } else if (response.equals("failure")) {
                        Toast.makeText(signup.this, "Invalid login email/password", Toast.LENGTH_SHORT).show();
                    }
                }, error -> Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show()){
                    @NonNull
                    @Override
                    protected Map<String, String> getParams() {
                        Log.d("","on hashmap");
                        Map<String, String> data = new HashMap<>();
                        data.put("user_username", username);
                        data.put("user_email", email);
                        data.put("user_password", password);
                        return data;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
            }

        });
    }
}