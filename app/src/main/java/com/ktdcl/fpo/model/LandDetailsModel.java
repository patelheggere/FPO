package com.ktdcl.fpo.model;

public class LandDetailsModel {
    private String landType;
    private String surveyNumber;
    private String land;
    private String landValue;
    private String crop;
    private String village;
    private String taluk, district;

    public LandDetailsModel() {
    }

    public LandDetailsModel(String landType, String surveyNumber, String land, String landValue, String crop, String village, String taluk, String district) {
        this.landType = landType;
        this.surveyNumber = surveyNumber;
        this.land = land;
        this.landValue = landValue;
        this.crop = crop;
        this.village = village;
        this.taluk = taluk;
        this.district = district;
    }

    public String getLandType() {
        return landType;
    }

    public void setLandType(String landType) {
        this.landType = landType;
    }

    public String getSurveyNumber() {
        return surveyNumber;
    }

    public void setSurveyNumber(String surveyNumber) {
        this.surveyNumber = surveyNumber;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getLandValue() {
        return landValue;
    }

    public void setLandValue(String landValue) {
        this.landValue = landValue;
    }

    public String getCrop() {
        return crop;
    }

    public void setCrop(String crop) {
        this.crop = crop;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getTaluk() {
        return taluk;
    }

    public void setTaluk(String taluk) {
        this.taluk = taluk;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
