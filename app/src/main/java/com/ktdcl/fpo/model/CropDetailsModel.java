package com.ktdcl.fpo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CropDetailsModel implements Parcelable {
    private String crop, area, season, breed, produceCost, produced, subProduct;

    public CropDetailsModel() {
    }

    protected CropDetailsModel(Parcel in) {
        crop = in.readString();
        area = in.readString();
        season = in.readString();
        breed = in.readString();
        produceCost = in.readString();
        produced = in.readString();
        subProduct = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(crop);
        dest.writeString(area);
        dest.writeString(season);
        dest.writeString(breed);
        dest.writeString(produceCost);
        dest.writeString(produced);
        dest.writeString(subProduct);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CropDetailsModel> CREATOR = new Creator<CropDetailsModel>() {
        @Override
        public CropDetailsModel createFromParcel(Parcel in) {
            return new CropDetailsModel(in);
        }

        @Override
        public CropDetailsModel[] newArray(int size) {
            return new CropDetailsModel[size];
        }
    };

    public String getCrop() {
        return crop;
    }

    public void setCrop(String crop) {
        this.crop = crop;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getProduceCost() {
        return produceCost;
    }

    public void setProduceCost(String produceCost) {
        this.produceCost = produceCost;
    }

    public String getProduced() {
        return produced;
    }

    public void setProduced(String produced) {
        this.produced = produced;
    }

    public String getSubProduct() {
        return subProduct;
    }

    public void setSubProduct(String subProduct) {
        this.subProduct = subProduct;
    }
}
