package com.ktdcl.fpo.model;

public class LetterModel {
    private String name;
    private String mobile;
    private String tanda, taluk, dist, url;
    private int mediaType;

    public LetterModel() {
    }

    public LetterModel(String name, String mobile, String tanda, String taluk, String dist, String url, int mediaType) {
        this.name = name;
        this.mobile = mobile;
        this.tanda = tanda;
        this.taluk = taluk;
        this.dist = dist;
        this.url = url;
        this.mediaType = mediaType;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getMediaType() {
        return mediaType;
    }

    public void setMediaType(int mediaType) {
        this.mediaType = mediaType;
    }
}
