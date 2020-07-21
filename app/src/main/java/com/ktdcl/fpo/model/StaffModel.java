package com.ktdcl.fpo.model;

public class StaffModel {
    private String name;
    private String mobile;
    private String dept;
    private String desgn;
    private String tanda, taluk, dist;
    private String otherPlace;

    public StaffModel() {
    }

    public StaffModel(String otherPlace, String name, String mobile, String dept, String desgn, String tanda, String taluk, String dist) {
        this.name = name;
        this.mobile = mobile;
        this.dept = dept;
        this.desgn = desgn;
        this.tanda = tanda;
        this.taluk = taluk;
        this.dist = dist;
        this.otherPlace = otherPlace;
    }

    public String getOtherPlace() {
        return otherPlace;
    }

    public void setOtherPlace(String otherPlace) {
        this.otherPlace = otherPlace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getDesgn() {
        return desgn;
    }

    public void setDesgn(String desgn) {
        this.desgn = desgn;
    }

    public String getTanda() {
        return tanda;
    }

    public void setTanda(String tanda) {
        this.tanda = tanda;
    }

    public String getTaluk() {
        return taluk;
    }

    public void setTaluk(String taluk) {
        this.taluk = taluk;
    }

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }
}
