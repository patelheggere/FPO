package com.ktdcl.fpo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class AgToolsPurchaseModel implements Parcelable {
    private String toolType, purchaseMode, ifLoanDuration,loanPercentage, interest, purchaseQuantity;

    public AgToolsPurchaseModel() {
    }

    protected AgToolsPurchaseModel(Parcel in) {
        toolType = in.readString();
        purchaseMode = in.readString();
        ifLoanDuration = in.readString();
        loanPercentage = in.readString();
        interest = in.readString();
        purchaseQuantity = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(toolType);
        dest.writeString(purchaseMode);
        dest.writeString(ifLoanDuration);
        dest.writeString(loanPercentage);
        dest.writeString(interest);
        dest.writeString(purchaseQuantity);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AgToolsPurchaseModel> CREATOR = new Creator<AgToolsPurchaseModel>() {
        @Override
        public AgToolsPurchaseModel createFromParcel(Parcel in) {
            return new AgToolsPurchaseModel(in);
        }

        @Override
        public AgToolsPurchaseModel[] newArray(int size) {
            return new AgToolsPurchaseModel[size];
        }
    };

    public String getToolType() {
        return toolType;
    }

    public void setToolType(String toolType) {
        this.toolType = toolType;
    }

    public String getPurchaseMode() {
        return purchaseMode;
    }

    public void setPurchaseMode(String purchaseMode) {
        this.purchaseMode = purchaseMode;
    }

    public String getIfLoanDuration() {
        return ifLoanDuration;
    }

    public void setIfLoanDuration(String ifLoanDuration) {
        this.ifLoanDuration = ifLoanDuration;
    }

    public String getLoanPercentage() {
        return loanPercentage;
    }

    public void setLoanPercentage(String loanPercentage) {
        this.loanPercentage = loanPercentage;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(String purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }
}
