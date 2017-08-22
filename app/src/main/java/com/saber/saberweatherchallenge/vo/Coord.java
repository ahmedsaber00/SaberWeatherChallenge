package com.saber.saberweatherchallenge.vo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Coord implements Parcelable {

    public static final Creator<Coord> CREATOR = new Creator<Coord>() {
        @Override
        public Coord createFromParcel(Parcel source) {
            return new Coord(source);
        }

        @Override
        public Coord[] newArray(int size) {
            return new Coord[size];
        }
    };
    @SerializedName("Lat")
    private double Lat;
    @SerializedName("Lon")
    private double Lon;

    public Coord() {
    }

    protected Coord(Parcel in) {
        this.Lat = in.readDouble();
        this.Lon = in.readDouble();
    }

    public double getLat() {
        return Lat;
    }

    public void setLat(double lat) {
        Lat = lat;
    }

    public double getLon() {
        return Lon;
    }

    public void setLon(double lon) {
        Lon = lon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.Lat);
        dest.writeDouble(this.Lon);
    }
}