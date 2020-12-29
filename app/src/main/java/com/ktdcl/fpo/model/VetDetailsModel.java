package com.ktdcl.fpo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class VetDetailsModel implements Parcelable {
    private String animalName;
    private String animalCount;
    private String animalRate;

    public VetDetailsModel() {

    }

    protected VetDetailsModel(Parcel in) {
        animalName = in.readString();
        animalCount = in.readString();
        animalRate = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(animalName);
        dest.writeString(animalCount);
        dest.writeString(animalRate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<VetDetailsModel> CREATOR = new Creator<VetDetailsModel>() {
        @Override
        public VetDetailsModel createFromParcel(Parcel in) {
            return new VetDetailsModel(in);
        }

        @Override
        public VetDetailsModel[] newArray(int size) {
            return new VetDetailsModel[size];
        }
    };

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public String getAnimalCount() {
        return animalCount;
    }

    public void setAnimalCount(String animalCount) {
        this.animalCount = animalCount;
    }

    public String getAnimalRate() {
        return animalRate;
    }

    public void setAnimalRate(String animalRate) {
        this.animalRate = animalRate;
    }
}
