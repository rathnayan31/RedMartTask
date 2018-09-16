package com.mob.redmart.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductData {

    public ProductData(String productName, String productDesc, String productId, ProductImage imageList, ProductPrice productPriceList) {
            this.productName = productName;
            this.productDesc = productDesc;
            this.productId = productId;
            this.imageList = imageList;
            this.productPriceList = productPriceList;
    }

    @SerializedName("title")
    @Expose
    private String productName;

    @SerializedName("desc")
    @Expose
    private String productDesc;

    @SerializedName("id")
    @Expose
    private String productId;

    @SerializedName("img")
    @Expose
    private ProductImage imageList;

    @SerializedName("pricing")
    @Expose
    private ProductPrice productPriceList;

    public String getProductName() {
        return productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public String getProductId() {
        return productId;
    }

    public ProductImage getImageList() {
        return imageList;
    }

    public ProductPrice getProductPriceList() {
        return productPriceList;
    }

}
