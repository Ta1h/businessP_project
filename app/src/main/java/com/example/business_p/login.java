package com.example.business_p;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private String email, password; //storing values from edittext
    private final String URL = "http://10.0.2.2/login&register/login.php";

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
            Log.d(TAG, "inside of string request login");
            // string request is used for retrieving the response body of the URL(as a string)
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d(TAG, "inside of string response login");
                    if (response.equals("success")) {
                        Log.d(TAG, "response success login");
                        Intent intent = new Intent(login.this, Newsfeed.class);
                        startActivity(intent);
                        finish();
                    } else if (response.equals("failure")) {
                        Toast.makeText(login.this, "Invalid Login", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d(TAG, "does not respond");
                    }
                }
            }, new Response.ErrorListener() { // set a proper error listener
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(login.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }){ // set a hashmap for putting values
                @NonNull
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("user_email", email);
                    data.put("user_password", password);
                    return data;
                }
            };
            // set to enqueue the given request for dispatch
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);

        } else if (email.equals("") && password.equals("")){
            Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Fields can not be empty", Toast.LENGTH_SHORT).show();
        }
    }
}