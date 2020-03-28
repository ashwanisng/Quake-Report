package com.example.android.quakereport;

public class EarthQuake {
    private double mMagnitude;
    private String mLocation;
    private String mUrl;
    private long mTime;

    public EarthQuake(double magnitude, String location, long time, String url){
        mMagnitude = magnitude;
        mLocation = location;
        mUrl = url;
        mTime = time;
    }

    public double getmMagnitude() {
        return mMagnitude;
    }

    public String getmLocation() {
        return mLocation;
    }

    public long getmTime() {
        return mTime;
    }

    public String getmUrl() {
        return mUrl;
    }
}
