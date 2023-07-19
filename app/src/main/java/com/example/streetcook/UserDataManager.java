package com.example.streetcook;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class UserDataManager {
    private static final String PREFERENCES_NAME = "UserPrefs";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_NAME = "name";
    private static final String KEY_STAY = "status";
    private static final String KEY_FAVORITE_LIST = "favorite_list";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;




    public FavoriteItemData getItemAtPosition(int position) {
        List<FavoriteItemData> favoriteList = getFavoriteList();
        if (position >= 0 && position < favoriteList.size()) {
            return favoriteList.get(position);
        }
        return null;
    }

    public void saveFavoriteItemData(FavoriteItemData itemData) {
        List<FavoriteItemData> currentList = getFavoriteList();

        boolean isDuplicate = false;
        for (FavoriteItemData data : currentList) {
            if (data.getTitle().equals(itemData.getTitle())) {
                isDuplicate = true;
                break;
            }
        }

        if (!isDuplicate) {
            currentList.add(itemData);
            saveFavoriteList(currentList);
        }
    }





    public List<FavoriteItemData> getFavoriteList() {
        String favoriteListJson = sharedPreferences.getString(KEY_FAVORITE_LIST, null);
        List<FavoriteItemData> favoriteList;

        if (favoriteListJson != null) {
            favoriteList = new Gson().fromJson(favoriteListJson, new TypeToken<List<FavoriteItemData>>(){}.getType());
        } else {
            favoriteList = new ArrayList<>();
        }

        return favoriteList;
    }

    public void saveFavoriteList(List<FavoriteItemData> favoriteList) {
        String favoriteListJson = new Gson().toJson(favoriteList);
        editor.putString(KEY_FAVORITE_LIST, favoriteListJson);
        editor.apply();
    }

    public void printFavoriteList() {
        List<FavoriteItemData> favoriteList = getFavoriteList();

        for (FavoriteItemData itemData : favoriteList) {
            System.out.println("Title: " + itemData.getTitle());
            System.out.println("Tag: " + itemData.getDescription());
            System.out.println("Image: " + itemData.getImage());
            System.out.println("------------------");
        }
    }



    public UserDataManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveUserDataWithOutImage(String username, String password) {
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_PASSWORD, password);
        editor.apply();
    }

    public void saveImageOnly(String image) {
        editor.putString(KEY_IMAGE, image);
        editor.apply();
    }

    public void saveNameOnly(String name) {
        editor.putString(KEY_NAME, name);
        editor.apply();
    }


    public void savePasswordOnly(String password) {
        editor.putString(KEY_PASSWORD, password);
        editor.apply();
    }

    public void saveStayStatus(boolean status) {
        editor.putBoolean(KEY_STAY, status);
        editor.apply();
    }

    public void cleaStay() {
        editor.remove(KEY_STAY);
        editor.apply();
    }

    public void clearUserData() {
        editor.clear();
        editor.apply();
    }

    public String getUsername() {
        return sharedPreferences.getString(KEY_USERNAME, "");
    }

    public String getPassword() {
        return sharedPreferences.getString(KEY_PASSWORD, "");
    }
    public String getImage() {
        return sharedPreferences.getString(KEY_IMAGE, "");
    }

    public String getName() {return sharedPreferences.getString(KEY_NAME,"");}

    public boolean getStay() {return sharedPreferences.getBoolean(KEY_STAY,false);}
}
