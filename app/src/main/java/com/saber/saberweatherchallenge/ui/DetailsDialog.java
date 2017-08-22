package com.saber.saberweatherchallenge.ui;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.saber.saberweatherchallenge.R;
import com.saber.saberweatherchallenge.vo.City;

public class DetailsDialog extends Dialog {
    private FragmentActivity mContext;
    private City mCity;
    private TextView tvTemp, tvName, tvMaxTemp, tvMinTemp, tvHumidity, tvPressure, tvWeatherDesc, tvWindSpeed, tvWindDegree;

    public DetailsDialog(@NonNull FragmentActivity context, City city) {
        super(context);
        this.mContext = context;
        this.mCity = city;
        setContentView(R.layout.popup_details);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        inisialize();
    }

    private void inisialize() {
        tvName = (TextView) findViewById(R.id.tv_name);
        tvHumidity = (TextView) findViewById(R.id.tv_humidity);
        tvTemp = (TextView) findViewById(R.id.tv_temp);
        tvMaxTemp = (TextView) findViewById(R.id.tv_max_temp);
        tvMinTemp = (TextView) findViewById(R.id.tv_min_temp);
        tvPressure = (TextView) findViewById(R.id.tv_pressure);
        tvWeatherDesc = (TextView) findViewById(R.id.tv_weather_desc);
        tvWindDegree = (TextView) findViewById(R.id.tv_wind_degree);
        tvWindSpeed = (TextView) findViewById(R.id.tv_wind_speed);
        tvName.setText(mCity.getName());
        tvHumidity.setText(mCity.getMain().getHumidity() + "");
        tvTemp.setText(mCity.getMain().getTemp() + "");
        tvMaxTemp.setText(mCity.getMain().getTempMax() + "");
        tvMinTemp.setText(mCity.getMain().getTempMin() + "");
        tvPressure.setText(mCity.getMain().getPressure() + "");
        tvWeatherDesc.setText(mCity.getWeather().get(0).getDescription() + "");
        tvWindDegree.setText(mCity.getWind().getDeg() + "");
        tvWindSpeed.setText(mCity.getWind().getSpeed() + "");
    }

}
