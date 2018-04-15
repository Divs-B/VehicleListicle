package com.example.divya.vehiclelisticle.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Vehicle implements Serializable {

    @SerializedName("vehicleId")
    private int vehicleId;

    @SerializedName("vrn")
    private String vrn;

    @SerializedName("country")
    private String country;

    @SerializedName("color")
    private String color;

    @SerializedName("type")
    private String type;

    @SerializedName("default")
    private String defaultTxt;

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }


    public int getVehicleId() {
        return vehicleId;
    }

    public void setVersion(String version) {
        this.vrn = version;
    }


    public String getVersion() {
        return vrn;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setDefaultText(String defaultTxt) {
        this.defaultTxt = defaultTxt;
    }

    public String getDefaultText() {
        return defaultTxt;
    }
}

