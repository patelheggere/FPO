package com.ktdcl.fpo.model;

public class VersionCheck {
    private String versionName;
    private String versionCode;

    public VersionCheck() {
    }

    public VersionCheck(String versionName, String versionCode) {
        this.versionName = versionName;
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }
}
