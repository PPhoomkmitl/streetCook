package com.example.streetcook;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class SettingActivity extends AppCompatActivity {
    // Account setting
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button emailEditButton;
    private Button passwordEditButton;
    private EditText nameEditText;

    private  ImageView pen;




    private ImageView profileImageView;
    private FloatingActionButton selectImageButton;
    private static final int REQUEST_IMAGE_SELECTION = 1;
    String username;
    String password;
    String name;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        UserDataManager userDataManager = new UserDataManager(SettingActivity.this);
        username = userDataManager.getUsername();
        password = userDataManager.getPassword();
        name = userDataManager.getName();

        //Account setting
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        emailEditButton = findViewById(R.id.editButton1);
        passwordEditButton = findViewById(R.id.editButton2);

        //Profile Image
        profileImageView = findViewById(R.id.profileImageView);
        selectImageButton = findViewById(R.id.selectImageButton);



        //Edit name
        nameEditText = findViewById(R.id.nameEditText);
        pen = findViewById(R.id.pen);

        emailEditText.setText(username);
        nameEditText.setText(name);
        passwordEditText.setText(password);

        //Back
        ImageView backIcon = findViewById(R.id.backButtonSetting);
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backSignup = new Intent(SettingActivity.this, HomepageActivity.class);
                startActivity(backSignup);
            }
        });

        //Sign out
        Button editButtonSignout = findViewById(R.id.editButtonSignout);
        editButtonSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDataManager.cleaStay();
                Intent in = new Intent(SettingActivity.this,SplashActivity.class);
                startActivity(in);
            }
        });

//        nameEditText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String newName = nameEditText.getText().toString();
//                // Save the new name or perform other operations with it
//                Toast.makeText(SettingActivity.this, "Name saved: " + newName, Toast.LENGTH_SHORT).show();
//            }
//        });

        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(SettingActivity.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start(REQUEST_IMAGE_SELECTION);

            }

        });


        pen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameEditText.isEnabled()) {
                    // Save the updated email
                    String updatedName = nameEditText.getText().toString();
                    // Disable editing mode
                    nameEditText.setText(updatedName);
                    nameEditText.setEnabled(false);
                    Toast.makeText(SettingActivity.this, "Name saved: " + updatedName, Toast.LENGTH_SHORT).show();
                    if (!updatedName.isEmpty()){
                        name = updatedName;
                        userDataManager.saveNameOnly(name);
                    }


                } else {
                    // Enable editing mode
                    nameEditText.setEnabled(true);
                    // Clear the text
                    nameEditText.setText("");
                }
            }
        });





        emailEditButton.setOnClickListener(new View.OnClickListener() {
            String emailEditTextS_1;
            @Override
            public void onClick(View v) {
                String emailEditTextS = emailEditButton.getText().toString();
                if (emailEditTextS.isEmpty()) {
                    emailEditText.setText(emailEditTextS_1);
                    emailEditText.setEnabled(false);
                    Toast.makeText(SettingActivity.this, "Please fill in fields", Toast.LENGTH_SHORT).show();
                    emailEditButton.setText("Edit");
                }
                else if (emailEditText.isEnabled()) {
                    // Save the updated email
                    String updatedEmail = emailEditText.getText().toString();
                    // Disable editing mode
                    emailEditText.setEnabled(false);
                    // Update the button text
                    emailEditButton.setText("Edit");
                    // Perform further operations with the updated email if needed
                    password = updatedEmail;
                    userDataManager.saveUserDataWithOutImage(username, password);
                }
                else {
                    // Enable editing mode
                    emailEditText.setEnabled(true);
                    emailEditTextS_1 = emailEditButton.getText().toString();
                    // Clear the text
                    //                    emailEditText.setText("");
                    // Change the button text
                    emailEditButton.setText("Save");
                }
            }
        });


        passwordEditButton.setOnClickListener(new View.OnClickListener() {
            String passwordEditTextS_1;
            @Override
            public void onClick(View v) {
                String passEditTextS = passwordEditText.getText().toString();
                if (passEditTextS.isEmpty()) {
                    passwordEditText.setText(passwordEditTextS_1);
                    passwordEditText.setEnabled(false);
                    Toast.makeText(SettingActivity.this, "Please fill in fields", Toast.LENGTH_SHORT).show();
                    passwordEditButton.setText("Edit");
                }
                else if (passwordEditText.isEnabled()) {
                    // Save the updated email
                    String updatedPassword = passwordEditText.getText().toString();
                    // Disable editing mode
                    passwordEditText.setEnabled(false);
                    // Update the button text
                    passwordEditButton.setText("Edit");
                    // Perform further operations with the updated email if needed
                    password = updatedPassword;
                    userDataManager.saveUserDataWithOutImage(username, password);
                }
                else {
                    // Enable editing mode
                    passwordEditText.setEnabled(true);
                    passwordEditTextS_1 = passwordEditButton.getText().toString();
                    // Clear the text
                    //                    emailEditText.setText("");
                    // Change the button text
                    passwordEditButton.setText("Save");
                }
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_Setting);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.bottom_Home) {
                startActivity(new Intent(getApplicationContext(), HomepageActivity.class));
                return true;
            } else if (item.getItemId() == R.id.bottom_Search) {
                startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                return true;
            } else if (item.getItemId() == R.id.bottom_Favorite) {
                startActivity(new Intent(getApplicationContext(), FavoriteActivity.class));
                return true;
            } else if (item.getItemId() == R.id.bottom_Setting) {
                return true;
            }
            return false;
        });

        if (userDataManager.getImage() != "") {
            String imageUrl = userDataManager.getImage();
            // Load the image using Glide
            Glide.with(this)
                    .load(imageUrl)
                    .into(profileImageView);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_SELECTION && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            profileImageView.setImageURI(selectedImageUri);
            UserDataManager userDataManager = new UserDataManager(SettingActivity.this);
            // Save the selected image using UserDataManager
            userDataManager.saveImageOnly(selectedImageUri.toString());
        }
    }
}