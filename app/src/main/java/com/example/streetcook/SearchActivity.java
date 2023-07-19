package com.example.streetcook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.view.View;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> foodList;

    private List<String> AllfoodList;
    private List<String> foodTypeSeafood;
    private List<String> foodTypeChicken;
    private List<String> foodTypePasta;
    private List<String> foodTypeVegetarian;
    private List<String> foodTypeBeef;
    private List<String> foodTypeDessert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        EditText searchView = findViewById(R.id.searchView);
        listView = findViewById(R.id.listView);
//        TextView TextAtSearch = findViewById(R.id.TextAtSearch);

        // Seafood
        foodTypeSeafood = new ArrayList<>();
        foodTypeSeafood.add("Fish pie");
        foodTypeSeafood.add("Recheado Masala Fish");
        foodTypeSeafood.add("Cajun spiced fish tacos");
        foodTypeSeafood.add("Three Fish Pie");
        foodTypeSeafood.add("Fish Stew with Rouille");
        foodTypeSeafood.add("Saltfish and Ackee");
        foodTypeSeafood.add("Escovitch Fish");
        foodTypeSeafood.add("Fish fofos");
        foodTypeSeafood.add("Portuguese fish stew (Caldeirada de peixe)");

        // Chicken
        foodTypeChicken = new ArrayList<>();
        foodTypeChicken.add("Teriyaki Chicken Casserole");
        foodTypeChicken.add("Chicken Enchilada Casserole");
        foodTypeChicken.add("Potato Gratin with Chicken");
        foodTypeChicken.add("Chicken Handi");
        foodTypeChicken.add("Chicken Alfredo Primavera");
        foodTypeChicken.add("Tandoori chicken");
        foodTypeChicken.add("Kentucky Fried Chicken");
        foodTypeChicken.add("Chicken Fajita Mac and Cheese");
        foodTypeChicken.add("Katsu Chicken curry");
        foodTypeChicken.add("Crock Pot Chicken Baked Tacos");

        // Vegetarian
        foodTypeVegetarian = new ArrayList<>();
        foodTypeVegetarian.add("Tortang Talong");
        foodTypeVegetarian.add("Grilled eggplant with coconut milk");
        foodTypeVegetarian.add("Eggplant Adobo");
        foodTypeVegetarian.add("Crispy Eggplant");
        foodTypeVegetarian.add("Vegetarian Chilli");
        foodTypeVegetarian.add("Dal fry");
        foodTypeVegetarian.add("Spicy North African Potato Salad");
        foodTypeVegetarian.add("Ribollita");
        foodTypeVegetarian.add("Roasted Eggplant With Tahini, Pine Nuts, and Lentils");
        foodTypeVegetarian.add("Stovetop Eggplant With Harissa, Chickpeas, and Cumin Yogurt");
        foodTypeVegetarian.add("Vegetarian Casserole");
        foodTypeVegetarian.add("Matar Paneer");

        // Beef
        foodTypeBeef = new ArrayList<>();
        foodTypeBeef.add("Beef Wellington");
        foodTypeBeef.add("Beef Brisket Pot Roast");
        foodTypeBeef.add("Beef Sunday Roast");
        foodTypeBeef.add("Braised Beef Chilli");
        foodTypeBeef.add("Massaman Beef curry");
        foodTypeBeef.add("Beef stroganoff");
        foodTypeBeef.add("Beef Dumpling Stew");
        foodTypeBeef.add("Beef and Mustard Pie");
        foodTypeBeef.add("Minced Beef Pie");
        foodTypeBeef.add("Beef and Oyster pie");
        foodTypeBeef.add("Beef Bourguignon");
        foodTypeBeef.add("Jamaican Beef Patties");
        foodTypeBeef.add("Beef Asado");
        foodTypeBeef.add("Beef Caldereta");

        // Dessert
        foodTypeDessert = new ArrayList<>();
        foodTypeDessert.add("Apam balik");
        foodTypeDessert.add("Apple & Blackberry Crumble");
        foodTypeDessert.add("Apple Frangipan Tart");
        foodTypeDessert.add("Bakewell tart");
        foodTypeDessert.add("Banana Pancakes");
        foodTypeDessert.add("Battenberg Cake");
        foodTypeDessert.add("BeaverTails");
        foodTypeDessert.add("Blackberry Fool");
        foodTypeDessert.add("Bread and Butter Pudding");
        foodTypeDessert.add("Budino Di Ricotta");

        // Pasta
        foodTypePasta = new ArrayList<>();
        foodTypePasta.add("Chilli prawn linguine");
        foodTypePasta.add("Fettuccine Alfredo");
        foodTypePasta.add("Fettucine alfredo");
        foodTypePasta.add("Grilled Mac and Cheese Sandwich");
        foodTypePasta.add("Lasagna Sandwiches");
        foodTypePasta.add("Lasagne");
        foodTypePasta.add("Pilchard puttanesca");
        foodTypePasta.add("Spaghetti alla Carbonara");
        foodTypePasta.add("Venetian Duck Ragu");

        AllfoodList = new ArrayList<>();
        // Add all food items from different lists to the allFoodList
        AllfoodList.addAll(foodTypeSeafood);
        AllfoodList.addAll(foodTypeChicken);
        AllfoodList.addAll(foodTypeVegetarian);
        AllfoodList.addAll(foodTypeBeef);
        AllfoodList.addAll(foodTypeDessert);
        AllfoodList.addAll(foodTypePasta);

        FoodData.allFoodList = AllfoodList;
        FoodData.beefFoodList = foodTypeBeef;
        FoodData.seaFoodList = foodTypeSeafood;
        FoodData.dessertFoodList = foodTypeDessert;
        FoodData.pastaFoodList = foodTypePasta;
        FoodData.chickenFoodList = foodTypeChicken;
        FoodData.vegetarianFoodList = foodTypeVegetarian;

        //BackButton
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(SearchActivity.this,HomepageActivity.class);
                startActivity(in);
            }
        });



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_Search);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.bottom_Home) {
                startActivity(new Intent(getApplicationContext(), HomepageActivity.class));
                return true;
            } else if (item.getItemId() == R.id.bottom_Search) {
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

        searchView.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                String searchText = searchView.getText().toString().trim();
                if (!TextUtils.isEmpty(searchText)) {
                    searchFood(searchText);
                    hideSoftKeyboard();
//                    TextAtSearch.setVisibility(View.GONE);
                }
                return true;
            }
            return false;
        });
    }

    private void searchFood(String searchText) {
        // สร้าง ArrayList เพื่อเก็บรายการอาหารที่ตรงกับคำค้นหา
        List<String> filteredFoodList = new ArrayList<>();

        // วนลูปตรวจสอบคำใน AllfoodList ว่าตรงกับคำค้นหาหรือไม่
        for (String food : AllfoodList) {
            if (food.contains(searchText)) {
                filteredFoodList.add(food);
            }
        }
        // Sort the filteredFoodList in alphabetical order
        Collections.sort(filteredFoodList);

        // สร้าง ArrayAdapter สำหรับรายการอาหารที่ตรงกับคำค้นหา
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filteredFoodList);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedFood = filteredFoodList.get(position);
            Intent intent = new Intent(SearchActivity.this, DetailActivity.class);
            intent.putExtra("foodName", selectedFood);
            startActivity(intent);
        });

        // กำหนด Adapter และแสดง ListView
        listView.setAdapter(adapter);
        listView.setVisibility(View.VISIBLE);
    }


    private void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        View focusedView = getCurrentFocus();
        if (focusedView != null) {
            imm.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);
        }
    }
}
