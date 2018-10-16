package com.igor.hamsteratlas.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient mInstance = null;
    private Retrofit mRetrofit;
    private OkHttpClient mClient;

    private ApiService mApiService;

    public RetrofitClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        mClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Gson gson = new GsonBuilder()

                .create();
        mRetrofit = new Retrofit.Builder().baseUrl(ApiProperties.SCHEME + "://" + ApiProperties.HOST + ApiProperties.BASE_PATH)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(mClient)
                .build();

        mApiService = mRetrofit.create(ApiService.class);
    }
    public static RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }

        return mInstance;
    }
    public ApiService getApiService() {
        return mApiService;
    }


}
