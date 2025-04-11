package com.csds.transactiondissplay.networkutility;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitObject {

    static Retrofit retrofit;
    public static retrofig_service retrofit_service;
    public static retrofig_service getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.prepstripe.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            retrofit_service=retrofit.create(retrofig_service.class);

            return retrofit_service;
        } else {
            return retrofit_service;
        }
    }


    }
