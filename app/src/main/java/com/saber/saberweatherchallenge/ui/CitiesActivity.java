package com.saber.saberweatherchallenge.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.saber.saberweatherchallenge.R;
import com.saber.saberweatherchallenge.adapter.CitiesAdapter;
import com.saber.saberweatherchallenge.adapter.DividerItemDecoration;
import com.saber.saberweatherchallenge.interfaces.CityOperationInterface;
import com.saber.saberweatherchallenge.utils.AppConstants;
import com.saber.saberweatherchallenge.vo.City;

import java.util.ArrayList;

public class CitiesActivity extends AppCompatActivity implements CityOperationInterface {
    private RecyclerView rvCities;
    CitiesAdapter mCitiesAdapter;
    private SearchView mSearchViewAndroidActionBar;
    private String currentQuery = "";
    private ArrayList<City> allCities = new ArrayList<>();
    private ArrayList<City> mCitiesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        if (intent.hasExtra(AppConstants.KEY_CITIES)) {
            allCities = intent.getExtras().getParcelableArrayList(AppConstants.KEY_CITIES);
        } else {

            throw new IllegalArgumentException(this.toString() + " arguments must have CITIES list");

        }
        mCitiesList.addAll(allCities);
        rvCities = (RecyclerView) findViewById(R.id.rv_cities);
        rvCities.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvCities.setLayoutManager(layoutManager);
        rvCities.addItemDecoration(new DividerItemDecoration(CitiesActivity.this));
        mCitiesAdapter = new CitiesAdapter(CitiesActivity.this, mCitiesList, this);
        rvCities.setAdapter(mCitiesAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_users, menu);
        MenuItem searchViewItem = menu.findItem(R.id.action_search);
        mSearchViewAndroidActionBar = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        if (!TextUtils.isEmpty(currentQuery)) {
            MenuItemCompat.expandActionView(searchViewItem);
            mSearchViewAndroidActionBar.setQuery(currentQuery, true);
            mSearchViewAndroidActionBar.clearFocus();
        }

        mSearchViewAndroidActionBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mSearchViewAndroidActionBar.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (TextUtils.isEmpty(newText)) {
                    mCitiesList.clear();
                    mCitiesList.addAll(allCities);
                    mCitiesAdapter.notifyDataSetChanged();
                    return false;
                }
                mCitiesList.clear();
                mCitiesList.addAll(searchFor(allCities, newText));
                mCitiesAdapter.notifyDataSetChanged();
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            default:
                break;
        }

        return false;
    }

    private ArrayList<City> searchFor(ArrayList<City> cities, String searchedText) {
        ArrayList<City> resultCities = new ArrayList<>();
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i).getName().toLowerCase().contains(searchedText.toLowerCase()))
                resultCities.add(cities.get(i));
        }
        return resultCities;
    }

    @Override
    public void onItemClick(int position) {
        DetailsDialog detailsDialog = new DetailsDialog(CitiesActivity.this, mCitiesList.get(position));
        detailsDialog.show();
    }
}
