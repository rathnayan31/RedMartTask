package com.mob.redmart.viewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.mob.redmart.model.ProductData;
import com.mob.redmart.repository.ProductRepo;

import java.util.ArrayList;

public class ProductViewModel extends ViewModel implements ProductRepo.ViewModelCallback
{

    public MutableLiveData<ArrayList<ProductData>> prodLiveData = new MutableLiveData<>();
    public MutableLiveData<ProductData> prodDetailsLiveData = new MutableLiveData<>();

    public void getProductData(int currentPage, int pageSize){
        ProductRepo instance = ProductRepo.getInstance();
        instance.setViewModelCallback(this);
        instance.getProdData(currentPage  ,pageSize);
    }

    public void getItemDetail(String itmeId){
        ProductRepo instance = ProductRepo.getInstance();
        instance.setViewModelCallback(this);
        instance.getProductDetails(itmeId);
    }

    @Override
    public void onProductDataLoad(ArrayList<ProductData> productDataList) {
        prodLiveData.setValue(productDataList);

    }

    @Override
    public void onProductDetails(ProductData data) {
        prodDetailsLiveData.setValue(data);
    }
}
