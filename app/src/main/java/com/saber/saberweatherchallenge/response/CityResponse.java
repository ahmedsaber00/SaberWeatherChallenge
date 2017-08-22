package com.saber.saberweatherchallenge.response;

import com.google.gson.annotations.SerializedName;
import com.saber.saberweatherchallenge.vo.City;

import java.util.ArrayList;

public class CityResponse {
    @SerializedName("cod")
    private int cod;

    @SerializedName("cnt")
    private int cnt;

    @SerializedName("list")
    private ArrayList<City> cities;

    public int getCod() {
        return cod;
    }

    public int getCnt() {
        return cnt;
    }

    public ArrayList<City> getCities() {
        return cities;
    }
}
