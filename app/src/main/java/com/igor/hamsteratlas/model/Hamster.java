package com.igor.hamsteratlas.model;

import com.google.gson.annotations.SerializedName;

public class Hamster {
    @SerializedName("title")
    private String mName;


    @SerializedName("description")
    private String mDescription;

    @SerializedName("pinned")
    private Boolean mPinned;

    @SerializedName("image")
    private String mImage;

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public Boolean isPinned() {
        return mPinned;
    }

    public String getImage() {
        return mImage;
    }



}
