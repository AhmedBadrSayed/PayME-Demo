package com.projects.ahmedbadr.paymedemo.API;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ahmed Badr on 14/6/2016.
 */
public interface ServiceInterfaces {

    interface LogIn {
        @POST("/login")
        void login(
                @Field("fname") String userName,
                @Field("password") String password
        );
    }

    interface SignUp {
        @POST("/register")
        void login(
                @Field("fname") String firstName,
                @Field("lname") String lastName,
                @Field("email") String email,
                @Field("mobile") String mobile,
                @Field("password") String password
                );
    }

    interface AddPromo {
        @POST("/add_promo")
        void login(
                @Field("token") String token,
                @Field("promococe") String promoCode
        );
    }


}
