package com.ktdcl.fpo.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class FPOAppModel implements Parcelable {
    public String name, fruitsNo, PAN, religion, caste, mainProfession, subProfession, noGents, noLadies, noChildren;
    public String fname, workingInLand, noOfEducated, memOfLocalOrg;
    public String aadha, cropIncome,agWorkIncome, nonAgWorkIncome, dairyIncome, liveStockIncome, sericultureIncome;
    public String phone, govtDevelopmentIncome, cropProcessingIncome, otherIncome;
    public String email, isThereBankAcct, bankName;
    public String village;
    public String taluk;
    public String dist;
    public String qualification;
    public String tanda_resident;
    public String gp;
    public String otherPlace;
    public String dob;
    public String gender;
    public String kottigeArea, kottigeRemarks;
    public String mainIncomeSource, secondIncomeSource, ceo_id;

    public List<LandDetailsModel> landDetailsModelList;
    public List<CropDetailsModel> cropDetailsModelList;
    public List<AgToolsModel> agToolsModelList;
    public List<VetDetailsModel> vetDetailsModelList;
    public List<MarketDetailsModel> marketDetailsModelList;
    public List<AgToolsPurchaseModel> agToolsPurchaseModelList;

    public List<CropInsuranceModel> cropInsuranceModels;

    public String soilTested, soilTestedYear, didSoilTestImplemented;
    public String advanceSeedsUsed;
    public String advancedIrrigation;
    public String pestControlled;
    public String areYouVisitingAgUniveese, ifYesWhom;
    public String ictInstalled;
    public String dryingYard, model, capacity;
    public String aadharPhoto, photo;

    public FPOAppModel() {
    }

    public FPOAppModel(String sericultureIncome, String mainIncome, String secondIncomeSource, String name, String fruitsNo, String PAN, String religion, String caste, String mainProfession, String subProfession, String noGents, String noLadies, String noChildren, String fname, String workingInLand, String noOfEducated, String memOfLocalOrg, String aadha, String cropIncome, String agWorkIncome, String nonAgWorkIncome, String dairyIncome, String liveStockIncome, String phone, String govtDevelopmentIncome, String cropProcessingIncome, String otherIncome, String email, String isThereBankAcct, String bankName, String village, String taluk, String dist, String qualification, String tanda_resident, String gp, String otherPlace, String dob, String gender, String kottigeArea, String kottigeRemarks, List<LandDetailsModel> landDetailsModelList, List<CropDetailsModel> cropDetailsModelList, List<AgToolsModel> agToolsModelList, List<VetDetailsModel> vetDetailsModelList, List<AgToolsPurchaseModel> agToolsPurchaseModelList, List<CropInsuranceModel> cropInsuranceModels, String soilTested, String soilTestedYear, String didSoilTestImplemented, String advanceSeedsUsed, String advancedIrrigation, String pestControlled, String areYouVisitingAgUniveese, String ifYesWhom, String ictInstalled) {
        this.sericultureIncome = sericultureIncome;
        this.mainIncomeSource = mainIncome;
        this.secondIncomeSource = secondIncomeSource;
        this.name = name;
        this.fruitsNo = fruitsNo;
        this.PAN = PAN;
        this.religion = religion;
        this.caste = caste;
        this.mainProfession = mainProfession;
        this.subProfession = subProfession;
        this.noGents = noGents;
        this.noLadies = noLadies;
        this.noChildren = noChildren;
        this.fname = fname;
        this.workingInLand = workingInLand;
        this.noOfEducated = noOfEducated;
        this.memOfLocalOrg = memOfLocalOrg;
        this.aadha = aadha;
        this.cropIncome = cropIncome;
        this.agWorkIncome = agWorkIncome;
        this.nonAgWorkIncome = nonAgWorkIncome;
        this.dairyIncome = dairyIncome;
        this.liveStockIncome = liveStockIncome;
        this.phone = phone;
        this.govtDevelopmentIncome = govtDevelopmentIncome;
        this.cropProcessingIncome = cropProcessingIncome;
        this.otherIncome = otherIncome;
        this.email = email;
        this.isThereBankAcct = isThereBankAcct;
        this.bankName = bankName;
        this.village = village;
        this.taluk = taluk;
        this.dist = dist;
        this.qualification = qualification;
        this.tanda_resident = tanda_resident;
        this.gp = gp;
        this.otherPlace = otherPlace;
        this.dob = dob;
        this.gender = gender;
        this.kottigeArea = kottigeArea;
        this.kottigeRemarks = kottigeRemarks;
        this.landDetailsModelList = landDetailsModelList;
        this.cropDetailsModelList = cropDetailsModelList;
        this.agToolsModelList = agToolsModelList;
        this.vetDetailsModelList = vetDetailsModelList;
        this.agToolsPurchaseModelList = agToolsPurchaseModelList;
        this.cropInsuranceModels = cropInsuranceModels;
        this.soilTested = soilTested;
        this.soilTestedYear = soilTestedYear;
        this.didSoilTestImplemented = didSoilTestImplemented;
        this.advanceSeedsUsed = advanceSeedsUsed;
        this.advancedIrrigation = advancedIrrigation;
        this.pestControlled = pestControlled;
        this.areYouVisitingAgUniveese = areYouVisitingAgUniveese;
        this.ifYesWhom = ifYesWhom;
        this.ictInstalled = ictInstalled;
    }

    protected FPOAppModel(Parcel in) {
        name = in.readString();
        fruitsNo = in.readString();
        PAN = in.readString();
        religion = in.readString();
        caste = in.readString();
        mainProfession = in.readString();
        subProfession = in.readString();
        noGents = in.readString();
        noLadies = in.readString();
        noChildren = in.readString();
        fname = in.readString();
        workingInLand = in.readString();
        noOfEducated = in.readString();
        memOfLocalOrg = in.readString();
        aadha = in.readString();
        cropIncome = in.readString();
        agWorkIncome = in.readString();
        nonAgWorkIncome = in.readString();
        dairyIncome = in.readString();
        liveStockIncome = in.readString();
        phone = in.readString();
        govtDevelopmentIncome = in.readString();
        cropProcessingIncome = in.readString();
        otherIncome = in.readString();
        email = in.readString();
        isThereBankAcct = in.readString();
        bankName = in.readString();
        village = in.readString();
        taluk = in.readString();
        dist = in.readString();
        qualification = in.readString();
        tanda_resident = in.readString();
        gp = in.readString();
        otherPlace = in.readString();
        dob = in.readString();
        gender = in.readString();
        kottigeArea = in.readString();
        kottigeRemarks = in.readString();
        soilTested = in.readString();
        soilTestedYear = in.readString();
        didSoilTestImplemented = in.readString();
        advanceSeedsUsed = in.readString();
        advancedIrrigation = in.readString();
        pestControlled = in.readString();
        areYouVisitingAgUniveese = in.readString();
        ifYesWhom = in.readString();
        ictInstalled = in.readString();
        mainIncomeSource = in.readString();
        secondIncomeSource = in.readString();
        sericultureIncome = in.readString();
        dryingYard = in.readString();
        model = in.readString();
        capacity = in.readString();
        aadharPhoto = in.readString();
        photo = in.readString();
    }

    public static final Creator<FPOAppModel> CREATOR = new Creator<FPOAppModel>() {
        @Override
        public FPOAppModel createFromParcel(Parcel in) {
            return new FPOAppModel(in);
        }

        @Override
        public FPOAppModel[] newArray(int size) {
            return new FPOAppModel[size];
        }
    };

    public String getCeo_id() {
        return ceo_id;
    }

    public void setCeo_id(String ceo_id) {
        this.ceo_id = ceo_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFruitsNo() {
        return fruitsNo;
    }

    public void setFruitsNo(String fruitsNo) {
        this.fruitsNo = fruitsNo;
    }

    public String getPAN() {
        return PAN;
    }

    public void setPAN(String PAN) {
        this.PAN = PAN;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getCaste() {
        return caste;
    }

    public void setCaste(String caste) {
        this.caste = caste;
    }

    public String getMainProfession() {
        return mainProfession;
    }

    public void setMainProfession(String mainProfession) {
        this.mainProfession = mainProfession;
    }

    public String getSubProfession() {
        return subProfession;
    }

    public void setSubProfession(String subProfession) {
        this.subProfession = subProfession;
    }

    public String getNoGents() {
        return noGents;
    }

    public void setNoGents(String noGents) {
        this.noGents = noGents;
    }

    public String getNoLadies() {
        return noLadies;
    }

    public void setNoLadies(String noLadies) {
        this.noLadies = noLadies;
    }

    public String getNoChildren() {
        return noChildren;
    }

    public void setNoChildren(String noChildren) {
        this.noChildren = noChildren;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getWorkingInLand() {
        return workingInLand;
    }

    public void setWorkingInLand(String workingInLand) {
        this.workingInLand = workingInLand;
    }

    public String getNoOfEducated() {
        return noOfEducated;
    }

    public void setNoOfEducated(String noOfEducated) {
        this.noOfEducated = noOfEducated;
    }

    public String getMemOfLocalOrg() {
        return memOfLocalOrg;
    }

    public void setMemOfLocalOrg(String memOfLocalOrg) {
        this.memOfLocalOrg = memOfLocalOrg;
    }

    public String getAadha() {
        return aadha;
    }

    public void setAadha(String aadha) {
        this.aadha = aadha;
    }

    public String getCropIncome() {
        return cropIncome;
    }

    public void setCropIncome(String cropIncome) {
        this.cropIncome = cropIncome;
    }

    public String getAgWorkIncome() {
        return agWorkIncome;
    }

    public void setAgWorkIncome(String agWorkIncome) {
        this.agWorkIncome = agWorkIncome;
    }

    public String getNonAgWorkIncome() {
        return nonAgWorkIncome;
    }

    public void setNonAgWorkIncome(String nonAgWorkIncome) {
        this.nonAgWorkIncome = nonAgWorkIncome;
    }

    public String getDairyIncome() {
        return dairyIncome;
    }

    public void setDairyIncome(String dairyIncome) {
        this.dairyIncome = dairyIncome;
    }

    public String getLiveStockIncome() {
        return liveStockIncome;
    }

    public void setLiveStockIncome(String liveStockIncome) {
        this.liveStockIncome = liveStockIncome;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGovtDevelopmentIncome() {
        return govtDevelopmentIncome;
    }

    public void setGovtDevelopmentIncome(String govtDevelopmentIncome) {
        this.govtDevelopmentIncome = govtDevelopmentIncome;
    }

    public String getCropProcessingIncome() {
        return cropProcessingIncome;
    }

    public void setCropProcessingIncome(String cropProcessingIncome) {
        this.cropProcessingIncome = cropProcessingIncome;
    }

    public String getOtherIncome() {
        return otherIncome;
    }

    public void setOtherIncome(String otherIncome) {
        this.otherIncome = otherIncome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIsThereBankAcct() {
        return isThereBankAcct;
    }

    public void setIsThereBankAcct(String isThereBankAcct) {
        this.isThereBankAcct = isThereBankAcct;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
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

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getTanda_resident() {
        return tanda_resident;
    }

    public void setTanda_resident(String tanda_resident) {
        this.tanda_resident = tanda_resident;
    }

    public String getGp() {
        return gp;
    }

    public void setGp(String gp) {
        this.gp = gp;
    }

    public String getOtherPlace() {
        return otherPlace;
    }

    public void setOtherPlace(String otherPlace) {
        this.otherPlace = otherPlace;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getKottigeArea() {
        return kottigeArea;
    }

    public void setKottigeArea(String kottigeArea) {
        this.kottigeArea = kottigeArea;
    }

    public String getKottigeRemarks() {
        return kottigeRemarks;
    }

    public void setKottigeRemarks(String kottigeRemarks) {
        this.kottigeRemarks = kottigeRemarks;
    }

    public List<LandDetailsModel> getLandDetailsModelList() {
        return landDetailsModelList;
    }

    public void setLandDetailsModelList(List<LandDetailsModel> landDetailsModelList) {
        this.landDetailsModelList = landDetailsModelList;
    }

    public List<CropDetailsModel> getCropDetailsModelList() {
        return cropDetailsModelList;
    }

    public void setCropDetailsModelList(List<CropDetailsModel> cropDetailsModelList) {
        this.cropDetailsModelList = cropDetailsModelList;
    }

    public List<AgToolsModel> getAgToolsModelList() {
        return agToolsModelList;
    }

    public void setAgToolsModelList(List<AgToolsModel> agToolsModelList) {
        this.agToolsModelList = agToolsModelList;
    }

    public List<VetDetailsModel> getVetDetailsModelList() {
        return vetDetailsModelList;
    }

    public void setVetDetailsModelList(List<VetDetailsModel> vetDetailsModelList) {
        this.vetDetailsModelList = vetDetailsModelList;
    }

    public List<AgToolsPurchaseModel> getAgToolsPurchaseModelList() {
        return agToolsPurchaseModelList;
    }

    public void setAgToolsPurchaseModelList(List<AgToolsPurchaseModel> agToolsPurchaseModelList) {
        this.agToolsPurchaseModelList = agToolsPurchaseModelList;
    }

    public List<CropInsuranceModel> getCropInsuranceModels() {
        return cropInsuranceModels;
    }

    public void setCropInsuranceModels(List<CropInsuranceModel> cropInsuranceModels) {
        this.cropInsuranceModels = cropInsuranceModels;
    }

    public String getSoilTested() {
        return soilTested;
    }

    public void setSoilTested(String soilTested) {
        this.soilTested = soilTested;
    }

    public String getSoilTestedYear() {
        return soilTestedYear;
    }

    public void setSoilTestedYear(String soilTestedYear) {
        this.soilTestedYear = soilTestedYear;
    }

    public String getDidSoilTestImplemented() {
        return didSoilTestImplemented;
    }

    public void setDidSoilTestImplemented(String didSoilTestImplemented) {
        this.didSoilTestImplemented = didSoilTestImplemented;
    }

    public String getAdvanceSeedsUsed() {
        return advanceSeedsUsed;
    }

    public void setAdvanceSeedsUsed(String advanceSeedsUsed) {
        this.advanceSeedsUsed = advanceSeedsUsed;
    }

    public String getAdvancedIrrigation() {
        return advancedIrrigation;
    }

    public void setAdvancedIrrigation(String advancedIrrigation) {
        this.advancedIrrigation = advancedIrrigation;
    }

    public String getPestControlled() {
        return pestControlled;
    }

    public void setPestControlled(String pestControlled) {
        this.pestControlled = pestControlled;
    }

    public String getAreYouVisitingAgUniveese() {
        return areYouVisitingAgUniveese;
    }

    public void setAreYouVisitingAgUniveese(String areYouVisitingAgUniveese) {
        this.areYouVisitingAgUniveese = areYouVisitingAgUniveese;
    }

    public String getIfYesWhom() {
        return ifYesWhom;
    }

    public void setIfYesWhom(String ifYesWhom) {
        this.ifYesWhom = ifYesWhom;
    }

    public String getIctInstalled() {
        return ictInstalled;
    }

    public void setIctInstalled(String ictInstalled) {
        this.ictInstalled = ictInstalled;
    }

    public String getMainIncomeSource() {
        return mainIncomeSource;
    }

    public void setMainIncomeSource(String mainIncomeSource) {
        this.mainIncomeSource = mainIncomeSource;
    }

    public String getSecondIncomeSource() {
        return secondIncomeSource;
    }

    public void setSecondIncomeSource(String secondIncomeSource) {
        this.secondIncomeSource = secondIncomeSource;
    }

    public String getSericultureIncome() {
        return sericultureIncome;
    }

    public void setSericultureIncome(String sericultureIncome) {
        this.sericultureIncome = sericultureIncome;
    }

    public List<MarketDetailsModel> getMarketDetailsModelList() {
        return marketDetailsModelList;
    }

    public void setMarketDetailsModelList(List<MarketDetailsModel> marketDetailsModelList) {
        this.marketDetailsModelList = marketDetailsModelList;
    }

    public String getDryingYard() {
        return dryingYard;
    }

    public void setDryingYard(String dryingYard) {
        this.dryingYard = dryingYard;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getAadharPhoto() {
        return aadharPhoto;
    }

    public void setAadharPhoto(String aadharPhoto) {
        this.aadharPhoto = aadharPhoto;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(fruitsNo);
        dest.writeString(PAN);
        dest.writeString(religion);
        dest.writeString(caste);
        dest.writeString(mainProfession);
        dest.writeString(subProfession);
        dest.writeString(noGents);
        dest.writeString(noLadies);
        dest.writeString(noChildren);
        dest.writeString(fname);
        dest.writeString(workingInLand);
        dest.writeString(noOfEducated);
        dest.writeString(memOfLocalOrg);
        dest.writeString(aadha);
        dest.writeString(cropIncome);
        dest.writeString(agWorkIncome);
        dest.writeString(nonAgWorkIncome);
        dest.writeString(dairyIncome);
        dest.writeString(liveStockIncome);
        dest.writeString(phone);
        dest.writeString(govtDevelopmentIncome);
        dest.writeString(cropProcessingIncome);
        dest.writeString(otherIncome);
        dest.writeString(email);
        dest.writeString(isThereBankAcct);
        dest.writeString(bankName);
        dest.writeString(village);
        dest.writeString(taluk);
        dest.writeString(dist);
        dest.writeString(qualification);
        dest.writeString(tanda_resident);
        dest.writeString(gp);
        dest.writeString(otherPlace);
        dest.writeString(dob);
        dest.writeString(gender);
        dest.writeString(kottigeArea);
        dest.writeString(kottigeRemarks);
        dest.writeString(soilTested);
        dest.writeString(soilTestedYear);
        dest.writeString(didSoilTestImplemented);
        dest.writeString(advanceSeedsUsed);
        dest.writeString(advancedIrrigation);
        dest.writeString(pestControlled);
        dest.writeString(areYouVisitingAgUniveese);
        dest.writeString(ifYesWhom);
        dest.writeString(ictInstalled);
        dest.writeString(mainIncomeSource);
        dest.writeString(secondIncomeSource);
        dest.writeString(sericultureIncome);
        dest.writeString(dryingYard);
        dest.writeString(model);
        dest.writeString(capacity);
        dest.writeString(aadharPhoto);
        dest.writeString(photo);
    }
}
