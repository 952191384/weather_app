package com.example.myapplication;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class Weather {
    private String status;
    private String api_version;
    private String api_status;
    private String lang;
    private String unit;
    private int tzshift;
    private String timezone;
    private long server_time;
    private List<Double> location;
    private Result result;

    // Getters and Setters
    public Result getResult() {
        return result;
    }

    public static class Result {
        private Daily daily;
        private int primary;

        // Getters and Setters
        public Daily getDaily() {
            return daily;
        }
        public static class Daily {
            private String status;
            private List<Astro> astro;
            private List<Precipitation> precipitation_08h_20h;
            private List<Precipitation> precipitation_20h_32h;
            private List<Precipitation> precipitation;
            private List<Temperature> temperature;
            private List<Temperature> temperature_08h_20h;
            private List<Temperature> temperature_20h_32h;
            private List<Wind> wind;
            private List<Wind> wind_08h_20h;
            private List<Wind> wind_20h_32h;
            private List<Humidity> humidity;
            private List<Cloudrate> cloudrate;
            private List<Pressure> pressure;
            private List<Visibility> visibility;
            private List<Dswrf> dswrf;
            private AirQuality air_quality;
            private List<Skycon> skycon;
            private List<Skycon> skycon_08h_20h;
            private List<Skycon> skycon_20h_32h;
            private LifeIndex life_index;

            // Getters and Setters

            public static class Astro {
                private String date;
                private Sunrise sunrise;
                private Sunset sunset;

                // Getters and Setters

                public static class Sunrise {
                    private String time;

                    // Getter and Setter
                }

                public static class Sunset {
                    private String time;

                    // Getter and Setter
                }
            }
            public List<Precipitation> getPrecipitation() {
                return precipitation;
            }
            public static class Precipitation {
                private String date;
                private double max;
                private double min;
                private double avg;
                private int probability;
                public double getAvg() {
                    return avg;
                }
                // Getters and Setters
            }

            public List<Temperature> getTemperature() {
                return temperature;
            }
            public static class Temperature {
                private String date;
                private String max;
                private String min;
                private double avg;
                public String getDate() {
                    return date;
                }
                public String getMax() {
                    return max;
                }
                public String getMin() {
                    return min;
                }
                // Getters and Setters
            }

            public static class Wind {
                private String date;
                private WindDetail max;
                private WindDetail min;
                private WindDetail avg;

                // Getters and Setters

                public static class WindDetail {
                    private double speed;
                    private double direction;

                    // Getters and Setters
                }
            }

            public static class Humidity {
                private String date;
                private double max;
                private double min;
                private double avg;

                // Getters and Setters
            }

            public static class Cloudrate {
                private String date;
                private double max;
                private double min;
                private double avg;

                // Getters and Setters
            }

            public static class Pressure {
                private String date;
                private double max;
                private double min;
                private double avg;

                // Getters and Setters
            }

            public static class Visibility {
                private String date;
                private double max;
                private double min;
                private double avg;

                // Getters and Setters
            }

            public static class Dswrf {
                private String date;
                private double max;
                private double min;
                private double avg;

                // Getters and Setters
            }

            public static class AirQuality {
                private List<Aqi> aqi;
                private List<Pm25> pm25;

                // Getters and Setters

                public static class Aqi {
                    private String date;
                    private QualityIndex max;
                    private QualityIndex avg;
                    private QualityIndex min;

                    // Getters and Setters

                    public static class QualityIndex {
                        private int chn;
                        private int usa;

                        // Getters and Setters
                    }
                }

                public static class Pm25 {
                    private String date;
                    private int max;
                    private int avg;
                    private int min;

                    // Getters and Setters
                }
            }

            public static class Skycon {
                private String date;
                private String value;

                // Getters and Setters
            }

            public static class LifeIndex {
                private List<Index> ultraviolet;
                private List<Index> carWashing;
                private List<Index> dressing;
                private List<Index> comfort;
                private List<Index> coldRisk;

                // Getters and Setters

                public static class Index {
                    private String date;
                    private String index;
                    private String desc;

                    // Getters and Setters
                }
            }
        }
    }
}
