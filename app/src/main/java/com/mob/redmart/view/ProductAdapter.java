package com.mob.redmart.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mob.redmart.R;
import com.mob.redmart.model.ProductData;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private Context context;
    private ActivityCallBack callBack;
    private ArrayList<ProductData> productsList = new ArrayList<>();
    public static final String BASE_URL_IMG = "http://media.redmart.com/newmedia/200p";

    public ProductAdapter(Context context) {
        this.context = context;
        this.callBack = (ActivityCallBack) context;
    }

    public void changeDataList(ArrayList<ProductData> list){
        this.productsList = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return getViewHolder(parent, inflater);

    }

    @NonNull
    private MyViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {

        View v1 = inflater.inflate(R.layout.product_adapter, parent, false);
        return new MyViewHolder(v1);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.setHolderData(this.productsList.get(position));

    }

    @Override
    public int getItemCount() {
        return productsList == null ? 0 : productsList.size();
    }



    public void addDataList(ArrayList<ProductData> productDataArrayList) {

        this.productsList.addAll(productDataArrayList);
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
         TextView tvProductName;
         TextView tvProductPrice;
         ImageView imgProd;



        MyViewHolder(final View itemView) {
            super(itemView);

            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
            imgProd =  itemView.findViewById(R.id.imageProd);


          itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    callBack.onItemClick(imgProd,productsList.get(getAdapterPosition()).getProductId());

                }
            });

        }

        void setHolderData(ProductData productData){
            tvProductName.setText(productData.getProductName());
            tvProductPrice.setText("SGD " + productData.getProductPriceList().getProductPrice());
            tvProductName.setText(productData.getProductName());
            ViewCompat.setTransitionName(imgProd, productData.getProductId());

            Glide.with(context).load(BASE_URL_IMG + productData.getImageList().getImageName())
                    .apply(new RequestOptions().override(200,200).placeholder(R.drawable.ic_launcher_foreground)
                            .error(R.drawable.ic_launcher_background))
                    .thumbnail(0.5f)
                    .into(imgProd);
        }
    }


    interface ActivityCallBack{

        /**
         * * This function is invoked when user attempts a click on the item.*/

        void onItemClick(ImageView imageView,String itemId);
    }
}


