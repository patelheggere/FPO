package com.ktdcl.fpo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.gson.Gson;
import com.ktdcl.fpo.KtdclApplication;
import com.ktdcl.fpo.R;
import com.ktdcl.fpo.model.AgToolsPurchaseModel;
import com.ktdcl.fpo.model.CropInsuranceModel;
import com.ktdcl.fpo.model.FPOAppModel;

import java.util.ArrayList;
import java.util.List;

public class CropInsuranceActivity extends AppCompatActivity {
    private static final String TAG = "CropInsuranceActivity";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View mView;

    private TextInputEditText editTextSeedsIfLoanTenure,  editTextseedsROI, editTextseedsQuantity;
    private TextInputEditText   editTextPlantIfLoanTenure, editTextplantROI, editTextplantQuantity;

    private Spinner sp_seeds_purchase_base;
    private Spinner editTextPlantBaseName, editTextFertilizerBaseName, editTextOrganicManureBaseName, editTextPesticidesBaseName, editTextToolsMachinesBaseName, editTextBioFertilizerBaseName;
    private TextInputEditText   editTextFertilizerIfLoanTenure,  editTextFertilizerROI, editTextFertilizerQuantity;
    private TextInputEditText   editTextOrganicManureIfLoanTenure, editTextOrganicManureROI, editTextOrganicManureQuantity;

    private TextInputEditText   editTextPesticidesIfLoanTenure,  editTextPesticidesROI, editTextPesticidesQuantity;
    private TextInputEditText   editTextToolsMachinesIfLoanTenure,  editTextToolsMachinesROI, editTextToolsMachinesQuantity;
    private TextInputEditText   editTextBioFertilizerIfLoanTenure,  editTextBioFertilizerROI, editTextBioFertilizerQuantity;
    private TextInputEditText editTextCropInsureYear, editTextCropName, editTextPaidPremium, editTextInsuranceReceived, RGSoilTestYear;

    private RadioGroup radioGroupSeedsPurchaseMode, radioGroupPlantsPurchaseMode, radioGroupFertilizerPurchaseMode, radioGroupOrgManurePurchaseMode, radioGroupPestPurchaseMode, radioGroupToolsMachinePurchaseMode, radioGroupBioFertPurchaseMode;
    private RadioGroup radioGroupSeedsLoanPercentage, radioGroupPlantsLoanPercentage, radioGroupFertilizerLoanPercentage, radioGroupOrgManureLoanPercentage, radioGroupPestLoanPercentage, radioGroupToolsMachineLoanPercentage, radioGroupBioFertLoanPercentage;
    private RadioGroup radioGroupCropLost;
    private RadioGroup radioGroupSoilTest, RGAdoptedSoilTest, RGAdvancedCultivation, RGAdvancedIrrigation, RGPestControl, RGICTComm, RGCropTech;
    private String SeedsBaseName, seedsLoanTenure, seedsROI, seedsQuantity, seedsPurchaseMode, seedsLoanPerc;
    private String plantName, plantLoanTenure, PlantROI, plantQuantity, plantPurchaseMode, plantLoanPerc;
    private String fertilizerName, fertilizerLoanTenure, fertilizerROI, fertilizerQuantity, fertilizerPurchasemode, fertilizerLoanPerc;
    private String OrgManuname, OrgManunLoanTenure, OrgManunROI, OrgManunQuantity, OrgManunPurchaseMode, OrgManunLoanPerc;
    private String pesticideName, pesticideLoanTenure, pesticideROI, pesticideQuantity, pesticidePurchaseMode, pesticideLoanPerc;
    private String ToolsMachinesBaseName, ToolsMachinesLoanTenure, ToolsMachinesROI, ToolsMachinesQuantity, ToolsMachinesPurchaseMode, ToolsMachinesLoanPerc;
    private String BioFertilizerBaseName, BioFertilizerLoanTenure, BioFertilizerROI, BioFertilizerQuantity, BioFertilizerPurchaseMode, BioFertilizerLoanPerc;

    private String cropInsureYear, cropNameInsured, premiumPaid, cropLost, cropParihara;

