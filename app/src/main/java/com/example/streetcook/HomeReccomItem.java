package com.example.streetcook;

public class HomeReccomItem {
    private String title;
    private String image;

    public HomeReccomItem(String title,String image) {
        this.title = title;
        this.image = image;
    }
    public String getHomeReccomTitle() {
        return title;
    }
    public String getHomeReccomImageResource() { return image; }

}
