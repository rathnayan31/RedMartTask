package com.mob.redmart.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductPrice {

    public ProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    @SerializedName("price")
    @Expose
    private String productPrice;


    public String getProductPrice() {
        return productPrice;
    }


}
