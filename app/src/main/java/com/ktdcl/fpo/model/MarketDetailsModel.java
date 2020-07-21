package com.ktdcl.fpo.model;

public class MarketDetailsModel {
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
