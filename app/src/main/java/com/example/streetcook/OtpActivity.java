package com.example.streetcook;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

public class OtpActivity extends AppCompatActivity {
    private EditText OTP1, OTP2, OTP3, OTP4, OTP5, OTP6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        OTP1 = findViewById(R.id.OTP1);
        OTP2 = findViewById(R.id.OTP2);
        OTP3 = findViewById(R.id.OTP3);
        OTP4 = findViewById(R.id.OTP4);
        OTP5 = findViewById(R.id.OTP5);
        OTP6 = findViewById(R.id.OTP6);

        setupOTPInputs();

        //ContinueButton
        Button continueButton = findViewById(R.id.ContinueButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otpGT1= OTP1.getText().toString();
                String otpGT2= OTP2.getText().toString();
                String otpGT3= OTP3.getText().toString();
                String otpGT4= OTP4.getText().toString();
                String otpGT5= OTP5.getText().toString();
                String otpGT6= OTP6.getText().toString();

                String otpCT1 = "1";
                String otpCT2 = "2";
                String otpCT3 = "3";
                String otpCT4 = "4";
                String otpCT5 = "5";
                String otpCT6 = "6";


                if (otpGT1.isEmpty() || otpGT2.isEmpty() || otpGT3.isEmpty() || otpGT4.isEmpty() || otpGT5.isEmpty() || otpGT6.isEmpty()) {
                    Toast.makeText(OtpActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
                else if (otpCT1.equals(otpGT1) && otpCT2.equals(otpGT2) && otpCT3.equals(otpGT3) && otpCT4.equals(otpGT4) && otpCT5.equals(otpGT5) && otpCT6.equals(otpGT6)){
                    Intent goResetPassword = new Intent(OtpActivity.this, ResetPasswordActivity.class);
                    startActivity(goResetPassword);
                    //Message box
                    Toast.makeText(OtpActivity.this, "correct", Toast.LENGTH_SHORT).show();
                }else {
                    //Message box
                    Toast.makeText(OtpActivity.this, "invalid OTP Please try again", Toast.LENGTH_SHORT).show();
                }

            }
        });

        ImageView backIcon = findViewById(R.id.backButton);
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backSignup = new Intent(OtpActivity.this, ForgotPasswordActivity.class);
                startActivity(backSignup);
            }
        });
    }

    private void setupOTPInputs(){
        OTP1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    OTP2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        OTP2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    OTP3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        OTP3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    OTP4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        OTP4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    OTP5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        OTP5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    OTP6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
