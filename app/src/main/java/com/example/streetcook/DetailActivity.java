package com.example.streetcook;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private String foodName;
    private String allData;
    private String videoUrl;
    private String videoId;
    private String videoThumbnailUrl;

    private ImageView imageView;
    private ImageView videoImageView;
    private TextView nameTextView;
    private TextView categoryTextView;
    private TextView areaTextView;
    private TextView tagTextView;
    private TextView instructionsTextView;
    private RecyclerView ingredientsRecyclerView;
    private IngredientsAdapter ingredientsAdapter;
    String Category ;
    String Area ;
    String Tag ;
    String tmp ;
    String Instructions ;
    List<String> Ingredients ;
    String Picture ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = findViewById(R.id.Picture);
        videoImageView = findViewById(R.id.video);
        nameTextView = findViewById(R.id.nameFood);
        categoryTextView = findViewById(R.id.category);
        areaTextView = findViewById(R.id.area);
        tagTextView = findViewById(R.id.tag);
        instructionsTextView = findViewById(R.id.instruction);
        ingredientsRecyclerView = findViewById(R.id.Ingredients);
        FloatingActionButton favButton = findViewById(R.id.favButton);

        //New in homepage





        // Prepare the data for the RecyclerView



        ImageView backButton = findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBackToCategory = new Intent(DetailActivity.this, HomepageActivity.class);
                startActivity(goBackToCategory);
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            foodName = extras.getString("foodName");
            nameTextView.setText(foodName);
            new GetDataTask().execute();
        }


        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FavoriteItemData favoriteItem = new FavoriteItemData(foodName, Tag, Picture);
                UserDataManager userDataManager = new UserDataManager(DetailActivity.this);
                userDataManager.saveFavoriteItemData(favoriteItem);

                // ตัวอย่างเท่านั้น คุณอาจต้องแก้ไขต่อเพื่อปรับให้เหมาะสมกับโครงสร้างของแอปพลิเคชันของคุณ
                // ตัวอย่าง: เปลี่ยน Activity หรือทำการอัพเดตรายการที่แสดงรายการโปรด หรืออื่นๆ

                Toast.makeText(DetailActivity.this, "Added to favorites", Toast.LENGTH_SHORT).show();

                UserDataManager u = new UserDataManager(DetailActivity.this);
                u.printFavoriteList();

            }
        });



    }

    private class GetDataTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            return FoodData.getAllData(foodName);
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                allData = result;

                 Category = FoodData.getValueOf(allData, "strCategory");
                 Area = FoodData.getValueOf(allData, "strArea");
                 Tag = FoodData.getValueOf(allData, "strTags");
                 tmp = FoodData.getValueOf(allData, "strInstructions");
                 Instructions = FoodData.formatTextForTextView(tmp);
                 Ingredients = FoodData.extractIngredients(allData);
                 Picture = FoodData.getValueOf(allData, "strMealThumb");
                 videoUrl = FoodData.getVideoUrl(allData);
                 videoId = FoodData.extractVideoId(videoUrl);
                 videoThumbnailUrl = "https://img.youtube.com/vi/" + videoId + "/0.jpg";
                Picasso.get().load(videoThumbnailUrl).into(videoImageView);
                videoImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + videoId));
                        intent.putExtra("VIDEO_ID", videoId);
                        startActivity(intent);
                    }
                });

                Glide.with(DetailActivity.this).load(Picture).into(imageView);
                categoryTextView.setText("Category: " + Category);
                areaTextView.setText("Area: " + Area);
                tagTextView.setText("Tag: " + Tag);
                instructionsTextView.setText(Instructions);

                ingredientsAdapter = new IngredientsAdapter(Ingredients);
                ingredientsRecyclerView.setAdapter(ingredientsAdapter);
                ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(DetailActivity.this));
            }
        }
    }
}
