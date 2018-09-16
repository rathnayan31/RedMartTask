package com.mob.redmart.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductSearch {


    @SerializedName("products")
    @Expose
    private ArrayList<ProductData> productDataList;

    public ArrayList<ProductData> getProductDataList() {
        return productDataList;
    }



}
