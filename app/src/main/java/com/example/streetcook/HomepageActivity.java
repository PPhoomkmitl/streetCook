package com.example.streetcook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class HomepageActivity extends AppCompatActivity {

    protected List<HomeReccomItem> dataListHome;
    protected List<HomeReccomItem> favListHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        CardView fav1 = findViewById(R.id.Fav1);
        CardView fav2 = findViewById(R.id.Fav2);
        CardView fav3 = findViewById(R.id.Fav3);

        TextView noFav = findViewById(R.id.noFavText);

        TextView textName = findViewById(R.id.textName);
        UserDataManager userDataManager = new UserDataManager(HomepageActivity.this);
        String name = userDataManager.getName();
        String username = userDataManager.getUsername();

        ImageView profile = findViewById(R.id.homepic);

        String imageUrl = userDataManager.getImage();

        favListHome = new ArrayList<>();

        if (userDataManager.getFavoriteList().size() == 0) {
            fav1.setVisibility(View.INVISIBLE);
            fav2.setVisibility(View.INVISIBLE);
            fav3.setVisibility(View.INVISIBLE);

        }
        else if (userDataManager.getFavoriteList().size() == 1) {
            fav2.setVisibility(View.INVISIBLE);
            fav3.setVisibility(View.INVISIBLE);
            noFav.setVisibility(View.INVISIBLE);
            for (int i = 0; i < 1; i++) {
                String FoodName = userDataManager.getFavoriteList().get(i).getTitle();

                try {
                    GetDataTask getDataTask = new GetDataTask(FoodName); // Instantiate GetDataTask
                    String allData = getDataTask.execute().get(); // Wait for task execution and get the result
                    if (allData != null) {

                        String Picture = FoodData.getValueOf(allData, "strMealThumb");
                        HomeReccomItem randomItem = new HomeReccomItem(FoodName,Picture);
                        favListHome.add(randomItem);

                    } else {
                        // Handle the case where allData is null
                    }
                } catch(Exception e){
                    e.printStackTrace();
                }
            }

            ImageView imageFav_1 = findViewById(R.id.imageFavor_1);
            TextView textFav_1 = findViewById(R.id.textFavor_1);
            // Load the image using Glide

            Glide.with(HomepageActivity.this)
                    .load(favListHome.get(0).getHomeReccomImageResource())
                    .into(imageFav_1);
            textFav_1.setText(favListHome.get(0).getHomeReccomTitle());
            imageFav_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goReccom = new Intent(HomepageActivity.this, DetailActivity.class);
                    goReccom.putExtra("foodName", favListHome.get(0).getHomeReccomTitle());
                    startActivity(goReccom);
                }
            });
        }

        else if (userDataManager.getFavoriteList().size() == 2) {
            fav3.setVisibility(View.INVISIBLE);
            noFav.setVisibility(View.INVISIBLE);
            for (int i = 0; i < 2; i++) {
                String FoodName = userDataManager.getFavoriteList().get(i).getTitle();

                try {
                    GetDataTask getDataTask = new GetDataTask(FoodName); // Instantiate GetDataTask
                    String allData = getDataTask.execute().get(); // Wait for task execution and get the result
                    if (allData != null) {

                        String Picture = FoodData.getValueOf(allData, "strMealThumb");
                        HomeReccomItem randomItem = new HomeReccomItem(FoodName,Picture);
                        favListHome.add(randomItem);

                    } else {
                        // Handle the case where allData is null
                    }
                } catch(Exception e){
                    e.printStackTrace();
                }
            }

            ImageView imageFav_1 = findViewById(R.id.imageFavor_1);
            TextView textFav_1 = findViewById(R.id.textFavor_1);
            ImageView imageFav_2 = findViewById(R.id.imageFavor_2);
            TextView textFav_2 = findViewById(R.id.textFavor_2);
            // Load the image using Glide

            Glide.with(HomepageActivity.this)
                    .load(favListHome.get(0).getHomeReccomImageResource())
                    .into(imageFav_1);
            textFav_1.setText(favListHome.get(0).getHomeReccomTitle());
            imageFav_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goReccom = new Intent(HomepageActivity.this, DetailActivity.class);
                    goReccom.putExtra("foodName", favListHome.get(0).getHomeReccomTitle());
                    startActivity(goReccom);
                }
            });

            Glide.with(HomepageActivity.this)
                    .load(favListHome.get(1).getHomeReccomImageResource())
                    .into(imageFav_2);
            textFav_2.setText(favListHome.get(1).getHomeReccomTitle());
            imageFav_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goReccom = new Intent(HomepageActivity.this, DetailActivity.class);
                    goReccom.putExtra("foodName", favListHome.get(1).getHomeReccomTitle());
                    startActivity(goReccom);
                }
            });

        }
        else if (userDataManager.getFavoriteList().size() >= 3) {
            noFav.setVisibility(View.INVISIBLE);
            for (int i = 0; i < 3; i++) {
                String FoodName = userDataManager.getFavoriteList().get(i).getTitle();

                try {
                    GetDataTask getDataTask = new GetDataTask(FoodName); // Instantiate GetDataTask
                    String allData = getDataTask.execute().get(); // Wait for task execution and get the result
                    if (allData != null) {

                        String Picture = FoodData.getValueOf(allData, "strMealThumb");
                        HomeReccomItem randomItem = new HomeReccomItem(FoodName,Picture);
                        favListHome.add(randomItem);

                    } else {
                        // Handle the case where allData is null
                    }
                } catch(Exception e){
                    e.printStackTrace();
                }
            }

            ImageView imageFav_1 = findViewById(R.id.imageFavor_1);
            TextView textFav_1 = findViewById(R.id.textFavor_1);
            ImageView imageFav_2 = findViewById(R.id.imageFavor_2);
            TextView textFav_2 = findViewById(R.id.textFavor_2);
            ImageView imageFav_3 = findViewById(R.id.imageFavor_3);
            TextView textFav_3 = findViewById(R.id.textFavor_3);
            // Load the image using Glide

            Glide.with(HomepageActivity.this)
                    .load(favListHome.get(0).getHomeReccomImageResource())
                    .into(imageFav_1);
            textFav_1.setText(favListHome.get(0).getHomeReccomTitle());
            imageFav_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goReccom = new Intent(HomepageActivity.this, DetailActivity.class);
                    goReccom.putExtra("foodName", favListHome.get(0).getHomeReccomTitle());
                    startActivity(goReccom);
                }
            });

            Glide.with(HomepageActivity.this)
                    .load(favListHome.get(1).getHomeReccomImageResource())
                    .into(imageFav_2);
            textFav_2.setText(favListHome.get(1).getHomeReccomTitle());
            imageFav_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goReccom = new Intent(HomepageActivity.this, DetailActivity.class);
                    goReccom.putExtra("foodName", favListHome.get(1).getHomeReccomTitle());
                    startActivity(goReccom);
                }
            });

            Glide.with(HomepageActivity.this)
                    .load(favListHome.get(2).getHomeReccomImageResource())
                    .into(imageFav_3);
            textFav_3.setText(favListHome.get(2).getHomeReccomTitle());
            imageFav_3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goReccom = new Intent(HomepageActivity.this, DetailActivity.class);
                    goReccom.putExtra("foodName", favListHome.get(2).getHomeReccomTitle());
                    startActivity(goReccom);
                }
            });

        }


        if (imageUrl != "") {
            // Load the image using Glide
            Glide.with(this)
                    .load(imageUrl)
                    .into(profile);
        }

        if (name != "") {
            textName.setText(name);
        }
        else if (name == "") {
            textName.setText("@User888");
        }


        ImageView beefCat = findViewById(R.id.circle1);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_Home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.bottom_Home) {
                return true;
            } else if (item.getItemId() == R.id.bottom_Search) {
                startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                return true;
            } else if (item.getItemId() == R.id.bottom_Favorite) {
                startActivity(new Intent(getApplicationContext(), FavoriteActivity.class));
                return true;
            } else if (item.getItemId() == R.id.bottom_Setting) {
                startActivity(new Intent(getApplicationContext(), SettingActivity.class));
                return true;
            }
            return false;
        });

        beefCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goCategory = new Intent(HomepageActivity.this, CategoryActivity.class);
                goCategory.putExtra("Type", 1);
                startActivity(goCategory);
            }
        });

        ImageView seaCat = findViewById(R.id.circle2);
        seaCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goCategory = new Intent(HomepageActivity.this, CategoryActivity.class);
                goCategory.putExtra("Type", 2);
                startActivity(goCategory);
            }
        });

        ImageView chickenCat = findViewById(R.id.circle3);
        chickenCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goCategory = new Intent(HomepageActivity.this, CategoryActivity.class);
                goCategory.putExtra("Type", 3);
                startActivity(goCategory);
            }
        });

        ImageView vegetarianCat = findViewById(R.id.circle4);
        vegetarianCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goCategory = new Intent(HomepageActivity.this, CategoryActivity.class);
                goCategory.putExtra("Type", 4);
                startActivity(goCategory);
            }
        });

        ImageView dessertCat = findViewById(R.id.circle5);
        dessertCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goCategory = new Intent(HomepageActivity.this, CategoryActivity.class);
                goCategory.putExtra("Type", 5);
                startActivity(goCategory);
            }
        });

        ImageView pastaCat = findViewById(R.id.circle6);
        pastaCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goCategory = new Intent(HomepageActivity.this, CategoryActivity.class);
                goCategory.putExtra("Type", 6);
                startActivity(goCategory);
            }
        });

        //Search Button
        TextView search = findViewById(R.id.Search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSearch = new Intent(HomepageActivity.this, SearchActivity.class);
                startActivity(goToSearch);
            }
        });

        // See all recommend
        TextView seeAllRecommend = findViewById(R.id.textSeeAll1);

        seeAllRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToRecommend = new Intent(HomepageActivity.this, RecomendActivity.class);
                startActivity(goToRecommend);
            }
        });

        // See all favorite
        TextView seeAllFavorite = findViewById(R.id.textSeeAll2);

        seeAllFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToFavorite = new Intent(HomepageActivity.this, FavoriteActivity.class);
                startActivity(goToFavorite);
            }
        });

        //Recoommend page
        dataListHome = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Random rand = new Random();
            FoodData.initializeFoodItems();
            List<String> allFoodList = FoodData.allFoodList;
            Log.d("Test", "Size" + allFoodList.size());
            int randomIndex = rand.nextInt(allFoodList.size());
            String FoodName = allFoodList.get(randomIndex);

            try {
                GetDataTask getDataTask = new GetDataTask(FoodName); // Instantiate GetDataTask
                String allData = getDataTask.execute().get(); // Wait for task execution and get the result
                if (allData != null) {

                    String Picture = FoodData.getValueOf(allData, "strMealThumb");
                    HomeReccomItem randomItem = new HomeReccomItem(FoodName,Picture);
                    dataListHome.add(randomItem);

                } else {
                    // Handle the case where allData is null
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        }

        ImageView imageRecom_1 = findViewById(R.id.imageRecom_1);
        TextView textRecom_1 = findViewById(R.id.textRecom_1);
        // Load the image using Glide

        Glide.with(HomepageActivity.this)
                .load(dataListHome.get(0).getHomeReccomImageResource())
                .into(imageRecom_1);
        textRecom_1.setText(dataListHome.get(0).getHomeReccomTitle());
        imageRecom_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goReccom = new Intent(HomepageActivity.this, DetailActivity.class);
                goReccom.putExtra("foodName", dataListHome.get(0).getHomeReccomTitle());
                startActivity(goReccom);
            }
        });


        ImageView imageRecom_2 = findViewById(R.id.imageRecom_2);
        TextView textRecom_2 = findViewById(R.id.textRecom_2);
        // Load the image using Glide
        Glide.with(HomepageActivity.this)
                .load(dataListHome.get(1).getHomeReccomImageResource())
                .into(imageRecom_2);
        textRecom_2.setText(this.dataListHome.get(1).getHomeReccomTitle());
        imageRecom_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goReccom = new Intent(HomepageActivity.this, DetailActivity.class);
                goReccom.putExtra("foodName", dataListHome.get(1).getHomeReccomTitle());
                startActivity(goReccom);
            }
        });


        ImageView imageRecom_3 = findViewById(R.id.imageRecom_3);
        TextView textRecom_3 = findViewById(R.id.textRecom_3);
        // Load the image using Glide
        Glide.with(HomepageActivity.this)
                .load(dataListHome.get(2).getHomeReccomImageResource())
                .into(imageRecom_3);
        textRecom_3.setText(this.dataListHome.get(2).getHomeReccomTitle());
        imageRecom_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goReccom = new Intent(HomepageActivity.this, DetailActivity.class);
                goReccom.putExtra("foodName", dataListHome.get(2).getHomeReccomTitle());
                startActivity(goReccom);
            }
        });

    }


    private static class GetDataTask extends AsyncTask<Void, Void, String> {
        private String foodName;

        public GetDataTask(String foodName) {
            this.foodName = foodName;
        }

        @Override
        protected String doInBackground(Void... params) {
            return FoodData.getAllData(foodName);
        }
    }
}