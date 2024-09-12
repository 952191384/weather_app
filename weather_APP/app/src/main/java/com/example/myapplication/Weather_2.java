package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

import java.util.List;
            //24小时数据
public class Weather_2 {

    @SerializedName("status")
    private String status;
    @SerializedName("api_version")
    private String apiVersion;
    @SerializedName("api_status")
    private String apiStatus;
    @SerializedName("lang")
    private String lang;
    @SerializedName("unit")
    private String unit;
    @SerializedName("tzshift")
    private int tzshift;
    @SerializedName("timezone")
    private String timezone;
    @SerializedName("server_time")
    private int serverTime;
    @SerializedName("location")
    private List<Double> location;
    @SerializedName("result")
    private ResultDTO result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getApiStatus() {
        return apiStatus;
    }

    public void setApiStatus(String apiStatus) {
        this.apiStatus = apiStatus;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getTzshift() {
        return tzshift;
    }

    public void setTzshift(int tzshift) {
        this.tzshift = tzshift;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public int getServerTime() {
        return serverTime;
    }

    public void setServerTime(int serverTime) {
        this.serverTime = serverTime;
    }

    public List<Double> getLocation() {
        return location;
    }

    public void setLocation(List<Double> location) {
        this.location = location;
    }

    public ResultDTO getResult() {
        return result;
    }

    public void setResult(ResultDTO result) {
        this.result = result;
    }

    public static class ResultDTO {
        @SerializedName("hourly")
        private HourlyDTO hourly;
        @SerializedName("primary")
        private int primary;
        @SerializedName("forecast_keypoint")
        private String forecastKeypoint;

        public HourlyDTO getHourly() {
            return hourly;
        }

        public void setHourly(HourlyDTO hourly) {
            this.hourly = hourly;
        }

        public int getPrimary() {
            return primary;
        }

        public void setPrimary(int primary) {
            this.primary = primary;
        }

        public String getForecastKeypoint() {
            return forecastKeypoint;
        }

        public void setForecastKeypoint(String forecastKeypoint) {
            this.forecastKeypoint = forecastKeypoint;
        }

        public static class HourlyDTO {
            @SerializedName("status")
            private String status;
            @SerializedName("description")
            private String description;
            @SerializedName("precipitation")
            private List<PrecipitationDTO> precipitation;
            @SerializedName("temperature")
            private List<TemperatureDTO> temperature;
            @SerializedName("apparent_temperature")
            private List<ApparentTemperatureDTO> apparentTemperature;
            @SerializedName("wind")
            private List<WindDTO> wind;
            @SerializedName("humidity")
            private List<HumidityDTO> humidity;
            @SerializedName("cloudrate")
            private List<CloudrateDTO> cloudrate;
            @SerializedName("skycon")
            private List<SkyconDTO> skycon;
            @SerializedName("pressure")
            private List<PressureDTO> pressure;
            @SerializedName("visibility")
            private List<VisibilityDTO> visibility;
            @SerializedName("dswrf")
            private List<DswrfDTO> dswrf;
            @SerializedName("air_quality")
            private AirQualityDTO airQuality;

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public List<PrecipitationDTO> getPrecipitation() {
                return precipitation;
            }

            public void setPrecipitation(List<PrecipitationDTO> precipitation) {
                this.precipitation = precipitation;
            }

            public List<TemperatureDTO> getTemperature() {
                return temperature;
            }

            public void setTemperature(List<TemperatureDTO> temperature) {
                this.temperature = temperature;
            }

            public List<ApparentTemperatureDTO> getApparentTemperature() {
                return apparentTemperature;
            }

            public void setApparentTemperature(List<ApparentTemperatureDTO> apparentTemperature) {
                this.apparentTemperature = apparentTemperature;
            }

            public List<WindDTO> getWind() {
                return wind;
            }

            public void setWind(List<WindDTO> wind) {
                this.wind = wind;
            }

            public List<HumidityDTO> getHumidity() {
                return humidity;
            }

            public void setHumidity(List<HumidityDTO> humidity) {
                this.humidity = humidity;
            }

            public List<CloudrateDTO> getCloudrate() {
                return cloudrate;
            }

            public void setCloudrate(List<CloudrateDTO> cloudrate) {
                this.cloudrate = cloudrate;
            }

            public List<SkyconDTO> getSkycon() {
                return skycon;
            }

            public void setSkycon(List<SkyconDTO> skycon) {
                this.skycon = skycon;
            }

            public List<PressureDTO> getPressure() {
                return pressure;
            }

            public void setPressure(List<PressureDTO> pressure) {
                this.pressure = pressure;
            }

            public List<VisibilityDTO> getVisibility() {
                return visibility;
            }

            public void setVisibility(List<VisibilityDTO> visibility) {
                this.visibility = visibility;
            }

            public List<DswrfDTO> getDswrf() {
                return dswrf;
            }

            public void setDswrf(List<DswrfDTO> dswrf) {
                this.dswrf = dswrf;
            }

            public AirQualityDTO getAirQuality() {
                return airQuality;
            }

            public void setAirQuality(AirQualityDTO airQuality) {
                this.airQuality = airQuality;
            }

            public static class AirQualityDTO {
                @SerializedName("aqi")
                private List<AqiDTO> aqi;
                @SerializedName("pm25")
                private List<Pm25DTO> pm25;

                public List<AqiDTO> getAqi() {
                    return aqi;
                }

                public void setAqi(List<AqiDTO> aqi) {
                    this.aqi = aqi;
                }

                public List<Pm25DTO> getPm25() {
                    return pm25;
                }

                public void setPm25(List<Pm25DTO> pm25) {
                    this.pm25 = pm25;
                }

                public static class AqiDTO {
                    @SerializedName("datetime")
                    private String datetime;
                    @SerializedName("value")
                    private ValueDTO value;

                    public String getDatetime() {
                        return datetime;
                    }

                    public void setDatetime(String datetime) {
                        this.datetime = datetime;
                    }

                    public ValueDTO getValue() {
                        return value;
                    }

                    public void setValue(ValueDTO value) {
                        this.value = value;
                    }

                    public static class ValueDTO {
                        @SerializedName("chn")
                        private int chn;
                        @SerializedName("usa")
                        private int usa;

                        public int getChn() {
                            return chn;
                        }

                        public void setChn(int chn) {
                            this.chn = chn;
                        }

                        public int getUsa() {
                            return usa;
                        }

                        public void setUsa(int usa) {
                            this.usa = usa;
                        }
                    }
                }

                public static class Pm25DTO {
                    @SerializedName("datetime")
                    private String datetime;
                    @SerializedName("value")
                    private int value;

                    public String getDatetime() {
                        return datetime;
                    }

                    public void setDatetime(String datetime) {
                        this.datetime = datetime;
                    }

                    public int getValue() {
                        return value;
                    }

                    public void setValue(int value) {
                        this.value = value;
                    }
                }
            }

            public static class PrecipitationDTO {
                @SerializedName("datetime")
                private String datetime;
                @SerializedName("value")
                private double value;
                @SerializedName("probability")
                private int probability;

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }

                public double getValue() {
                    return value;
                }

                public void setValue(double value) {
                    this.value = value;
                }

                public int getProbability() {
                    return probability;
                }

                public void setProbability(int probability) {
                    this.probability = probability;
                }
            }

            public static class TemperatureDTO {
                @SerializedName("datetime")
                private String datetime;
                @SerializedName("value")
                private String value;

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }

            public static class ApparentTemperatureDTO {
                @SerializedName("datetime")
                private String datetime;
                @SerializedName("value")
                private double value;

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }

                public double getValue() {
                    return value;
                }

                public void setValue(double value) {
                    this.value = value;
                }
            }

            public static class WindDTO {
                @SerializedName("datetime")
                private String datetime;
                @SerializedName("speed")
                private double speed;
                @SerializedName("direction")
                private double direction;

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }

                public double getSpeed() {
                    return speed;
                }

                public void setSpeed(double speed) {
                    this.speed = speed;
                }

                public double getDirection() {
                    return direction;
                }

                public void setDirection(double direction) {
                    this.direction = direction;
                }
            }

            public static class HumidityDTO {
                @SerializedName("datetime")
                private String datetime;
                @SerializedName("value")
                private double value;

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }

                public double getValue() {
                    return value;
                }

                public void setValue(double value) {
                    this.value = value;
                }
            }

            public static class CloudrateDTO {
                @SerializedName("datetime")
                private String datetime;
                @SerializedName("value")
                private double value;

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }

                public double getValue() {
                    return value;
                }

                public void setValue(double value) {
                    this.value = value;
                }
            }

            public static class SkyconDTO {
                @SerializedName("datetime")
                private String datetime;
                @SerializedName("value")
                private String value;

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }

            public static class PressureDTO {
                @SerializedName("datetime")
                private String datetime;
                @SerializedName("value")
                private double value;

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }

                public double getValue() {
                    return value;
                }

                public void setValue(double value) {
                    this.value = value;
                }
            }

            public static class VisibilityDTO {
                @SerializedName("datetime")
                private String datetime;
                @SerializedName("value")
                private double value;

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }

                public double getValue() {
                    return value;
                }

                public void setValue(double value) {
                    this.value = value;
                }
            }

            public static class DswrfDTO {
                @SerializedName("datetime")
                private String datetime;
                @SerializedName("value")
                private double value;

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }

                public double getValue() {
                    return value;
                }

                public void setValue(double value) {
                    this.value = value;
                }
            }
        }
    }
}
