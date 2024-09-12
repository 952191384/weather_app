package com.example.myapplication;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import java.time.Instant;


public class GetCityIdService extends Service {

    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private LocationManager locationManager;

    @Override
    public void onCreate() {
        super.onCreate();

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        // 请求位置更新
        requestLocationUpdates();
    }

    private void requestLocationUpdates() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // 如果没有权限，退出服务
            Toast.makeText(this, "Permissions not allowed", Toast.LENGTH_SHORT).show();
            stopSelf();
            return;
        }

        boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (isNetworkEnabled) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        }
        else {
            Toast.makeText(this, "GPS没打开！", Toast.LENGTH_SHORT).show();
            stopSelf();
        }
    }

    // LocationListener 用于接收位置更新
    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            long timestampSeconds = Instant.now().getEpochSecond();
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            String cityName = getCityIdFromCoordinates(latitude, longitude);

            // 获取 SharedPreferences 实例，"AppPreferences" 是文件名，MODE_PRIVATE 表示私有模式
            SharedPreferences sharedPreferences = getSharedPreferences("AppPreferences", MODE_PRIVATE);
            // 创建 SharedPreferences.Editor 对象用于编辑数据
            SharedPreferences.Editor editor = sharedPreferences.edit();
            // 将 latitude、longitude 和 cityId 存储到 SharedPreferences 中
            editor.putLong("timestampSeconds",timestampSeconds);
            editor.putString("cityName", cityName); // 存储 cityId 字符串
            editor.putFloat("latitude", (float) latitude); // 使用 float 存储 double 类型的 latitude
            editor.putFloat("longitude", (float) longitude); // 使用 float 存储 double 类型的 longitude
            // 提交更改到 SharedPreferences
            editor.apply(); // 或者 editor.commit();

            sendBroadcastToMainActivity();
            Toast.makeText(GetCityIdService.this, "刷新成功！", Toast.LENGTH_SHORT).show();
            stopSelf();
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {}
        @Override
        public void onProviderEnabled(String provider) {}
        @Override
        public void onProviderDisabled(String provider) {}
    };

    private void sendBroadcastToMainActivity() {
        Intent intent = new Intent("com.example.myapplication.CITY_UPDATE");
        sendBroadcast(intent);
    }

    // 根据经纬度从 citylist.txt 中获取 cityId
    private String getCityIdFromCoordinates(double latitude, double longitude) {
        try {
            InputStreamReader reader = new InputStreamReader(getAssets().open("citylist.txt"));
            Type listType = new TypeToken<List<City>>() {}.getType();
            List<City> cityList = new Gson().fromJson(reader, listType);
            reader.close();

            double[] distance = new double[cityList.size()];
            int i = 0;
            for (City city : cityList) {
                double cityLat = Double.parseDouble(city.latitude);
                double cityLon = Double.parseDouble(city.longitude);
                double dx = latitude - cityLat;
                double dy = longitude - cityLon;
                distance[i] = Math.sqrt(dx * dx + dy * dy);
                i++;
            }

            double distanceMin = 10;
            int position = 0;
            for (i = 0; i < cityList.size(); i++) {
                if (distanceMin > distance[i]) {
                    distanceMin = distance[i];
                    position = i;
                }
            }
            return cityList.get(position).city+"市"+cityList.get(position).district;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null; // 不支持绑定
    }

    // 内部类表示城市数据
    private static class City {
        String cityId;
        String province;
        String city;
        String district;
        String latitude;
        String longitude;
    }
}
