package com.mob.redmart.model;

import com.google.gson.annotations.SerializedName;

public class ProductImage {

    @SerializedName("name")
    private String imageName;


    public String getImageName() {
        return imageName;
    }

}
