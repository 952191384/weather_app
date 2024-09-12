package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// 定义 WeatherAdapter 类，继承自 RecyclerView.Adapter
public class WeatherAdapter_2 extends RecyclerView.Adapter<WeatherAdapter_2.ViewHolder> {
    // 存储天气数据的列表
    private List<WeatherData_2> weatherDataList_2;
    // 用于访问应用程序资源的上下文
    private Context context;
    // 构造函数，初始化适配器，传入上下文和天气数据列表
    public WeatherAdapter_2(Context context, List<WeatherData_2> weatherDataList_2) {
        this.context = context;
        this.weatherDataList_2 = weatherDataList_2;
    }
    // 创建并返回一个 ViewHolder 实例
    @Override
    //ViewGroup parent 是 RecyclerView 的父视图，这通常是 RecyclerView 自身。
    //int viewType 是视图的类型（如果你的 RecyclerView 有多种视图类型的话）。在这个代码片段中，viewType 没有被使用。
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate(R.layout.item_weather, parent, false) 将 item_weather_2 布局文件膨胀（即转换）为一个视图对象，
        // 并将其附加到 parent（即 RecyclerView）中。
        // false 表示视图不会被立即附加到 parent。
        View view = LayoutInflater.from(context).inflate(R.layout.item_weather_2, parent, false);
        return new ViewHolder(view);
    }
    // 将数据绑定到 ViewHolder 中的视图
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // 获取指定位置的天气数据项
        WeatherData_2 weatherData_2 = weatherDataList_2.get(position);
        // 根据天气数据设置 ViewHolder 中每个视图的内容
        holder.time_24Hour.setText(weatherData_2.getTime_24Hour());
        holder.temp_24Hour.setText(weatherData_2.getTemp_24Hour());
        holder.weatherConditions_24Hour.setImageResource(weatherData_2.getWeatherConditions_24Hour());
    }
    // 返回数据集中的总项目数
    @Override
    public int getItemCount() {
        return weatherDataList_2.size();
    }
    // ViewHolder 类，用于保存每个项的视图引用
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // 声明用于显示天气信息的视图
        public TextView time_24Hour;
        public TextView temp_24Hour;
        public ImageView weatherConditions_24Hour;
        // ViewHolder 构造函数，初始化视图
        public ViewHolder(View itemView) {
            super(itemView);
            time_24Hour = itemView.findViewById(R.id.time_24Hour);
            temp_24Hour = itemView.findViewById(R.id.temp_24Hour);
            weatherConditions_24Hour = itemView.findViewById(R.id.weatherConditions_24Hour);
        }
    }
}
