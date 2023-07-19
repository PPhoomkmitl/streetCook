package com.example.streetcook;

public class FavoriteItemData {
    private String title;
    private String description;
    private String image;

    public FavoriteItemData(String title, String description, String image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }
}