    private String soilTested, soilTestYear, soilTestAdapted, advanceSeeds, advanceIrrigation, advancePestContl, advanceICT, advanceCropTech;
    private LinearLayout mLinearLayoutSoilTest;
    private Button mButtonSave;
    private List<AgToolsPurchaseModel> agToolsPurchaseModelList;
    private FPOAppModel fpoAppModel;
    private NumberPicker yearPicker;
    private LinearLayout linearLayoutSeedsLoanLyt,linearLayoutPlantsLoanLyt, linearLayoutFertLoanLyt, linearLayoutPestLoanLyt, linearLayoutBioPestLoanLyt,
            linearLayoutOrgManureLoanLyt,linearLayoutToolsLoanLyt;
    private List<String> purchaseBaseNames;

    private Spinner spSeedsBaseName, spPesticidesNames ;

    private List<String> pesticidesList = new ArrayList<>();
    private Button mButtonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_insurance);
        String str = getIntent().getStringExtra("Data");
        fpoAppModel = new Gson().fromJson(str, FPOAppModel.class);
        initViews();
        initData();
        initListeners();

    }

    private void initListeners() {

        radioGroupSeedsPurchaseMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.seeds_loan)
                {
                    linearLayoutSeedsLoanLyt.setVisibility(View.GONE);
                }
                else {
                    linearLayoutSeedsLoanLyt.setVisibility(View.VISIBLE);
                }
            }
        });
        radioGroupPlantsPurchaseMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.plant_loan)
                {
                    linearLayoutPlantsLoanLyt.setVisibility(View.GONE);
                }
                else {
                    linearLayoutPlantsLoanLyt.setVisibility(View.VISIBLE);
                }
            }
        });
        radioGroupFertilizerPurchaseMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.fert_loan)
                {
                    linearLayoutFertLoanLyt.setVisibility(View.GONE);
                }
                else {
                    linearLayoutFertLoanLyt.setVisibility(View.VISIBLE);
                }
            }
        });

        radioGroupPestPurchaseMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.pest_loan)
                {
                    linearLayoutPestLoanLyt.setVisibility(View.GONE);
                }
                else {
                    linearLayoutPestLoanLyt.setVisibility(View.VISIBLE);
                }
            }
        });
        radioGroupBioFertPurchaseMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.bio_pest_loan)
                {
                    linearLayoutBioPestLoanLyt.setVisibility(View.GONE);
                }
                else {
                    linearLayoutBioPestLoanLyt.setVisibility(View.VISIBLE);
                }
            }
        });
        radioGroupOrgManurePurchaseMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.org_man_loan)
                {
                    linearLayoutOrgManureLoanLyt.setVisibility(View.GONE);
                }
                else {
                    linearLayoutOrgManureLoanLyt.setVisibility(View.VISIBLE);
                }
            }
        });

        radioGroupToolsMachinePurchaseMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.tools_machine_loan)
                {
                    linearLayoutToolsLoanLyt.setVisibility(View.GONE);
                }
                else {
                    linearLayoutToolsLoanLyt.setVisibility(View.VISIBLE);
                }
            }
        });

        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agToolsPurchaseModelList = new ArrayList<>();
                getObjects(spSeedsBaseName.getSelectedItem().toString(), radioGroupSeedsPurchaseMode.getCheckedRadioButtonId(), editTextSeedsIfLoanTenure.getText().toString(), radioGroupSeedsLoanPercentage.getCheckedRadioButtonId(), editTextseedsROI.getText().toString(), editTextseedsQuantity.getText().toString());
                getObjects(editTextPlantBaseName.getSelectedItem().toString(), radioGroupPlantsPurchaseMode.getCheckedRadioButtonId(), editTextPlantIfLoanTenure.getText().toString(), radioGroupPlantsLoanPercentage.getCheckedRadioButtonId(), editTextplantROI.getText().toString(), editTextplantQuantity.getText().toString());
                getObjects(editTextFertilizerBaseName.getSelectedItem().toString(), radioGroupFertilizerPurchaseMode.getCheckedRadioButtonId(), editTextFertilizerIfLoanTenure.getText().toString(), radioGroupFertilizerLoanPercentage.getCheckedRadioButtonId(), editTextFertilizerROI.getText().toString(), editTextFertilizerQuantity.getText().toString());
                getObjects(editTextPesticidesBaseName.getSelectedItem().toString(), radioGroupPestPurchaseMode.getCheckedRadioButtonId(), editTextPesticidesIfLoanTenure.getText().toString(), radioGroupPestLoanPercentage.getCheckedRadioButtonId(), editTextPesticidesROI.getText().toString(), editTextPesticidesQuantity.getText().toString());
                getObjects(editTextBioFertilizerBaseName.getSelectedItem().toString(), radioGroupBioFertPurchaseMode.getCheckedRadioButtonId(), editTextBioFertilizerIfLoanTenure.getText().toString(), radioGroupBioFertLoanPercentage.getCheckedRadioButtonId(), editTextBioFertilizerROI.getText().toString(), editTextBioFertilizerQuantity.getText().toString());
                getObjects(editTextOrganicManureBaseName.getSelectedItem().toString(), radioGroupOrgManurePurchaseMode.getCheckedRadioButtonId(), editTextOrganicManureIfLoanTenure.getText().toString(), radioGroupOrgManureLoanPercentage.getCheckedRadioButtonId(), editTextOrganicManureROI.getText().toString(), editTextOrganicManureQuantity.getText().toString());
                getObjects(editTextToolsMachinesBaseName.getSelectedItem().toString(), radioGroupToolsMachinePurchaseMode.getCheckedRadioButtonId(), editTextToolsMachinesIfLoanTenure.getText().toString(), radioGroupToolsMachineLoanPercentage.getCheckedRadioButtonId(), editTextToolsMachinesROI.getText().toString(), editTextToolsMachinesQuantity.getText().toString());

                fpoAppModel.setAgToolsPurchaseModelList(agToolsPurchaseModelList);

                cropInsureYear = editTextCropInsureYear.getText().toString();
                cropNameInsured = editTextCropName.getText().toString();
                cropParihara = editTextInsuranceReceived.getText().toString();
                premiumPaid = editTextPaidPremium.getText().toString();

                RadioGroup radioGroup = findViewById(R.id.rg_crop_lost);
                RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());

                CropInsuranceModel cropInsuranceModel = new CropInsuranceModel();
                cropInsuranceModel.setCropName(cropNameInsured);
                cropInsuranceModel.setPaidPremium(premiumPaid);
                cropInsuranceModel.setYear(insuranceYear+"");
                cropInsuranceModel.setDidYouGetInsure(cropParihara);
                cropInsuranceModel.setIsCropLost(radioButton.getText().toString());
                List<CropInsuranceModel> cropInsuranceModelList = new ArrayList<>();
                cropInsuranceModelList.add(cropInsuranceModel);
                fpoAppModel.setCropInsuranceModels(cropInsuranceModelList);

                RadioButton radioButton1 = findViewById(radioGroupSoilTest.getCheckedRadioButtonId());
                fpoAppModel.setSoilTested(radioButton1.getText().toString());

                RadioButton radioButton2 = findViewById(RGAdoptedSoilTest.getCheckedRadioButtonId());
                fpoAppModel.setDidSoilTestImplemented(radioButton2.getText().toString());

                RadioButton radioButton3 = findViewById(RGAdvancedCultivation.getCheckedRadioButtonId());
                fpoAppModel.setAdvanceSeedsUsed(radioButton3.getText().toString());

                RadioButton radioButton4 = findViewById(RGAdvancedIrrigation.getCheckedRadioButtonId());
                fpoAppModel.setAdvancedIrrigation(radioButton4.getText().toString());

                RadioButton radioButton5 = findViewById(RGPestControl.getCheckedRadioButtonId());
                fpoAppModel.setPestControlled(radioButton5.getText().toString());

                RadioButton radioButton6 = findViewById(RGPestControl.getCheckedRadioButtonId());
                fpoAppModel.setPestControlled(radioButton6.getText().toString());

                RadioButton radioButton7 = findViewById(RGICTComm.getCheckedRadioButtonId());
                fpoAppModel.setIctInstalled(radioButton7.getText().toString());

                RadioButton radioButton8 = findViewById(RGCropTech.getCheckedRadioButtonId());
                fpoAppModel.setIctInstalled(radioButton8.getText().toString());

                Intent intent = new Intent(CropInsuranceActivity.this, MarketingDetailsActivity.class);
                Gson gson = new Gson();
                String str = gson.toJson(fpoAppModel);
                intent.putExtra("Data", str);
                startActivity(intent);

                 //   mListener.onFragmentInteractionCrop(fpoAppModel);
                
            }
        });
    }

    int insuranceYear;
    private void initData() {
        yearPicker.setMaxValue(2020);
        yearPicker.setMinValue(2015);;
        yearPicker.setValue(2019);
        yearPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                insuranceYear = yearPicker.getValue();
                Log.d(TAG, "onValueChange: "+insuranceYear);
            }
        });

        purchaseBaseNames = new ArrayList<>();
        purchaseBaseNames.add("ರೈತ ಸಂಪರ್ಕ ಕೇಂದ್ರ");
        purchaseBaseNames.add("ರೈತರಿಂದ");
        purchaseBaseNames.add("ಖಾಸಗಿ");
        purchaseBaseNames.add("ಸೊಸೈಟಿ");

        pesticidesList.add("ಸಲ್ಫರ್(ಶಿಲೀಂಧ್ರನಾಶಕ)");
        pesticidesList.add("ಎಂಡೋಸಲ್ಫಾನ್(ಕೀಟನಾಶಕ)");
        pesticidesList.add("ಮ್ಯಾಂಕೋಜೆಬ್(ಶಿಲೀಂಧ್ರನಾಶಕ)");
        pesticidesList.add("ಫೊರೆಟ್(ಕೀಟನಾಶಕ)");
        pesticidesList.add("ಮೀಥೈಲ್ ಪ್ಯಾರಾಥಿಯಾನ್(ಕೀಟನಾಶಕ)");
        pesticidesList.add("ಮೊನೊಕ್ರೊಟೊಫಾಸ್(ಕೀಟನಾಶಕ)");
        pesticidesList.add("ಸೈಪರ್ಮೆಥ್ರಿನ್ (ಕೀಟನಾಶಕ)");
        pesticidesList.add("ಐಸೊಪ್ರೊಟುರಾನ್ (ಸಸ್ಯನಾಶಕ)");
        pesticidesList.add("ಕ್ಲೋರ್ಪಿರಿಫೊಸ್(ಕೀಟನಾಶಕ) ");
        pesticidesList.add("ಮಾಲಾಥಿಯಾನ್(ಕೀಟನಾಶಕ)");
        pesticidesList.add("ಕಾರ್ಬೆಂಡಾಜಿಮ್(ಶಿಲೀಂಧ್ರನಾಶಕ)");
        pesticidesList.add("ಬುಟಾಚ್ಲೋರ್ (ಸಸ್ಯನಾಶಕ)");
        pesticidesList.add("ಕ್ವಿನಾಲ್ಫೋಸ್(ಕೀಟನಾಶಕ)");
        pesticidesList.add("ಕಾಪರ್ ಆಕ್ಸಿಕ್ಲೋರೈಡ್ ಡಿಕ್ಲೋರ್ವೋಸ್(ಕೀಟನಾಶಕ)");


        ArrayAdapter cropAdapter = new ArrayAdapter(CropInsuranceActivity.this, R.layout.spinner_item, purchaseBaseNames);
        cropAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spSeedsBaseName.setAdapter(cropAdapter);
        spSeedsBaseName.setSelection(0);

        ArrayAdapter plantAdapter = new ArrayAdapter(CropInsuranceActivity.this, R.layout.spinner_item, purchaseBaseNames);
        plantAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        editTextPlantBaseName.setAdapter(plantAdapter);
        editTextPlantBaseName.setSelection(0);




        ArrayAdapter FertilizerAdapter = new ArrayAdapter(CropInsuranceActivity.this, R.layout.spinner_item, purchaseBaseNames);
        FertilizerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        editTextFertilizerBaseName.setAdapter(FertilizerAdapter);
        editTextFertilizerBaseName.setSelection(0);


        ArrayAdapter organicManureAdapter = new ArrayAdapter(CropInsuranceActivity.this, R.layout.spinner_item, purchaseBaseNames);
        organicManureAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        editTextOrganicManureBaseName.setAdapter(organicManureAdapter);
        editTextOrganicManureBaseName.setSelection(0);


        ArrayAdapter pesticideAdapter = new ArrayAdapter(CropInsuranceActivity.this, R.layout.spinner_item, purchaseBaseNames);
        pesticideAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        editTextPesticidesBaseName.setAdapter(pesticideAdapter);
        editTextPesticidesBaseName.setSelection(0);


        ArrayAdapter ToolsMachineAdapter = new ArrayAdapter(CropInsuranceActivity.this, R.layout.spinner_item, purchaseBaseNames);
        ToolsMachineAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        editTextToolsMachinesBaseName.setAdapter(ToolsMachineAdapter);
        editTextToolsMachinesBaseName.setSelection(0);


        ArrayAdapter BioFertAdapter = new ArrayAdapter(CropInsuranceActivity.this, R.layout.spinner_item, purchaseBaseNames);
        BioFertAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        editTextBioFertilizerBaseName.setAdapter(BioFertAdapter);
        editTextBioFertilizerBaseName.setSelection(0);

    }

    private void initViews() {
        mButtonBack = findViewById(R.id.btn_back);
        mButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        linearLayoutSeedsLoanLyt = findViewById(R.id.seeds_loan_lyt);
        linearLayoutPlantsLoanLyt = findViewById(R.id.plants_loan_lyt);
        linearLayoutFertLoanLyt = findViewById(R.id.ferti_loan_lyt);
        linearLayoutPestLoanLyt = findViewById(R.id.pest_loan_lyt);
        linearLayoutBioPestLoanLyt = findViewById(R.id.bio_pest_lyt);
        linearLayoutOrgManureLoanLyt = findViewById(R.id.org_manu_lyt);
        linearLayoutToolsLoanLyt = findViewById(R.id.tools_loan_lyt);

        yearPicker = findViewById(R.id.year_picker);
        mButtonSave = findViewById(R.id.btn_submit);
        RGSoilTestYear = findViewById(R.id.et_soil_test_year);
        mLinearLayoutSoilTest = findViewById(R.id.linearlayout_soil_test);

        spSeedsBaseName = findViewById(R.id.et_root_name);
        editTextSeedsIfLoanTenure = findViewById(R.id.seeds_loan_tenure);
        editTextseedsROI = findViewById(R.id.et_seeds_ri);
        editTextseedsQuantity = findViewById(R.id.seeds_qty);

        editTextPlantBaseName = findViewById(R.id.et_plant_root_name);
        editTextPlantIfLoanTenure = findViewById(R.id.et_plant_loan_tenure);
        editTextplantROI = findViewById(R.id.et_plant_loan_ri);
        editTextplantQuantity = findViewById(R.id.et_plant_qty);

        editTextFertilizerBaseName = findViewById(R.id.et_fertilizer_root_name);
        editTextFertilizerIfLoanTenure = findViewById(R.id.et_fert_loan_tenure);
        editTextFertilizerROI = findViewById(R.id.et_fert_loan_ri);
        editTextFertilizerQuantity = findViewById(R.id.et_fert_qty);

        editTextOrganicManureBaseName = findViewById(R.id.et_org_man_root_name);
        editTextOrganicManureIfLoanTenure = findViewById(R.id.et_org_man_loan_tenure);
        editTextOrganicManureROI = findViewById(R.id.et_org_man_loan_ri);
        editTextOrganicManureQuantity = findViewById(R.id.et_org_man_qty);

        editTextPesticidesBaseName = findViewById(R.id.et_pest_root_name);
        editTextPesticidesIfLoanTenure = findViewById(R.id.et_pest_loan_tenure);
        editTextPesticidesROI = findViewById(R.id.et_pest_loan_ri);
        editTextPesticidesQuantity = findViewById(R.id.et_pest_qty);

        editTextToolsMachinesBaseName = findViewById(R.id.et_tools_machine_root_name);
        editTextToolsMachinesIfLoanTenure = findViewById(R.id.et_tools_machine_loan_tenure);
        editTextToolsMachinesROI = findViewById(R.id.et_tools_machine_loan_ri);
        editTextToolsMachinesQuantity = findViewById(R.id.et_tools_machine_qty);

        editTextBioFertilizerBaseName = findViewById(R.id.et_bio_pest_root_name);
        editTextBioFertilizerIfLoanTenure = findViewById(R.id.et_bio_pest_loan_tenure);
        editTextBioFertilizerROI = findViewById(R.id.et_bio_pest_loan_ri);
        editTextBioFertilizerQuantity = findViewById(R.id.et_bio_pest_qty);

        editTextCropInsureYear = findViewById(R.id.et_year);
        editTextCropName = findViewById(R.id.et_crop_name);
        editTextPaidPremium = findViewById(R.id.et_premium);
        editTextInsuranceReceived = findViewById(R.id.et_parihara);

        radioGroupSeedsPurchaseMode = findViewById(R.id.seeds_purchase_method);
        radioGroupPlantsPurchaseMode = findViewById(R.id.plant_purchase_method);
        radioGroupFertilizerPurchaseMode = findViewById(R.id.fert_purchase_method);
        radioGroupPestPurchaseMode = findViewById(R.id.pest_purchase_method);
        radioGroupOrgManurePurchaseMode = findViewById(R.id.org_man_purchase_method);
        radioGroupToolsMachinePurchaseMode = findViewById(R.id.tools_machine_purchase_method);
        radioGroupBioFertPurchaseMode = findViewById(R.id.bio_pest_purchase_method);

        radioGroupSeedsLoanPercentage = findViewById(R.id.seeds_purc_loan_perc);
        radioGroupPlantsLoanPercentage = findViewById(R.id.plant_purc_loan_perc);
        radioGroupFertilizerLoanPercentage = findViewById(R.id.fert_purc_loan_perc);
        radioGroupOrgManureLoanPercentage = findViewById(R.id.org_man_purc_loan_perc);
        radioGroupPestLoanPercentage = findViewById(R.id.pest_purc_loan_perc);
        radioGroupToolsMachineLoanPercentage = findViewById(R.id.tools_machine_purc_loan_perc);
        radioGroupBioFertLoanPercentage = findViewById(R.id.bio_pest_purc_loan_perc);

        radioGroupCropLost = findViewById(R.id.rg_crop_lost);
        radioGroupSoilTest = findViewById(R.id.rg_soil_test);
        RGAdoptedSoilTest = findViewById(R.id.rg_soil_test_rec);
        RGAdvancedCultivation = findViewById(R.id.rg_improve_reform);
        RGAdvancedIrrigation = findViewById(R.id.rg_improve_irrigation);
        RGPestControl = findViewById(R.id.rg_improve_pest_control);
        RGICTComm = findViewById(R.id.rg_met_ict);
        RGCropTech = findViewById(R.id.rg_impl_samagra_crop);

        // spPesticidesNames = findViewById(R.id.)

        radioGroupSoilTest.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.soil_test_yes)
                {
                    mLinearLayoutSoilTest.setVisibility(View.VISIBLE);

                }
                else
                {
                    mLinearLayoutSoilTest.setVisibility(View.GONE);
                }
            }
        });


    }

    private void getObjects(String name, int checkedRadioButtonId, String toString, int radioButtonId, String string, String s1){
        AgToolsPurchaseModel agToolsPurchaseModel = new AgToolsPurchaseModel();
        RadioButton radioButtonLoan = findViewById(radioButtonId);
        RadioButton mode = findViewById(checkedRadioButtonId);
        if(name!=null)
        {
            agToolsPurchaseModel.setToolType(name);
        }
        else
        {
            agToolsPurchaseModel.setToolType(null);

        }
        if(toString!=null) {
            agToolsPurchaseModel.setIfLoanDuration(toString);
        }
        else {
            agToolsPurchaseModel.setIfLoanDuration("0");
        }
        if(string!=null) {
            agToolsPurchaseModel.setInterest(string);
        }
        else
        {
            agToolsPurchaseModel.setInterest("0");
        }
        if(s1!=null) {
            agToolsPurchaseModel.setPurchaseQuantity(s1);
        }
        else
        {
            agToolsPurchaseModel.setPurchaseQuantity("0");
        }
        if(radioButtonLoan!=null) {
            agToolsPurchaseModel.setLoanPercentage(radioButtonLoan.getText().toString());
        }
        else
        {
            agToolsPurchaseModel.setLoanPercentage(null);
        }
        if(mode!=null)
        {
            agToolsPurchaseModel.setPurchaseMode(mode.getText().toString());
        }
        else {
            agToolsPurchaseModel.setPurchaseMode(null);
        }
        agToolsPurchaseModelList.add(agToolsPurchaseModel);
    }

}