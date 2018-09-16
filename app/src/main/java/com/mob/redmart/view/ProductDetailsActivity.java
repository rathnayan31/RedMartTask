package com.mob.redmart.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mob.redmart.R;
import com.mob.redmart.model.ProductData;
import com.mob.redmart.viewModel.ProductViewModel;

public class ProductDetailsActivity extends AppCompatActivity {
    private ProductViewModel viewActivityModel;
    private ImageView imageView;
    private TextView tvName,tvPrice,tvDes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);

        if(getSupportActionBar() != null)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inIt();
        String id = "";
        try{
            id= getIntent().getStringExtra("id");
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                String imageTransitionName = getIntent().getExtras().getString("trans");
                imageView.setTransitionName(imageTransitionName);
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        viewActivityModel.getItemDetail(id);
        observeProductDetail();

    }

    private void observeProductDetail() {
        viewActivityModel.prodDetailsLiveData.observe(this, new Observer<ProductData>() {
            @Override
            public void onChanged(@Nullable ProductData productData) {
                if (productData == null)
                    return;

                tvName.setText(productData.getProductName());
                tvPrice.setText("SGD " + productData.getProductPriceList().getProductPrice());
                tvDes.setText(productData.getProductDesc());
                Glide.with(ProductDetailsActivity.this).load(ProductAdapter.BASE_URL_IMG + productData.getImageList().getImageName())
                        .apply(new RequestOptions().override(500,500).placeholder(R.drawable.ic_launcher_foreground)
                                .error(R.drawable.ic_launcher_background))
                        .thumbnail(0.5f)
                        .into(imageView);
            }
        });
    }

    private void inIt() {
        tvName = findViewById(R.id.prodName);
        tvDes = findViewById(R.id.prodDesc);
        tvPrice = findViewById(R.id.prodPrice);
        imageView = findViewById(R.id.prodImage);
        viewActivityModel = ViewModelProviders.of(this).get(ProductViewModel.class);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
