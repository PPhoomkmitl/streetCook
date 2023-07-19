package com.example.streetcook;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FoodData {
    public static List<String> allFoodList;
    public static List<String> beefFoodList;
    public static List<String> seaFoodList;
    public static List<String> chickenFoodList;
    public static List<String> vegetarianFoodList;
    public static List<String> dessertFoodList;
    public static List<String> pastaFoodList;

    public String allData;
    private String foodName;

    public static void initializeFoodItems() {
        // Seafood
        seaFoodList = new ArrayList<>();
        seaFoodList.add("Fish pie");
        seaFoodList.add("Recheado Masala Fish");
        seaFoodList.add("Cajun spiced fish tacos");
        seaFoodList.add("Three Fish Pie");
        seaFoodList.add("Fish Stew with Rouille");
        seaFoodList.add("Saltfish and Ackee");
        seaFoodList.add("Escovitch Fish");
        seaFoodList.add("Fish fofos");
        seaFoodList.add("Portuguese fish stew (Caldeirada de peixe)");

        // Chicken
        chickenFoodList = new ArrayList<>();
        chickenFoodList.add("Teriyaki Chicken Casserole");
        chickenFoodList.add("Chicken Enchilada Casserole");
        chickenFoodList.add("Potato Gratin with Chicken");
        chickenFoodList.add("Chicken Handi");
        chickenFoodList.add("Chicken Alfredo Primavera");
        chickenFoodList.add("Tandoori chicken");
        chickenFoodList.add("Kentucky Fried Chicken");
        chickenFoodList.add("Chicken Fajita Mac and Cheese");
        chickenFoodList.add("Katsu Chicken curry");
        chickenFoodList.add("Crock Pot Chicken Baked Tacos");

        // Vegetarian
        vegetarianFoodList = new ArrayList<>();
        vegetarianFoodList.add("Tortang Talong");
        vegetarianFoodList.add("Grilled eggplant with coconut milk");
        vegetarianFoodList.add("Eggplant Adobo");
        vegetarianFoodList.add("Crispy Eggplant");
        vegetarianFoodList.add("Vegetarian Chilli");
        vegetarianFoodList.add("Dal fry");
        vegetarianFoodList.add("Spicy North African Potato Salad");
        vegetarianFoodList.add("Ribollita");
        vegetarianFoodList.add("Roasted Eggplant With Tahini, Pine Nuts, and Lentils");
        vegetarianFoodList.add("Stovetop Eggplant With Harissa, Chickpeas, and Cumin Yogurt");
        vegetarianFoodList.add("Vegetarian Casserole");
        vegetarianFoodList.add("Matar Paneer");

        // Beef
        beefFoodList = new ArrayList<>();
        beefFoodList.add("Beef Wellington");
        beefFoodList.add("Beef Brisket Pot Roast");
        beefFoodList.add("Beef Sunday Roast");
        beefFoodList.add("Braised Beef Chilli");
        beefFoodList.add("Massaman Beef curry");
        beefFoodList.add("Beef stroganoff");
        beefFoodList.add("Beef Dumpling Stew");
        beefFoodList.add("Beef and Mustard Pie");
        beefFoodList.add("Minced Beef Pie");
        beefFoodList.add("Beef and Oyster pie");
        beefFoodList.add("Beef Bourguignon");
        beefFoodList.add("Jamaican Beef Patties");
        beefFoodList.add("Beef Asado");
        beefFoodList.add("Beef Caldereta");

        // Dessert
        dessertFoodList = new ArrayList<>();
        dessertFoodList.add("Apam balik");
        dessertFoodList.add("Apple & Blackberry Crumble");
        dessertFoodList.add("Apple Frangipan Tart");
        dessertFoodList.add("Bakewell tart");
        dessertFoodList.add("Banana Pancakes");
        dessertFoodList.add("Battenberg Cake");
        dessertFoodList.add("BeaverTails");
        dessertFoodList.add("Blackberry Fool");
        dessertFoodList.add("Bread and Butter Pudding");
        dessertFoodList.add("Budino Di Ricotta");

        // Pasta
        pastaFoodList = new ArrayList<>();
        pastaFoodList.add("Chilli prawn linguine");
        pastaFoodList.add("Fettuccine Alfredo");
        pastaFoodList.add("Fettucine alfredo");
        pastaFoodList.add("Grilled Mac and Cheese Sandwich");
        pastaFoodList.add("Lasagna Sandwiches");
        pastaFoodList.add("Lasagne");
        pastaFoodList.add("Pilchard puttanesca");
        pastaFoodList.add("Spaghetti alla Carbonara");
        pastaFoodList.add("Venetian Duck Ragu");

        allFoodList = new ArrayList<>();
        // Add all food items from different lists to the allFoodList
        allFoodList.addAll(seaFoodList);
        allFoodList.addAll(chickenFoodList);
        allFoodList.addAll(vegetarianFoodList);
        allFoodList.addAll(beefFoodList);
        allFoodList.addAll(dessertFoodList);
        allFoodList.addAll(pastaFoodList);
    }


    public class GetDataTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            return FoodData.getAllData(foodName);
        }
    }

    public static String getAllData(String foodName) {
        String result = null;
        try {
            String apiUrl = "https://www.themealdb.com/api/json/v1/1/search.php?s=" + foodName;
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                reader.close();
                result = stringBuilder.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getValueOf(String allData, String key) {
        String value = null;
        try {
            JSONObject jsonObject = new JSONObject(allData);
            JSONArray mealsArray = jsonObject.getJSONArray("meals");
            JSONObject mealObject = mealsArray.getJSONObject(0);
            value = mealObject.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static String formatTextForTextView(String text) {
        String formattedText = text.replace("\r\n", "\n")
                .replace("\n\n", "\n")
                .replace("\n", "\n\n");
        return formattedText;
    }

    public static String getVideoUrl(String allData) {
        String videoUrl = getValueOf(allData, "strYoutube");
        if (!videoUrl.startsWith("http")) {
            videoUrl = "https://" + videoUrl;
        }
        return videoUrl;
    }

    public static String extractVideoId(String videoUrl) {
        String videoId = null;
        String pattern = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(videoUrl);
        if (matcher.find()) {
            videoId = matcher.group();
        }
        return videoId;
    }

    public static List<String> extractIngredients(String jsonString) {
        List<String> ingredients = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray mealsArray = jsonObject.getJSONArray("meals");
            if (mealsArray.length() > 0) {
                JSONObject mealObject = mealsArray.getJSONObject(0);
                for (int i = 1; i <= 20; i++) {
                    String ingredient = mealObject.getString("strIngredient" + i);
                    if (!ingredient.isEmpty()) {
                        ingredients.add(ingredient);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return removeDuplicates(ingredients);
    }

    private static List<String> removeDuplicates(List<String> list) {
        Set<String> set = new HashSet<>(list);
        return new ArrayList<>(set);
    }

}
