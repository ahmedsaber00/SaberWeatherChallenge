package com.saber.saberweatherchallenge.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.saber.saberweatherchallenge.utils.ApiConstants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl(ApiConstants.BASE_URL).build();
        }
        return retrofit;
    }
}
