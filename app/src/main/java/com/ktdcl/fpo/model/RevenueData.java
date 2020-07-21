package com.ktdcl.fpo.model;

public class RevenueData {
    private String ac, acDist;
    private String appName, appFatherName, appAge, appAdhar, appProfession, appDist, appTaluk, appVillage, appOthers;
    private String oppName, oppFatherName, oppAge, oppAdhar, oppProfession, oppDist, oppTaluk, oppVillage, oppOthers;

    private String dist, taluk, village, newVillage;
    private String surveyNo, hissaNo, tax, estimatedValue, tenure;
    private String resDimen, resNorth, resSouth, resWest, resEast;
    private String kottigeDimen, kottigeNorth, kottigeSouth, kottigeWest, kottigeEast;
    private String toolsDimen, toolsNorth, toolsSouth, toolsWest, toolsEast;
    private String othersDimen, othersNorth, othersSouth, othersWest, othersEast;
    private String totalDimen;
    private long time;

    public RevenueData() {
    }

    public RevenueData(long time, String ac, String acDist, String appName, String appFatherName, String appAge, String appAdhar, String appProfession, String appDist, String appTaluk, String appVillage, String appOthers, String oppName, String oppFatherName, String oppAge, String oppAdhar, String oppProfession, String oppDist, String oppTaluk, String oppVillage, String oppOthers, String dist, String taluk, String village, String newVillage, String surveyNo, String hissaNo, String tax, String estimatedValue, String tenure, String resDimen, String resNorth, String resSouth, String resWest, String resEast, String kottigeDimen, String kottigeNorth, String kottigeSouth, String kottigeWest, String kottigeEast, String toolsDimen, String toolsNorth, String toolsSouth, String toolsWest, String toolsEast, String othersDimen, String othersNorth, String othersSouth, String othersWest, String othersEast, String totalDimen) {
        this.time = time;
        this.ac = ac;
        this.acDist = acDist;
        this.appName = appName;
        this.appFatherName = appFatherName;
        this.appAge = appAge;
        this.appAdhar = appAdhar;
        this.appProfession = appProfession;
        this.appDist = appDist;
        this.appTaluk = appTaluk;
        this.appVillage = appVillage;
        this.appOthers = appOthers;
        this.oppName = oppName;
        this.oppFatherName = oppFatherName;
        this.oppAge = oppAge;
        this.oppAdhar = oppAdhar;
        this.oppProfession = oppProfession;
        this.oppDist = oppDist;
        this.oppTaluk = oppTaluk;
        this.oppVillage = oppVillage;
        this.oppOthers = oppOthers;
        this.dist = dist;
        this.taluk = taluk;
        this.village = village;
        this.newVillage = newVillage;
        this.surveyNo = surveyNo;
        this.hissaNo = hissaNo;
        this.tax = tax;
        this.estimatedValue = estimatedValue;
        this.tenure = tenure;
        this.resDimen = resDimen;
        this.resNorth = resNorth;
        this.resSouth = resSouth;
        this.resWest = resWest;
        this.resEast = resEast;
        this.kottigeDimen = kottigeDimen;
        this.kottigeNorth = kottigeNorth;
        this.kottigeSouth = kottigeSouth;
        this.kottigeWest = kottigeWest;
        this.kottigeEast = kottigeEast;
        this.toolsDimen = toolsDimen;
        this.toolsNorth = toolsNorth;
        this.toolsSouth = toolsSouth;
        this.toolsWest = toolsWest;
        this.toolsEast = toolsEast;
        this.othersDimen = othersDimen;
        this.othersNorth = othersNorth;
        this.othersSouth = othersSouth;
        this.othersWest = othersWest;
        this.othersEast = othersEast;
        this.totalDimen = totalDimen;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getAc() {
        return ac;
    }

    public void setAc(String ac) {
        this.ac = ac;
    }

    public String getAcDist() {
        return acDist;
    }

    public void setAcDist(String acDist) {
        this.acDist = acDist;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppFatherName() {
        return appFatherName;
    }

    public void setAppFatherName(String appFatherName) {
        this.appFatherName = appFatherName;
    }

    public String getAppAge() {
        return appAge;
    }

    public void setAppAge(String appAge) {
        this.appAge = appAge;
    }

    public String getAppAdhar() {
        return appAdhar;
    }

    public void setAppAdhar(String appAdhar) {
        this.appAdhar = appAdhar;
    }

    public String getAppProfession() {
        return appProfession;
    }

    public void setAppProfession(String appProfession) {
        this.appProfession = appProfession;
    }

    public String getAppDist() {
        return appDist;
    }

    public void setAppDist(String appDist) {
        this.appDist = appDist;
    }

    public String getAppTaluk() {
        return appTaluk;
    }

    public void setAppTaluk(String appTaluk) {
        this.appTaluk = appTaluk;
    }

    public String getAppVillage() {
        return appVillage;
    }

    public void setAppVillage(String appVillage) {
        this.appVillage = appVillage;
    }

    public String getAppOthers() {
        return appOthers;
    }

    public void setAppOthers(String appOthers) {
        this.appOthers = appOthers;
    }

    public String getOppName() {
        return oppName;
    }

    public void setOppName(String oppName) {
        this.oppName = oppName;
    }

    public String getOppFatherName() {
        return oppFatherName;
    }

    public void setOppFatherName(String oppFatherName) {
        this.oppFatherName = oppFatherName;
    }

    public String getOppAge() {
        return oppAge;
    }

    public void setOppAge(String oppAge) {
        this.oppAge = oppAge;
    }

    public String getOppAdhar() {
        return oppAdhar;
    }

    public void setOppAdhar(String oppAdhar) {
        this.oppAdhar = oppAdhar;
    }

    public String getOppProfession() {
        return oppProfession;
    }

    public void setOppProfession(String oppProfession) {
        this.oppProfession = oppProfession;
    }

    public String getOppDist() {
        return oppDist;
    }

    public void setOppDist(String oppDist) {
        this.oppDist = oppDist;
    }

    public String getOppTaluk() {
        return oppTaluk;
    }

    public void setOppTaluk(String oppTaluk) {
        this.oppTaluk = oppTaluk;
    }

    public String getOppVillage() {
        return oppVillage;
    }

    public void setOppVillage(String oppVillage) {
        this.oppVillage = oppVillage;
    }

    public String getOppOthers() {
        return oppOthers;
    }

    public void setOppOthers(String oppOthers) {
        this.oppOthers = oppOthers;
    }

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public String getTaluk() {
        return taluk;
    }

    public void setTaluk(String taluk) {
        this.taluk = taluk;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getNewVillage() {
        return newVillage;
    }

    public void setNewVillage(String newVillage) {
        this.newVillage = newVillage;
    }

    public String getSurveyNo() {
        return surveyNo;
    }

    public void setSurveyNo(String surveyNo) {
        this.surveyNo = surveyNo;
    }

    public String getHissaNo() {
        return hissaNo;
    }

    public void setHissaNo(String hissaNo) {
        this.hissaNo = hissaNo;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getEstimatedValue() {
        return estimatedValue;
    }

    public void setEstimatedValue(String estimatedValue) {
        this.estimatedValue = estimatedValue;
    }

    public String getTenure() {
        return tenure;
    }

    public void setTenure(String tenure) {
        this.tenure = tenure;
    }

    public String getResDimen() {
        return resDimen;
    }

    public void setResDimen(String resDimen) {
        this.resDimen = resDimen;
    }

    public String getResNorth() {
        return resNorth;
    }

    public void setResNorth(String resNorth) {
        this.resNorth = resNorth;
    }

    public String getResSouth() {
        return resSouth;
    }

    public void setResSouth(String resSouth) {
        this.resSouth = resSouth;
    }

    public String getResWest() {
        return resWest;
    }

    public void setResWest(String resWest) {
        this.resWest = resWest;
    }

    public String getResEast() {
        return resEast;
    }

    public void setResEast(String resEast) {
        this.resEast = resEast;
    }

    public String getKottigeDimen() {
        return kottigeDimen;
    }

    public void setKottigeDimen(String kottigeDimen) {
        this.kottigeDimen = kottigeDimen;
    }

    public String getKottigeNorth() {
        return kottigeNorth;
    }

    public void setKottigeNorth(String kottigeNorth) {
        this.kottigeNorth = kottigeNorth;
    }

    public String getKottigeSouth() {
        return kottigeSouth;
    }

    public void setKottigeSouth(String kottigeSouth) {
        this.kottigeSouth = kottigeSouth;
    }

    public String getKottigeWest() {
        return kottigeWest;
    }

    public void setKottigeWest(String kottigeWest) {
        this.kottigeWest = kottigeWest;
    }

    public String getKottigeEast() {
        return kottigeEast;
    }

    public void setKottigeEast(String kottigeEast) {
        this.kottigeEast = kottigeEast;
    }

    public String getToolsDimen() {
        return toolsDimen;
    }

    public void setToolsDimen(String toolsDimen) {
        this.toolsDimen = toolsDimen;
    }

    public String getToolsNorth() {
        return toolsNorth;
    }

    public void setToolsNorth(String toolsNorth) {
        this.toolsNorth = toolsNorth;
    }

    public String getToolsSouth() {
        return toolsSouth;
    }

    public void setToolsSouth(String toolsSouth) {
        this.toolsSouth = toolsSouth;
    }

    public String getToolsWest() {
        return toolsWest;
    }

    public void setToolsWest(String toolsWest) {
        this.toolsWest = toolsWest;
    }

    public String getToolsEast() {
        return toolsEast;
    }

    public void setToolsEast(String toolsEast) {
        this.toolsEast = toolsEast;
    }

    public String getOthersDimen() {
        return othersDimen;
    }

    public void setOthersDimen(String othersDimen) {
        this.othersDimen = othersDimen;
    }

    public String getOthersNorth() {
        return othersNorth;
    }

    public void setOthersNorth(String othersNorth) {
        this.othersNorth = othersNorth;
    }

    public String getOthersSouth() {
        return othersSouth;
    }

    public void setOthersSouth(String othersSouth) {
        this.othersSouth = othersSouth;
    }

    public String getOthersWest() {
        return othersWest;
    }

    public void setOthersWest(String othersWest) {
        this.othersWest = othersWest;
    }

    public String getOthersEast() {
        return othersEast;
    }

    public void setOthersEast(String othersEast) {
        this.othersEast = othersEast;
    }

    public String getTotalDimen() {
        return totalDimen;
    }

    public void setTotalDimen(String totalDimen) {
        this.totalDimen = totalDimen;
    }
}
