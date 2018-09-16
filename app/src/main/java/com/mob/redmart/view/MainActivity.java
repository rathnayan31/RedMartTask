package com.mob.redmart.view;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.mob.redmart.R;
import com.mob.redmart.model.ProductData;
import com.mob.redmart.viewModel.ProductViewModel;

import java.util.ArrayList;

/**
 * *This class is used for displaying products along with price , name etc. with a limit of 20 items per page.*/

public class MainActivity extends AppCompatActivity implements ProductAdapter.ActivityCallBack{

    private static final String TAG = MainActivity.class.getSimpleName();

    LinearLayoutManager layoutManager;
    ProductAdapter productAdapter;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    private boolean isLoading = false;
    private int previousTotal,currentPage;
    private ProductViewModel viewActivityModel;
    private int endPage = 20;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       inIt();
        attachListeners();
        viewActivityModel.getProductData(currentPage, endPage);
       observeProductList();
    }

    private void inIt() {
        recyclerView = (RecyclerView) findViewById(R.id.rcView_products);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        productAdapter = new ProductAdapter(this);
        layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        viewActivityModel = ViewModelProviders.of(this).get(ProductViewModel.class);
    }

    private void observeProductList() {
        viewActivityModel.prodLiveData.observe(this, new Observer<ArrayList<ProductData>>() {
            @Override
            public void onChanged(@Nullable ArrayList<ProductData> productDataArrayList) {
                progressBar.setVisibility(View.GONE);
                if (productDataArrayList == null){
                    return;
                }
                if(currentPage == 0){
                    recyclerView.setAdapter(productAdapter);
                    productAdapter.changeDataList(productDataArrayList);
                }

                else
                    productAdapter.addDataList(productDataArrayList);

            }
        });
    }

    private void attachListeners() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }


            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                if (progressBar.getVisibility() == View.VISIBLE)
                    return;
                // If user is scrolling top to down.
                if(dy > 0 ){
                    if(isLoading){
                        if (totalItemCount > previousTotal){
                            isLoading = false;
                            previousTotal = totalItemCount;
                        }
                    }

                    // Load data when user is at last index
                    if (!isLoading && (totalItemCount - visibleItemCount) <= (firstVisibleItemPosition)){
                        currentPage++;
                        progressBar.setVisibility(View.VISIBLE);
                        viewActivityModel.getProductData(currentPage,endPage);
                    }
                }
            }
        });
    }

    @Override
    public void onItemClick(ImageView imageView,String itemId) {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                imageView,
                ViewCompat.getTransitionName(imageView));
        Intent intent = new Intent(this,ProductDetailsActivity.class);
        intent.putExtra("id",itemId);
        intent.putExtra("trans",ViewCompat.getTransitionName(imageView));
        Bundle bundle = options.toBundle();
        startActivity(intent,bundle);
    }
}
