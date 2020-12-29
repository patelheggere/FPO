package com.ktdcl.fpo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CropInsuranceModel implements Parcelable {
    private String year, cropName, paidPremium, isCropLost, didYouGetInsure;

    public CropInsuranceModel() {
    }

    protected CropInsuranceModel(Parcel in) {
        year = in.readString();
        cropName = in.readString();
        paidPremium = in.readString();
        isCropLost = in.readString();
        didYouGetInsure = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(year);
        dest.writeString(cropName);
        dest.writeString(paidPremium);
        dest.writeString(isCropLost);
        dest.writeString(didYouGetInsure);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CropInsuranceModel> CREATOR = new Creator<CropInsuranceModel>() {
        @Override
        public CropInsuranceModel createFromParcel(Parcel in) {
            return new CropInsuranceModel(in);
        }

        @Override
        public CropInsuranceModel[] newArray(int size) {
            return new CropInsuranceModel[size];
        }
    };

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
