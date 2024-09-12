package com.example.myapplication;

// WeatherData.java
public class WeatherData_2 {
    private String time_24Hour;
    private String temp_24Hour;
    private int weatherConditions_24Hour;

    public WeatherData_2(String time_24Hour, String temp_24Hour, int weatherConditions_24Hour) {
        this.time_24Hour = time_24Hour;
        this.temp_24Hour = temp_24Hour;
        this.weatherConditions_24Hour = weatherConditions_24Hour;
    }

    // Getters and setters
    public String getTime_24Hour() { return time_24Hour; }
    public void setTime_24Hour(String time_24Hour) { this.time_24Hour = time_24Hour; }

    public String getTemp_24Hour() { return temp_24Hour; }
    public void setTemp_24Hour(String temp_24Hour) { this.temp_24Hour = temp_24Hour; }

    public int getWeatherConditions_24Hour() { return weatherConditions_24Hour; }
    public void setWeatherConditions_24Hour(int weatherConditions_24Hour) { this.weatherConditions_24Hour = weatherConditions_24Hour; }

}
