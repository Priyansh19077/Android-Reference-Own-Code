package com.example.learning_recycler_view;

public class ModelClass {
    private int imageResource;
    private String title;
    private String body;

    public ModelClass(int imageResource, String title, String body) {
        this.imageResource = imageResource;
        this.title = title;
        this.body = body;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
