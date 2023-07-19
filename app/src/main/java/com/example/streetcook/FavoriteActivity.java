package com.example.streetcook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FavoriteActivity extends AppCompatActivity implements FavoriteRecyclerViewAdapter.OnDeleteClickListener, FavoriteRecyclerViewAdapter.OnDetailClickListener {

    private RecyclerView recyclerView;
    private FavoriteRecyclerViewAdapter adapter;
    private List<FavoriteItemData> dataList;
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        sharedPreferences = getSharedPreferences("DataList", MODE_PRIVATE);
        gson = new Gson();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataList = getDataList();
        context = this.context;

        Intent intent = getIntent();



        adapter = new FavoriteRecyclerViewAdapter(dataList);
        adapter.setOnDeleteClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnItemClickListener(this);

        ImageView back = findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToHomepage = new Intent(FavoriteActivity.this, HomepageActivity.class);
                startActivity(goToHomepage);
                int dataSize = dataList.size();
                Log.d("DataList", "Number of values in dataList: " + dataSize);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_Favorite);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.bottom_Home) {
                startActivity(new Intent(getApplicationContext(), HomepageActivity.class));
                return true;
            } else if (item.getItemId() == R.id.bottom_Search) {
                startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                return true;
            } else if (item.getItemId() == R.id.bottom_Favorite) {
                return true;
            } else if (item.getItemId() == R.id.bottom_Setting) {
                startActivity(new Intent(getApplicationContext(), SettingActivity.class));
                return true;
            }
            return false;
        });
        saveDataList(dataList);
    }

//    private void setupRecyclerView() {
//
//        adapter.setOnItemClickListener(new FavoriteRecyclerViewAdapter.FavoriteItemClickListener() {
//            @Override
//            public void onFavoriteItemClick(int position) {
//                FavoriteItemData itemData = dataList.get(position);
//                String selectedFood = itemData.getTitle();
//
//                Intent intent = new Intent(FavoriteActivity.this, DetailActivity.class);
//                intent.putExtra("foodName", selectedFood);
//                startActivity(intent);
//            }
//        });}

    @Override
    public void onDetailClick(int position) {
        Log.d("Detail", "Clicked item position: " + position);

        Intent sendFoodName = new Intent(FavoriteActivity.this, DetailActivity.class);
        String foodNameSend = dataList.get(position).getTitle();
        sendFoodName.putExtra("foodName", foodNameSend);
        startActivity(sendFoodName);
    }

    @Override
    public void onDeleteClick(int position) {
        deleteItem(position);
        saveFavoriteList(dataList);
    }



    private void saveFavoriteList(List<FavoriteItemData> favoriteList) {
        UserDataManager userDataManager = new UserDataManager(this);
        userDataManager.saveFavoriteList(favoriteList);
        adapter.notifyDataSetChanged();
    }


    private List<FavoriteItemData> getDataList() {
        UserDataManager userDataManager = new UserDataManager(this);
        List<FavoriteItemData> dataList = userDataManager.getFavoriteList();

        if (dataList.size() > 0) {
            for (int i = 0; i < 10; i++) {
                System.out.println("dataList contains favorite food items.");
            }
        } else {
            for (int i = 0; i < 10; i++) {
                System.out.println("dataList does not contain any favorite food items yet.");
            }
        }

        return dataList;
    }


    private void saveDataList(List<FavoriteItemData> dataList) {
        String dataListJson = gson.toJson(dataList);
        sharedPreferences.edit().putString("dataList", dataListJson).apply();
        adapter.notifyDataSetChanged();
    }

    private List<FavoriteItemData> createDataList() {
        List<FavoriteItemData> dataList = new ArrayList<>();
        return dataList;
    }

    private void deleteItem(int position) {
        if (position >= 0 && position < dataList.size()) {
            dataList.remove(position);
            saveDataList(dataList);
        }
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