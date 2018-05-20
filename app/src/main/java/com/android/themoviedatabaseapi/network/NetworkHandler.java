package com.android.themoviedatabaseapi.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkHandler {

    private static NetworkHandler instance;
    private Retrofit retrofit;
    private iAppServices services;

    public static NetworkHandler getInstance() {
        return instance == null? (instance = new NetworkHandler()) : instance;
    }

    private NetworkHandler(){
        retrofit = new Retrofit.Builder()
                .baseUrl(NetworkConstants.BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        services = retrofit.create(iAppServices.class);
    }

    public iAppServices getServices() {
        return services;
    }

}
