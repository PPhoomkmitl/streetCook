package com.example.streetcook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity implements CategoryRecyclerViewAdapter.CategoryItemClickListener {

    private RecyclerView recyclerView;
    private CategoryRecyclerViewAdapter adapter;
    private List<CategoryItemData> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        recyclerView = findViewById(R.id.CategoryrecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        int typeOfFood = intent.getIntExtra("Type", 0);
        // Prepare the data for the RecyclerView
        dataList = createDataList(typeOfFood);

        // Create and set the adapter
        adapter = new CategoryRecyclerViewAdapter(dataList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(this);

        ImageView backButton = findViewById(R.id.CategorybackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHomePage = new Intent(CategoryActivity.this, HomepageActivity.class);
                startActivity(goHomePage);
            }
        });

    }
        @Override
        public void onCategoryItemClick ( int position){
        CategoryItemData clickedItem = dataList.get(position);
        Log.d("CategoryActivity", "Clicked item position: " + position);

        Intent sendFoodName = new Intent(CategoryActivity.this, DetailActivity.class);
        String foodNameSend = dataList.get(position).getTitle();
        sendFoodName.putExtra("foodName", foodNameSend);
        startActivity(sendFoodName);
    }
    private List<CategoryItemData> createDataList(int Type) {
        List<CategoryItemData> dataList = new ArrayList<>();
        List<String> FoodList = null;
        FoodData.initializeFoodItems();

        switch (Type) {
            case 1 :
                FoodList = FoodData.beefFoodList;
                break;
            case 2 :
                FoodList = FoodData.seaFoodList;
                break;
            case 3 :
                FoodList = FoodData.chickenFoodList;
                break;
            case 4 :
                FoodList = FoodData.vegetarianFoodList;
                break;
            case 5 :
                FoodList = FoodData.dessertFoodList;
                break;
            case 6 :
                FoodList = FoodData.pastaFoodList;
                break;
        }

        for (int i = 0; i < FoodList.size(); i++) {
            String FoodName = FoodList.get(i);
            try {
                GetDataTask getDataTask = new GetDataTask(FoodName); // Instantiate GetDataTask
                String allData = getDataTask.execute().get(); // Wait for task execution and get the result

                if (allData != null) {
                    String Tag = FoodData.getValueOf(allData, "strTags");
                    String Picture = FoodData.getValueOf(allData, "strMealThumb");

                    if (Tag.equals("{")) {
                        Tag = "-";
                    }

                    CategoryItemData randomItem = new CategoryItemData(FoodName, Tag, Picture);
                    dataList.add(randomItem);
                } else {
                    // Handle the case where allData is null
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        }

        return dataList;
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