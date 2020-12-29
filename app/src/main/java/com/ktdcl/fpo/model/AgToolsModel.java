package com.ktdcl.fpo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class AgToolsModel implements Parcelable {
    private String type, units, rate;

    public AgToolsModel() {
    }

    public AgToolsModel(String type, String units, String rate) {
        this.type = type;
        this.units = units;
        this.rate = rate;
    }

    protected AgToolsModel(Parcel in) {
        type = in.readString();
        units = in.readString();
        rate = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(units);
        dest.writeString(rate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AgToolsModel> CREATOR = new Creator<AgToolsModel>() {
        @Override
        public AgToolsModel createFromParcel(Parcel in) {
            return new AgToolsModel(in);
        }

        @Override
        public AgToolsModel[] newArray(int size) {
            return new AgToolsModel[size];
        }
    };

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
