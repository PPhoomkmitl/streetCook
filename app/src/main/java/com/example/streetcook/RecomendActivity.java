package com.example.streetcook;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class RecomendActivity extends AppCompatActivity implements RecomendRecyclerViewAdapter.RecomendItemClickListener {

    private RecyclerView recyclerView;
    private RecomendRecyclerViewAdapter adapter;
    protected List<RecomendItem> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomend);

        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.RecomendrecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Prepare the data for the RecyclerView
        dataList = createDataList();

        // Create and set the adapter
        adapter = new RecomendRecyclerViewAdapter(this.dataList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(this);

        ImageView backButton = findViewById(R.id.RecomendbackButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent test = new Intent(RecomendActivity.this, HomepageActivity.class);
                startActivity(test);
            }
        });




    }

    @Override
    public void onRecomendItemClick(int position) {
        Log.d("RecomendActivity", "Clicked item position: " + position);

        Intent sendFoodName = new Intent(RecomendActivity.this, DetailActivity.class);
        String foodNameSend = dataList.get(position).getTitle();
        sendFoodName.putExtra("foodName", foodNameSend);
        startActivity(sendFoodName);
        // Perform any other action you want when an item is clicked
    }

    protected List<RecomendItem> createDataList() {
        List<RecomendItem> dataList = new ArrayList<>();
        FoodData.initializeFoodItems();
        List<String> allFoodList = FoodData.allFoodList;
        Random random = new Random();
        Set<Integer> usedIndices = new HashSet<>();

        while (dataList.size() < 10 && usedIndices.size() < allFoodList.size()) {
            int randomIndex = random.nextInt(allFoodList.size());

            // Check if the index has already been used
            if (usedIndices.contains(randomIndex)) {
                continue;
            }

            usedIndices.add(randomIndex);
            String randomFood = allFoodList.get(randomIndex);

            try {
                RecomendActivity.GetDataTask getDataTask = new RecomendActivity.GetDataTask(randomFood);
                String allData = getDataTask.execute().get();

                if (allData != null) {
                    String Tag = FoodData.getValueOf(allData, "strTags");
                    String Picture = FoodData.getValueOf(allData, "strMealThumb");

                    if (Tag.equals("{")) {
                        Tag = "-";
                    }

                    RecomendItem randomItem = new RecomendItem(randomFood, Tag, Picture);
                    dataList.add(randomItem);
                } else {
                    // Handle the case where allData is null
                }
            } catch (Exception e) {
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