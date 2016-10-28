package com.projects.ahmedbadr.paymedemo.API;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ahmed Badr on 14/6/2016.
 */
public interface ServiceInterfaces {

    interface LogIn {
        @POST("login")
        @FormUrlEncoded
        Call<ServiceInterfaces> login(
                @Field("email") String email,
                @Field("password") String password,
                @Field("device_model") String model,
                @Field("device_token") String token,
                @Field("device_platform") String devicePlatform
        );
    }

    interface SignUp {
        @POST("register")
        @FormUrlEncoded
        Call<ServiceInterfaces> SignUp(
                @Field("platform") String platform,
                @Field("email") String email,
                @Field("password") String password,
                @Field("fname") String firstName,
                @Field("lname") String lastName,
                @Field("mobile") String mobile,
                @Field("device_token") String token,
                @Field("device_model") String model,
                @Field("device_platform") String devicePlatform
                );
    }

    interface AddPromo {
        @POST("add_promo")
        @FormUrlEncoded
        Call<ServiceInterfaces> AddPromo(
                @Field("token") String token,
                @Field("promococe") String promoCode
        );
    }


}
