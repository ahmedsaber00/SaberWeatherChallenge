package com.saber.saberweatherchallenge.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.saber.saberweatherchallenge.R;
import com.saber.saberweatherchallenge.interfaces.CityOperationInterface;
import com.saber.saberweatherchallenge.vo.City;

import java.util.ArrayList;


public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<City> mCities;
    private CityOperationInterface mCityOperationInterface;

    public CitiesAdapter(Context c, ArrayList<City> list, CityOperationInterface cityOperationInterface) {
        this.mCities = list;
        this.mContext = c;
        this.mCityOperationInterface = cityOperationInterface;
    }

    public ArrayList<City> getItems() {
        return mCities;
    }

    @Override
    public CitiesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.city_item, parent, false);

        return new CitiesAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CitiesAdapter.MyViewHolder holder, final int position) {

        City city = mCities.get(position);
        holder.tvName.setText(city.getName());
        holder.tvWindSpeed.setText(city.getWind().getSpeed() + "");
        holder.tvWindDegree.setText(city.getWind().getDeg() + "");
        holder.tvTemp.setText(city.getMain().getTemp() + "");
        holder.tvHumidity.setText(city.getMain().getHumidity() + "");
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCityOperationInterface.onItemClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mCities.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvWindSpeed, tvTemp, tvWindDegree, tvHumidity;
        LinearLayout layout;

        MyViewHolder(View v) {
            super(v);
            tvName = (TextView) v.findViewById(R.id.tv_name);
            tvWindDegree = (TextView) v.findViewById(R.id.tv_wind_degree);
            tvWindSpeed = (TextView) v.findViewById(R.id.tv_wind_speed);
            tvHumidity = (TextView) v.findViewById(R.id.tv_humidity);
            tvTemp = (TextView) v.findViewById(R.id.tv_temp);
            layout = (LinearLayout) v.findViewById(R.id.news_layout);
        }
    }
}
