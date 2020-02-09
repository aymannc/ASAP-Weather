package com.naitcherif.asapweather;

import java.util.Objects;

class WeatherItem {
    private int tempMax;
    private int tempMin;
    private int presser;
    private int humidity;
    private String image;
    private String date;

    WeatherItem(int tempMax, int tempMin, int presser, int humidity, String image, String date) {
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.presser = presser;
        this.humidity = humidity;
        this.image = image;
        this.date = date;
    }

    public int getTempMax() {
        return tempMax;
    }

    public void setTempMax(int tempMax) {
        this.tempMax = tempMax;
    }

    public int getTempMin() {
        return tempMin;
    }

    public void setTempMin(int tempMin) {
        this.tempMin = tempMin;
    }

    public int getPresser() {
        return presser;
    }

    public void setPresser(int presser) {
        this.presser = presser;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "WeatherItem{" +
                "tempMax=" + tempMax +
                ", tempMin=" + tempMin +
                ", presser=" + presser +
                ", humidity=" + humidity +
                ", image='" + image + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherItem that = (WeatherItem) o;
        return tempMax == that.tempMax &&
                tempMin == that.tempMin &&
                presser == that.presser &&
                humidity == that.humidity &&
                Objects.equals(image, that.image) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tempMax, tempMin, presser, humidity, image, date);
    }
}
