package com.projects.ahmedbadr.paymedemo.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ahmed Badr on 14/6/2016.
 */
public class ServiceBuilder {

    public Retrofit retrofit;

    public ServiceBuilder(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://demo.paymeapp.co/api/v2")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public ServiceInterfaces.LogIn User(){
        return retrofit.create(ServiceInterfaces.LogIn.class);
    }

    public ServiceInterfaces.SignUp NewUser(){
        return retrofit.create(ServiceInterfaces.SignUp.class);
    }

    public ServiceInterfaces.AddPromo NewPromo(){
        return retrofit.create(ServiceInterfaces.AddPromo.class);
    }

}
