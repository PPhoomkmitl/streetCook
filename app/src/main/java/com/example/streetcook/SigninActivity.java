package com.example.streetcook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class SigninActivity extends AppCompatActivity {

    private HashMap<String, String> accountMap = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        UserDataManager userDataManager = new UserDataManager(SigninActivity.this);

        ImageView backIcon = findViewById(R.id.backButton);
        EditText userEditText = findViewById(R.id.UserEditText);
        EditText passwordEditText = findViewById(R.id.PasswordEditText);
        Button loginButton = findViewById(R.id.LoginButton);
        CheckBox stayCheckBox = findViewById(R.id.CheckBox);

        stayCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean status = stayCheckBox.isChecked();
                userDataManager.saveStayStatus(status);
            }
        });

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backSignup = new Intent(SigninActivity.this, SplashActivity.class);
                startActivity(backSignup);
            }
        });

        passwordEditText.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);

        String username = userDataManager.getUsername();
        String password = userDataManager.getPassword();
        Log.d("Test", username);
        Log.d("Test", password);

        accountMap.put(username, password);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SigninActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (accountMap.containsKey(username)) {
                        String storedPassword = accountMap.get(username);
                        if (password.equals(storedPassword)) {
                            Toast.makeText(SigninActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                            Intent goHomePage = new Intent(SigninActivity.this, HomepageActivity.class); // Test
                            goHomePage.putExtra("usernameKey", username);
                            goHomePage.putExtra("accountMap", accountMap);
                            startActivity(goHomePage);

                        } else {
                            Toast.makeText(SigninActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SigninActivity.this, "Username not found", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        TextView forgotPassword = findViewById(R.id.forgotpassText);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goSignIn = new Intent(SigninActivity.this, ForgotPasswordActivity.class);
                startActivity(goSignIn);
            }
        });
    }
}