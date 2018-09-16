package com.mob.redmart.repository;

import android.support.annotation.NonNull;
import android.util.Log;

import com.mob.redmart.apiUtils.ProductApiClient;
import com.mob.redmart.model.ProductData;
import com.mob.redmart.model.ProductDetail;
import com.mob.redmart.model.ProductSearch;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * *This class is used for only for Network Operation*/

public class ProductRepo {


    public void setViewModelCallback(ViewModelCallback viewModelCallback) {
        this.viewModelCallback = viewModelCallback;
    }

    private ViewModelCallback viewModelCallback;

    private static volatile ProductRepo repoInstance;
    public final String TAG = ProductRepo.class.getSimpleName();

    private ProductRepo(){}

    public void getProdData(int pageIndex , int pageSize){

        ProductApiClient.getApiInterface().getProductData(pageIndex,pageSize).enqueue(new Callback<ProductSearch>() {
            @Override

            public void onResponse( @NonNull Call<ProductSearch> call,  @NonNull Response<ProductSearch> response) {
                ArrayList<ProductData> productDataList = null;
                if (response.body() == null){
                    Log.e(TAG, "onResponse: is null.");
                    viewModelCallback.onProductDataLoad(productDataList);
                    return;
                }

                productDataList = Objects.requireNonNull(response.body()).getProductDataList();
                viewModelCallback.onProductDataLoad(productDataList);
            }

            @Override
            public void onFailure( @NonNull Call<ProductSearch> call,   @NonNull Throwable t) {
                Log.d(TAG, "onFailure: "+ t );
            }
        });

    }

    public void getProductDetails(String id){
        ProductApiClient.getApiInterface().getProductDetail(id).enqueue(new Callback<ProductDetail>() {
            @Override
            public void onResponse(@NonNull  Call<ProductDetail> call,@NonNull Response<ProductDetail> response) {
                if (response.body() == null){
                    Log.e(TAG, "onResponse: is null.");
                    return;
                }
                ProductData productDataList = null;
                productDataList = Objects.requireNonNull(response.body()).getProductDetailData();

                if(productDataList != null)
                    viewModelCallback.onProductDetails(productDataList);

            }

            @Override
            public void onFailure(@NonNull Call<ProductDetail> call,@NonNull Throwable t) {

            }
        });
    }

    public interface ViewModelCallback{

        void  onProductDataLoad(ArrayList<ProductData> productDataList);

        void onProductDetails(ProductData data);
    }

    public static ProductRepo getInstance(){
        if(repoInstance ==null){

            synchronized (ProductRepo.class) {
                if (repoInstance == null) {
                    repoInstance = new ProductRepo();
                }
            }
        }
        return repoInstance;
    }
}