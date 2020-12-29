package com.ktdcl.fpo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MarketDetailsModel implements Parcelable {
    private String cropName;
    private String obtainedProduct;
    private String retainedProduct, soldProduct, marketDistance, productPrice;
    private String marketName, transportMethod, productRange, rateInfo;

    public MarketDetailsModel() {
    }

    public MarketDetailsModel(String cropName, String obtainedProduct, String retainedProduct, String soldProduct, String marketDistance, String productPrice, String marketName, String transportMethod, String productRange, String rateInfo) {
        this.cropName = cropName;
        this.obtainedProduct = obtainedProduct;
        this.retainedProduct = retainedProduct;
        this.soldProduct = soldProduct;
        this.marketDistance = marketDistance;
        this.productPrice = productPrice;
        this.marketName = marketName;
        this.transportMethod = transportMethod;
        this.productRange = productRange;
        this.rateInfo = rateInfo;
    }

    protected MarketDetailsModel(Parcel in) {
        cropName = in.readString();
        obtainedProduct = in.readString();
        retainedProduct = in.readString();
        soldProduct = in.readString();
        marketDistance = in.readString();
        productPrice = in.readString();
        marketName = in.readString();
        transportMethod = in.readString();
        productRange = in.readString();
        rateInfo = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cropName);
        dest.writeString(obtainedProduct);
        dest.writeString(retainedProduct);
        dest.writeString(soldProduct);
        dest.writeString(marketDistance);
        dest.writeString(productPrice);
        dest.writeString(marketName);
        dest.writeString(transportMethod);
        dest.writeString(productRange);
        dest.writeString(rateInfo);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MarketDetailsModel> CREATOR = new Creator<MarketDetailsModel>() {
        @Override
        public MarketDetailsModel createFromParcel(Parcel in) {
            return new MarketDetailsModel(in);
        }

        @Override
        public MarketDetailsModel[] newArray(int size) {
            return new MarketDetailsModel[size];
        }
    };

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public String getObtainedProduct() {
        return obtainedProduct;
    }

    public void setObtainedProduct(String obtainedProduct) {
        this.obtainedProduct = obtainedProduct;
    }

    public String getRetainedProduct() {
        return retainedProduct;
    }

    public void setRetainedProduct(String retainedProduct) {
        this.retainedProduct = retainedProduct;
    }

    public String getSoldProduct() {
        return soldProduct;
    }

    public void setSoldProduct(String soldProduct) {
        this.soldProduct = soldProduct;
    }

    public String getMarketDistance() {
        return marketDistance;
    }

    public void setMarketDistance(String marketDistance) {
        this.marketDistance = marketDistance;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getTransportMethod() {
        return transportMethod;
    }

    public void setTransportMethod(String transportMethod) {
        this.transportMethod = transportMethod;
    }

    public String getProductRange() {
        return productRange;
    }

    public void setProductRange(String productRange) {
        this.productRange = productRange;
    }

    public String getRateInfo() {
        return rateInfo;
    }

    public void setRateInfo(String rateInfo) {
        this.rateInfo = rateInfo;
    }


}
