package com.mob.redmart.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDetail {

    public ProductData getProductDetailData() {
        return productDetailData;
    }

    @SerializedName("product")
    @Expose
    private ProductData productDetailData;
}
