package com.example.streetcook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        UserDataManager userDataManager = new UserDataManager(SplashActivity.this);

        Button buttonGmail = findViewById(R.id.ButtonGmail);
        TextView signIn = findViewById(R.id.signIn);

        if (userDataManager.getStay() == true) {
            Intent goHompage = new Intent(SplashActivity.this, HomepageActivity.class);
            startActivity(goHompage);
        }

        buttonGmail.setOnClickListener(v -> {
            Intent in = new Intent(SplashActivity.this,SignupActivity.class);
            startActivity(in);
        });

        signIn.setOnClickListener(v -> {
            Intent in = new Intent(SplashActivity.this,SigninActivity.class);
            startActivity(in);
        });
    }
}