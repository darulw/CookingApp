package com.example.cookingapp.model;

import java.util.UUID;

public class Recipe {
    private UUID mID;
    private String mTitle;
    private String mImage;
    private int sampleImage;
    private static int permission;


    /*------------------------------------------------*/
    //constructors
    public Recipe(){
        this(UUID.randomUUID());
    }
    public Recipe(UUID id){
        mID = id;
    }
    /*------------------------------------------------*/


    /*------------------------------------------------*/
    //getters and setters
    public int getSampleImage() {
        return sampleImage;
    }

    public void setSampleImage(int sampleImage) {
        this.sampleImage = sampleImage;
    }

    public UUID getID() {
        return mID;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public static int getPermission() {
        return permission;
    }

    public static void setPermission(int permission) {
        Recipe.permission = permission;
    }
    /*------------------------------------------------*/
}
