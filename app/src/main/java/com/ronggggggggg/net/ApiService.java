package com.ronggggggggg.net;


import com.ronggggggggg.model.getTokenModel;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    //登录
//    String REQUEST_LOGIN = "bmwl_lc/bmwl/android/factoryUser/login";
    String REQUEST_LOGIN = "user/getToken.json";
//    @POST(REQUEST_LOGIN)
//    Call<LoginResponse> login(
//            @Query("phone") String phone,
//            @Query("pwd") String pwd,
//            @Query("ticketCode") String ticketCode,
//            @Query("channelid") String channelid
//    );
    @POST(REQUEST_LOGIN)
    Call<getTokenModel> login(
            @Query("userId") String userId,
            @Query("name") String name,
            @Query("portraitUri") String portraitUri
    );

}