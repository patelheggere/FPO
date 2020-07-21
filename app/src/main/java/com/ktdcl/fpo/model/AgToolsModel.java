package com.ktdcl.fpo.model;

public class AgToolsModel {
    private String type, units, rate;

    public AgToolsModel() {
    }

    public AgToolsModel(String type, String units, String rate) {
        this.type = type;
        this.units = units;
        this.rate = rate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
