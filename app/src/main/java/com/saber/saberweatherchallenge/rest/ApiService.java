package com.saber.saberweatherchallenge.rest;

import com.saber.saberweatherchallenge.response.CityResponse;
import com.saber.saberweatherchallenge.utils.ApiConstants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET(ApiConstants.CITY_URL)
    Call<CityResponse> getCities(@Query(ApiConstants.KEY_BBOX) String bbox, @Query(ApiConstants.KEY_APPID) String appid);

}
