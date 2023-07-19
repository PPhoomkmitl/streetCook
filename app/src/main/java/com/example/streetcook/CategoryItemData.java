package com.example.streetcook;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class CategoryItemData {
    private String title;
    private String tag;

    private String image;

    public CategoryItemData(String title, String description, String image) {

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
