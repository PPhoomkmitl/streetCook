package com.example.streetcook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ResetPasswordActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);

        EditText enterPassword = findViewById(R.id.EnterNewPassword);
        EditText enterComfirmPassword = findViewById(R.id.EnterConfirmNewPassword);
        //ContinueButton
        Button continueButton = findViewById(R.id.ContinueButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enterPasswordS = enterPassword.getText().toString();
                String enterConfirmPasswordS = enterComfirmPassword.getText().toString();
                if (enterPasswordS.isEmpty() || enterConfirmPasswordS.isEmpty()) {
                    Toast.makeText(ResetPasswordActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else if (enterPasswordS.length() < 8) {
                    Toast.makeText(ResetPasswordActivity.this, "Password must be at least 8 characters long", Toast.LENGTH_SHORT).show();
                } else if (!enterPasswordS.equals(enterConfirmPasswordS)) {
                    Toast.makeText(ResetPasswordActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }  else {
                    Intent backToProgram = new Intent(ResetPasswordActivity.this, HomepageActivity.class);
                    UserDataManager userDataManager = new UserDataManager(ResetPasswordActivity.this);
                    userDataManager.savePasswordOnly(enterPasswordS);
                    startActivity(backToProgram);
                    //Message box
                    Toast.makeText(ResetPasswordActivity.this, "Password Has Been Changed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ImageView backIcon = findViewById(R.id.backButton);
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backSignup = new Intent(ResetPasswordActivity.this, OtpActivity.class);
                startActivity(backSignup);
            }
        });


    }
}
