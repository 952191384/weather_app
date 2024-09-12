package com.example.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


// MainActivity.java
public class MainActivity extends AppCompatActivity {
    private Button button;
    private TextView cityName;
    private TextView tamp_Now;
    private TextView tips;
    private TextView calender_day0;
    private TextView calender_day1;
    private TextView calender_day2;
    private TextView tamp_min_day0;
    private TextView tamp_min_day1;
    private TextView tamp_min_day2;
    private TextView tamp_max_day0;
    private TextView tamp_max_day1;
    private TextView tamp_max_day2;
    private ImageView image_day1;
    private ImageView image_day2;

    private RecyclerView recyclerView_2;
    private WeatherAdapter_2 adapter_2 ;
    private List<WeatherData_2> weatherDataList_2 = new ArrayList<>();

    private CityUpdateReceiver cityUpdateReceiver;
    private String weatherHourlyData = null;
    private String weatherDailyData = null;
    private String str_cityName;
    private String cityId = null;
    private double latitude = 0;
    private double longitude = 0;
    private Handler handler= new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0){
                weatherHourlyData = (String) msg.obj;
//                Toast.makeText(MainActivity.this, weather_data, Toast.LENGTH_SHORT).show();
                if(weatherHourlyData != null && weatherDailyData != null){
//                    Toast.makeText(MainActivity.this, "00", Toast.LENGTH_SHORT).show();
                    Gson_Init(weatherDailyData,weatherHourlyData);
                }
            } else if(msg.what == 1){
                weatherDailyData = (String) msg.obj;
//                 Toast.makeText(MainActivity.this, weather_data_2, Toast.LENGTH_SHORT).show();
                if(weatherHourlyData != null && weatherDailyData != null){
//                    Toast.makeText(MainActivity.this, "00", Toast.LENGTH_SHORT).show();
                    Gson_Init(weatherDailyData,weatherHourlyData);
                }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置 Activity 的布局文件
        setContentView(R.layout.activity_main);
        init_view();
        initEvent();
        while (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // 请求权限
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    1);
        }

        // 初始化并注册广播接收器
        cityUpdateReceiver = new CityUpdateReceiver();
        IntentFilter filter = new IntentFilter("com.example.myapplication.CITY_UPDATE");
        registerReceiver(cityUpdateReceiver, filter);

        long timestampSeconds = FetchWeatherData();

        if(timestampSeconds==0){


            Intent serviceIntent = new Intent(MainActivity.this, GetCityIdService.class);
            startService(serviceIntent);


        }else {
            // 启动 getWeatherData
            getWeatherData();
        }
    }

    private long FetchWeatherData() {
        // 获取 SharedPreferences 对象
        SharedPreferences sharedPreferences = getSharedPreferences("AppPreferences", Context.MODE_PRIVATE);

        // 从 SharedPreferences 中读取数据
        float latitudeFloat = sharedPreferences.getFloat("latitude", 0.0f); // 默认值为 0.0f
        float longitudeFloat = sharedPreferences.getFloat("longitude", 0.0f); // 默认值为 0.0f
        str_cityName = sharedPreferences.getString("cityName", "Unknown"); // 默认值为 "Unknown"
        long timestampSeconds = sharedPreferences.getLong("timestampSeconds",0);
        // 将 float 转换回 double
        latitude = (double) latitudeFloat;
        longitude = (double) longitudeFloat;
        return timestampSeconds;
    }


    private void initEvent(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent serviceIntent = new Intent(MainActivity.this, GetCityIdService.class);
                startService(serviceIntent);
            }
        });
    }

    public class CityUpdateReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //Service完成后，执行下面的方法
            FetchWeatherData();
            getWeatherData();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 注销广播接收器
        if (cityUpdateReceiver != null) {
            unregisterReceiver(cityUpdateReceiver);
        }
    }

    private void getWeatherData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String context = null;
                try {
                    context = OkHttpUtils.getInstance().doGet("http://api.caiyunapp.com/v2.6/k8NmegKF7xe24da3/"+longitude+","+latitude+"/hourly?hourlysteps=24");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                Message message = new Message();
                message.what = 0;
                message.obj = context;
                handler.sendMessage(message);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String context = null;
                try {
                    context = OkHttpUtils.getInstance().doGet("http://api.caiyunapp.com/v2.6/k8NmegKF7xe24da3/"+longitude+","+latitude+"/daily?dailysteps=3");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                Message message = new Message();
                message.what = 1;
                message.obj = context;
                handler.sendMessage(message);
            }
        }).start();
    }

    private void Gson_Init(String weatherDailyData,String weatherHourlyData) {
//        Gson gson = new Gson();
//        Weather weather = gson.fromJson(weatherDailyData, Weather.class);
        Weather weather = new Weather();
        try {
            Gson gson = new Gson();
            weather = gson.fromJson(weatherDailyData, Weather.class);
        } catch (JsonSyntaxException e) {
            Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
            // 处理异常
        }

        Gson gson_2 = new Gson();
        Weather_2 weather_2 = gson_2.fromJson(weatherHourlyData, Weather_2.class);

        String string1,string2;
        int resId;
        cityName.setText(str_cityName);
//        string1 = weather.getResult().getDaily().getTemperature().get(0).getDate();
//        Toast.makeText(getApplicationContext(), string1, Toast.LENGTH_LONG).show();
        string1 = weather_2.getResult().getHourly().getTemperature().get(0).getValue();
        // 使用 "." 作为分隔符分割字符串
        String[] parts = string1.split("\\.");
        // 取分割后的第一个部分，即小数点前的整数部分
        string2 = parts[0];
        tamp_Now.setText(string2+"°");
        tips.setText(weather_2.getResult().getHourly().getDescription());

        //获取星期
        LocalDate today = LocalDate.now();

        // 获取明天的日期
        LocalDate tomorrow = today.plusDays(1);

        // 获取后天的日期
        LocalDate dayAfterTomorrow = today.plusDays(2);

        // 定义格式化器
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE");

        // 获取今天的星期几
        String todayDayOfWeek = today.format(formatter);
        calender_day0.setText(todayDayOfWeek);
        // 获取明天的星期几
        String tomorrowDayOfWeek = tomorrow.format(formatter);
        calender_day1.setText(tomorrowDayOfWeek);
        // 获取后天的星期几
        String dayAfterTomorrowDayOfWeek = dayAfterTomorrow.format(formatter);
        calender_day2.setText(dayAfterTomorrowDayOfWeek);
        //tamp_min
        string1 = weather.getResult().getDaily().getTemperature().get(0).getMin();
        // 使用 "." 作为分隔符分割字符串
        parts = string1.split("\\.");
        // 取分割后的第一个部分，即小数点前的整数部分
        string2 = parts[0]+"°";
        tamp_min_day0.setText(string2);

        string1 = weather.getResult().getDaily().getTemperature().get(1).getMin();
        // 使用 "." 作为分隔符分割字符串
        parts = string1.split("\\.");
        // 取分割后的第一个部分，即小数点前的整数部分
        string2 = parts[0]+"°";
        tamp_min_day1.setText(string2);

        string1 = weather.getResult().getDaily().getTemperature().get(2).getMin();
        // 使用 "." 作为分隔符分割字符串
        parts = string1.split("\\.");
        // 取分割后的第一个部分，即小数点前的整数部分
        string2 = parts[0]+"°";
        tamp_min_day2.setText(string2);
        //tamp_max
        string1 = weather.getResult().getDaily().getTemperature().get(0).getMax();
        // 使用 "." 作为分隔符分割字符串
        parts = string1.split("\\.");
        // 取分割后的第一个部分，即小数点前的整数部分
        string2 = parts[0]+"°";
        tamp_max_day0.setText(string2);

        string1 = weather.getResult().getDaily().getTemperature().get(1).getMax();
        // 使用 "." 作为分隔符分割字符串
        parts = string1.split("\\.");
        // 取分割后的第一个部分，即小数点前的整数部分
        string2 = parts[0]+"°";
        tamp_max_day1.setText(string2);

        string1 = weather.getResult().getDaily().getTemperature().get(2).getMax();
        // 使用 "." 作为分隔符分割字符串
        parts = string1.split("\\.");
        // 取分割后的第一个部分，即小数点前的整数部分
        string2 = parts[0]+"°";
        tamp_max_day2.setText(string2);
        //image_day
        resId = getWeather_image_2(weather.getResult().getDaily().getPrecipitation().get(1).getAvg(),12);
        image_day1.setImageResource(resId);
        resId = getWeather_image_2(weather.getResult().getDaily().getPrecipitation().get(2).getAvg(),12);
        image_day1.setImageResource(resId);
//-------------------------------------------------------------------------------------------------------------------------------------------

        String time_24Hour,tem_24Hour;

        // 查找布局中的 RecyclerView 视图
        recyclerView_2 = findViewById(R.id.recycler_view_2);
        // 设置 RecyclerView 的布局管理器，这里使用线性布局管理器，水平排列项
        recyclerView_2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));



        // 确保列表不被重复填充
        weatherDataList_2.clear();
        // 初始化天气数据列表
        for (int i=0; i<24; i++) {
            string1 = weather_2.getResult().getHourly().getPrecipitation().get(i).getDatetime();
            string2 = string1.substring(11,13).replaceFirst("^0+", "");
            time_24Hour = string2+"时";
            string1 = weather_2.getResult().getHourly().getTemperature().get(i).getValue();
            // 使用 "." 作为分隔符分割字符串
            parts = string1.split("\\.");
            // 取分割后的第一个部分，即小数点前的整数部分
            string2 = parts[0];
            tem_24Hour = string2+"°";
            string1 = weather_2.getResult().getHourly().getPrecipitation().get(i).getDatetime();
            string2 = string1.substring(11,13);
            resId = getWeather_image_2(weather_2.getResult().getHourly().getPrecipitation().get(i).getValue(),Integer.parseInt(string2));
            weatherDataList_2.add(new WeatherData_2(time_24Hour, tem_24Hour, resId));
        }

        adapter_2 = new WeatherAdapter_2(this,weatherDataList_2);
        // 将适配器设置到 RecyclerView 上
        recyclerView_2.setAdapter(adapter_2);
        // 检查是否已存在分隔线装饰

        boolean hasDivider = false;
        for (int i = 0; i < recyclerView_2.getItemDecorationCount(); i++) {
            RecyclerView.ItemDecoration itemDecoration = recyclerView_2.getItemDecorationAt(i);
            //if判断 itemDecoration 是否是 DividerItemDecoration 的实例。
            if (itemDecoration instanceof DividerItemDecoration) {
                hasDivider = true;
                break;
            }
        }
        // 添加新的分隔线装饰（如果没有找到）
        if (!hasDivider) {
            DividerItemDecoration mDivider_2 = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);
            recyclerView_2.addItemDecoration(mDivider_2);
        }

    }

    private int getWeather_image_2(Double value, int time){
        int resId = 0;
        if(value<0.031){
            if(time>=8 && time<=20) {
                resId = R.drawable.qing;
            }else{
                resId = R.drawable.ye_qing;
            }
        } else if (value<0.25) {
            resId = R.drawable.yin;
        } else if (value<0.35) {
            resId = R.drawable.xiaoyu;
        } else if (value<0.48) {
            resId = R.drawable.zhongyu;
        } else{
            resId = R.drawable.dayu;
        }
        return resId;
    }


    private int getAqi_image(int aqi_data){
        int resId = 0;
        if(aqi_data<50){
            resId=R.drawable.you;
        } else if (aqi_data<100) {
            resId=R.drawable.liang;
        } else if (aqi_data<150) {
            resId=R.drawable.liang;
        } else if (aqi_data<200) {
            resId=R.drawable.cha;
        }else {
            resId=R.drawable.jicha;
        }
        return resId;
    }

    private void init_view() {
        button = findViewById(R.id.button);
        cityName = findViewById(R.id.cityName);
        tamp_Now = findViewById(R.id.tamp_Now);
        tips = findViewById(R.id.tips);
        calender_day0 = findViewById(R.id.calender_day0);
        calender_day1 = findViewById(R.id.calender_day1);
        calender_day2 = findViewById(R.id.calender_day2);
        tamp_min_day0 = findViewById(R.id.tamp_min_day0);
        tamp_min_day1 = findViewById(R.id.tamp_min_day1);
        tamp_min_day2 = findViewById(R.id.tamp_min_day2);
        tamp_max_day0 = findViewById(R.id.tamp_max_day0);
        tamp_max_day1 = findViewById(R.id.tamp_max_day1);
        tamp_max_day2 = findViewById(R.id.tamp_max_day2);
        image_day1 = findViewById(R.id.image_day1);
        image_day2 = findViewById(R.id.image_day2);
    }

}
