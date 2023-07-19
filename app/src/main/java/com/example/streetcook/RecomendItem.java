package com.example.streetcook;

public class RecomendItem {
    private String title;
    private String tag;
    private String image;

    public RecomendItem(String title, String description, String image) {
        this.title = title;
        this.tag = description;
        this.image = image;
    }
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return tag;
    }

    public String getImageResource() { return image; }
}