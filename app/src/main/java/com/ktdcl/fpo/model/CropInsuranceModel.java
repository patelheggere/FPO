package com.ktdcl.fpo.model;

public class CropInsuranceModel {
    private String year, cropName, paidPremium, isCropLost, didYouGetInsure;

    public CropInsuranceModel() {
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public String getPaidPremium() {
        return paidPremium;
    }

    public void setPaidPremium(String paidPremium) {
        this.paidPremium = paidPremium;
    }

    public String getIsCropLost() {
        return isCropLost;
    }

    public void setIsCropLost(String isCropLost) {
        this.isCropLost = isCropLost;
    }

    public String getDidYouGetInsure() {
        return didYouGetInsure;
    }

    public void setDidYouGetInsure(String didYouGetInsure) {
        this.didYouGetInsure = didYouGetInsure;
    }
}
