package com.mob.redmart.apiUtils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * This is used for setting up API related operations.
 * */

public class ProductApiClient {

    private static Retrofit retrofit = null;

    public static Retrofit getProdClient() {

        // ------   Logging Purpose ------
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

         //------ Retrofit Instance
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .baseUrl("https://api.redmart.com/v1.6.0/catalog/")
                    .build();
        }
        return retrofit;
    }

    public static ApiInterface getApiInterface(){

        return getProdClient().create(ApiInterface.class);

    }
}
