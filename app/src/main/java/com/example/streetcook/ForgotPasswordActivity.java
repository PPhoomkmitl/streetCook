package com.example.streetcook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ForgotPasswordActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        EditText emailText = findViewById(R.id.EnterYourEmail);
        UserDataManager userDataManager = new UserDataManager(ForgotPasswordActivity.this);
        //ContinueButton
        Button continueButton = findViewById(R.id.ContinueButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailText.getText().toString();
                if (email.isEmpty()) {
                    Toast.makeText(ForgotPasswordActivity.this, "Please fill in fields", Toast.LENGTH_SHORT).show();
                }else if(email.equals(userDataManager.getUsername())) {
                    Intent goResetPassword = new Intent(ForgotPasswordActivity.this, OtpActivity.class);
                    startActivity(goResetPassword);
                    //Message box
                    Toast.makeText(ForgotPasswordActivity.this, "Please check your email", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(ForgotPasswordActivity.this, "This email has never been registered before.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ImageView backIcon = findViewById(R.id.backButton);
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backSignup = new Intent(ForgotPasswordActivity.this, SigninActivity.class);
                startActivity(backSignup);
            }
        });
    }
}
