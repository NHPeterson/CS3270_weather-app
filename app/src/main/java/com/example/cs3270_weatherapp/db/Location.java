package com.example.cs3270_weatherapp.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Location {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String coords;
    private String forecast_today;
    private String picture_today;
    private String high1;
    private String low1;
    private String precip1;
    private String day2;
    private String high2;
    private String low2;
    private String precip2;
    private String day3;
    private String high3;
    private String low3;
    private String precip3;
    private String day4;
    private String high4;
    private String low4;
    private String precip4;
    private String day5;
    private String high5;
    private String low5;
    private String precip5;
    private String day6;
    private String high6;
    private String low6;
    private String precip6;
    private String day7;
    private String high7;
    private String low7;
    private String precip7;

    public Location(String name, String coords, String forecast_today, String picture_today, String high1, String low1, String precip1,
                    String day2, String high2, String low2, String precip2, String day3, String high3, String low3, String precip3,
                    String day4, String high4, String low4, String precip4, String day5, String high5, String low5, String precip5,
                    String day6, String high6, String low6, String precip6, String day7, String high7, String low7, String precip7) {
        this.name = name;
        this.coords = coords;
        this.forecast_today = forecast_today;
        this.picture_today = picture_today;
        this.high1 = high1;
        this.low1 = low1;
        this.precip1 = precip1;
        this.day2 = day2;
        this.high2 = high2;
        this.low2 = low2;
        this.precip2 = precip2;
        this.day3 = day3;
        this.high3 = high3;
        this.low3 = low3;
        this.precip3 = precip3;
        this.day4 = day4;
        this.high4 = high4;
        this.low4 = low4;
        this.precip4 = precip4;
        this.day5 = day5;
        this.high5 = high5;
        this.low5 = low5;
        this.precip5 = precip5;
        this.day6 = day6;
        this.high6 = high6;
        this.low6 = low6;
        this.precip6 = precip6;
        this.day7 = day7;
        this.high7 = high7;
        this.low7 = low7;
        this.precip7 = precip7;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getForecast_today() {
        return forecast_today;
    }

    public void setForecast_today(String forecast_today) {
        this.forecast_today = forecast_today;
    }

    public String getPicture_today() {
        return picture_today;
    }

    public void setPicture_today(String picture_today) {
        this.picture_today = picture_today;
    }

    public String getHigh1() {
        return high1;
    }

    public void setHigh1(String high1) {
        this.high1 = high1;
    }

    public String getLow1() {
        return low1;
    }

    public void setLow1(String low1) {
        this.low1 = low1;
    }

    public String getPrecip1() {
        return precip1;
    }

    public void setPrecip1(String precip1) {
        this.precip1 = precip1;
    }

    public String getHigh2() {
        return high2;
    }

    public void setHigh2(String high2) {
        this.high2 = high2;
    }

    public String getLow2() {
        return low2;
    }

    public void setLow2(String low2) {
        this.low2 = low2;
    }

    public String getPrecip2() {
        return precip2;
    }

    public void setPrecip2(String precip2) {
        this.precip2 = precip2;
    }

    public String getHigh3() {
        return high3;
    }

    public void setHigh3(String high3) {
        this.high3 = high3;
    }

    public String getLow3() {
        return low3;
    }

    public void setLow3(String low3) {
        this.low3 = low3;
    }

    public String getPrecip3() {
        return precip3;
    }

    public void setPrecip3(String precip3) {
        this.precip3 = precip3;
    }

    public String getHigh4() {
        return high4;
    }

    public void setHigh4(String high4) {
        this.high4 = high4;
    }

    public String getLow4() {
        return low4;
    }

    public void setLow4(String low4) {
        this.low4 = low4;
    }

    public String getPrecip4() {
        return precip4;
    }

    public void setPrecip4(String precip4) {
        this.precip4 = precip4;
    }

    public String getHigh5() {
        return high5;
    }

    public void setHigh5(String high5) {
        this.high5 = high5;
    }

    public String getLow5() {
        return low5;
    }

    public void setLow5(String low5) {
        this.low5 = low5;
    }

    public String getPrecip5() {
        return precip5;
    }

    public void setPrecip5(String precip5) {
        this.precip5 = precip5;
    }

    public String getHigh6() {
        return high6;
    }

    public void setHigh6(String high6) {
        this.high6 = high6;
    }

    public String getLow6() {
        return low6;
    }

    public void setLow6(String low6) {
        this.low6 = low6;
    }

    public String getPrecip6() {
        return precip6;
    }

    public void setPrecip6(String precip6) {
        this.precip6 = precip6;
    }

    public String getHigh7() {
        return high7;
    }

    public void setHigh7(String high7) {
        this.high7 = high7;
    }

    public String getLow7() {
        return low7;
    }

    public void setLow7(String low7) {
        this.low7 = low7;
    }

    public String getPrecip7() {
        return precip7;
    }

    public void setPrecip7(String precip7) {
        this.precip7 = precip7;
    }

    public String getDay2() {
        return day2;
    }

    public void setDay2(String day2) {
        this.day2 = day2;
    }

    public String getDay3() {
        return day3;
    }

    public void setDay3(String day3) {
        this.day3 = day3;
    }

    public String getDay4() {
        return day4;
    }

    public void setDay4(String day4) {
        this.day4 = day4;
    }

    public String getDay5() {
        return day5;
    }

    public void setDay5(String day5) {
        this.day5 = day5;
    }

    public String getDay6() {
        return day6;
    }

    public void setDay6(String day6) {
        this.day6 = day6;
    }

    public String getDay7() {
        return day7;
    }

    public void setDay7(String day7) {
        this.day7 = day7;
    }

    public String getCoords() {
        return coords;
    }

    public void setCoords(String coords) {
        this.coords = coords;
    }
}
