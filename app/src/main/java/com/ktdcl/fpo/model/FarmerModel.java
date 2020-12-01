package com.ktdcl.fpo.model;

public class FarmerModel {
    private String name, village;

    public FarmerModel() {
    }

    public FarmerModel(String name, String place) {
        this.name = name;
        this.village = place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return village;
    }

    public void setPlace(String place) {
        this.village = place;
    }
}
