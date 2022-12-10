package com.example.business_p;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class signup extends AppCompatActivity {

    private EditText etUsername, etEmail, etPassword, etConfirmPassword;
    private final String URL = "http://10.0.2.2/login&register/register.php";
    private String name, email, password, confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        name = email = password = confirmPassword = "";
    }

    public void btnSignup(View view) {
        name = etUsername.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        confirmPassword = etConfirmPassword.getText().toString().trim();

        if(!password.equals(confirmPassword)){
            Toast.makeText(this, "Password Mismatch", Toast.LENGTH_SHORT).show();
        }else if(!name.equals("") && !email.equals("") && !password.equals("")){
            Log.d(TAG, "inside of string request signup");
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d(TAG, "inside of response signup");
                    if (response.equals("success")) {
                        Log.d(TAG, "response is success signup");
                        Toast.makeText(signup.this, "registered successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(signup.this, login.class);
                        startActivity(intent);
                        finish();
                    } else if (response.equals("failure")) {
                        Log.d(TAG, "response is failure signup");
                        Toast.makeText(signup.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d(TAG, "does not respond");
                    }
                }
            }, new Response.ErrorListener() { // set a proper error listener
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }){ // set a hashmap for putting values
                @NonNull
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("user_username", name);
                    data.put("user_email", email);
                    data.put("user_password", password);
                    return data;
                }
            };
            // set to enqueue the given request for dispatch
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }

    }
}