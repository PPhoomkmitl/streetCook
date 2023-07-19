package com.example.streetcook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText userEditText = findViewById(R.id.UserEditText);
        EditText passwordEditText = findViewById(R.id.PasswordEditText);
        EditText confirmPasswordEditText = findViewById(R.id.ConfirmPasswordEditText);
        TextView Signin = findViewById(R.id.SigninText);
//        ImageView backIcon = findViewById(R.id.backButton);
        Button continueButton = findViewById(R.id.ContinueButton);



        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goSignIn = new Intent(SignupActivity.this, SigninActivity.class);
                startActivity(goSignIn);
            }
        });

//        backIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent backSignup = new Intent(SignupActivity.this, RecomendActivity.class);
//                startActivity(backSignup);
//            }
//        });

        passwordEditText.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
        confirmPasswordEditText.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

                if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 8) {
                    Toast.makeText(SignupActivity.this, "Password must be at least 8 characters long", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(SignupActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }  else {

                    Toast.makeText(SignupActivity.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                    Intent goSignIn1 = new Intent(SignupActivity.this, SigninActivity.class);
                    UserDataManager userDataManager = new UserDataManager(SignupActivity.this);
                    userDataManager.clearUserData();
                    userDataManager.saveUserDataWithOutImage(username, password);
                    startActivity(goSignIn1);

                }
            }
        });

    }
}