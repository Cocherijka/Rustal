package com.example.stas.rustal;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyService extends Service
{
    private LocationManager mLocationManager = null;
    private static final int LOCATION_INTERVAL = 1000;
    private static final float LOCATION_DISTANCE = 10f;
    public static Location mLastLocation;
    public MultipartBody.Part latitudePart, longitudePart;
    public String token;
    RestApi api;


    LocationListener[] mLocationListeners = new LocationListener[] {
            new LocationListener(LocationManager.GPS_PROVIDER),
            new LocationListener(LocationManager.NETWORK_PROVIDER)
    };
    @Override
    public IBinder onBind(Intent arg0)
    {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }
    @Override
    public void onCreate()
    {
        String baseURL = "http://rustralcom.nichost.ru/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(RestApi.class);


        initializeLocationManager();
        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[1]);
        } catch (java.lang.SecurityException ignored) {
        } catch (IllegalArgumentException ignored) {
        }
        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[0]);
        } catch (java.lang.SecurityException ignored) {
        } catch (IllegalArgumentException ignored) {
        }
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if (mLocationManager != null) {
            for (LocationListener mLocationListener : mLocationListeners) {
                try {
                    mLocationManager.removeUpdates(mLocationListener);
                } catch (Exception ignored) {
                }
            }
        }
    }

    private void initializeLocationManager() {
        if (mLocationManager == null) {
            mLocationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        }
    }

    private class LocationListener implements android.location.LocationListener{
         LocationListener(String provider)
        {
            mLastLocation = new Location(provider);
        }
        @Override
        public void onLocationChanged(Location location)
        {
            SharedPreferences sharedPref = getApplication().getSharedPreferences("tokenSave", Context.MODE_PRIVATE);
            token = sharedPref.getString("token", "NIHUA NET");

            mLastLocation.set(location);

            System.out.println(location.getLatitude() + "    " + location.getLongitude());
            latitudePart = MultipartBody.Part.createFormData("latitude", String.valueOf(location.getLatitude()));
            longitudePart = MultipartBody.Part.createFormData("longitude", String.valueOf(location.getLongitude()));

             Call<ResponseBody> call =   api.sendLocation(token, latitudePart, longitudePart);
             call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                        System.out.println(response.toString());
                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {

                    }
                });
        }

        @Override
        public void onProviderDisabled(String provider) {
        }
        @Override
        public void onProviderEnabled(String provider) {
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    }





}
