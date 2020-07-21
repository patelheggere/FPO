package com.ktdcl.fpo.model;

public class AgToolsPurchaseModel {
    private String toolType, purchaseMode, ifLoanDuration,loanPercentage, interest, purchaseQuantity;

    public AgToolsPurchaseModel() {
    }

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
