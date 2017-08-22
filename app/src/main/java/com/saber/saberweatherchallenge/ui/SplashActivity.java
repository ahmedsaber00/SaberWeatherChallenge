package com.saber.saberweatherchallenge.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.saber.saberweatherchallenge.R;
import com.saber.saberweatherchallenge.response.CityResponse;
import com.saber.saberweatherchallenge.rest.ApiClient;
import com.saber.saberweatherchallenge.rest.ApiService;
import com.saber.saberweatherchallenge.utils.ApiConstants;
import com.saber.saberweatherchallenge.utils.AppConstants;
import com.saber.saberweatherchallenge.utils.NetworkManager;
import com.saber.saberweatherchallenge.vo.City;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    private Handler mHandler;
    private Runnable mRunnable;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressBar = (ProgressBar) findViewById(R.id.pb_splash);
        if (NetworkManager.isNetworkAvailable(this)) {
            mHandler = new Handler();

            mRunnable = new Runnable() {

                /*
                 * Showing splash screen with a timer. This will be useful when you
                 * want to show case your app logo / company
                 */
                @Override
                public void run() {

                    getCities();

                }

            };

            mHandler.postDelayed(mRunnable, AppConstants.SPLASH_TIMEOUT);

        }else {
            showInternetDialog();
        }
    }

    private void startHomeScreen(ArrayList<City> cities) {

        Intent intent = new Intent(SplashActivity.this, MapsActivity.class);
        intent.putExtra(AppConstants.KEY_CITIES, cities);
        startActivity(intent);
        finish();

    }

    void getCities() {

        ApiService apiService =
                ApiClient.getClient().create(ApiService.class);

        Call<CityResponse> call = apiService.getCities(ApiConstants.KEY_EGYPT_LAT_LONG,ApiConstants.KEY_API );
        call.enqueue(new Callback<CityResponse>() {

            @Override
            public void onResponse(Call<CityResponse> call, Response<CityResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful())
                    startHomeScreen(response.body().getCities());
                else
                    Toast.makeText(SplashActivity.this, R.string.error, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<CityResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(SplashActivity.this, R.string.error, Toast.LENGTH_SHORT).show();

            }
        });

    }
    private void showInternetDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Connect to wifi or quit")
                .setCancelable(false)
                .setPositiveButton(R.string.connect_to_wifi, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
