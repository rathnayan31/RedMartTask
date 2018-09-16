package com.mob.redmart.apiUtils;

import com.mob.redmart.model.ProductDetail;
import com.mob.redmart.model.ProductSearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * This Interface is used for API calls to retrive product data and and product details
 * */

public interface ApiInterface {

    @GET("search")
    Call<ProductSearch> getProductData(

            @Query("page") int pageIndex,
            @Query("page_size") int pageSizeIndex

    );


    @GET("products/{id}")
    Call<ProductDetail> getProductDetail(
            @Path("id") String prodId);

}
