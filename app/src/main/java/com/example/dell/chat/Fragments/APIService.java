package com.example.dell.chat.Fragments;

import com.example.dell.chat.Notifications.MyResponse;
import com.example.dell.chat.Notifications.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Dell on 07/06/2019.
 */

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAARcH9u-o:APA91bH4Zv6cENXzC2i5KNhA59KntTbxIcjZf1DNFut9jZoc4Uui2x0ZdcmiiID2qlLgxutX88xVAZPRzYHHdn97Sh81_Fls7GzbsFb95LzZhLCVPgaZJWFUScOo7nt8UZMDiR4cF0iZ"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
