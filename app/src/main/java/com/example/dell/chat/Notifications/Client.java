package com.example.dell.chat.Notifications;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dell on 07/06/2019.
 */

public class Client {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(String url){
        if (retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
