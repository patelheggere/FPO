package com.ktdcl.fpo.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.textfield.TextInputEditText;
import com.ktdcl.fpo.R;
import com.ktdcl.fpo.model.AgToolsPurchaseModel;
import com.ktdcl.fpo.model.CropInsuranceModel;
import com.ktdcl.fpo.model.FPOAppModel;

import java.util.ArrayList;
import java.util.List;

public class CropInsuranceOtherFragment extends Fragment {
    private static final String TAG = "CropInsuranceOtherFragm";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View mView;

    private TextInputEditText editTextSeedsBaseName, editTextSeedsIfLoanTenure,  editTextseedsROI, editTextseedsQuantity;
    private TextInputEditText editTextPlantBaseName,  editTextPlantIfLoanTenure, editTextplantROI, editTextplantQuantity;

    private TextInputEditText editTextFertilizerBaseName,  editTextFertilizerIfLoanTenure,  editTextFertilizerROI, editTextFertilizerQuantity;
    private TextInputEditText editTextOrganicManureBaseName,  editTextOrganicManureIfLoanTenure, editTextOrganicManureROI, editTextOrganicManureQuantity;

    private TextInputEditText editTextPesticidesBaseName,  editTextPesticidesIfLoanTenure,  editTextPesticidesROI, editTextPesticidesQuantity;
    private TextInputEditText editTextToolsMachinesBaseName,  editTextToolsMachinesIfLoanTenure,  editTextToolsMachinesROI, editTextToolsMachinesQuantity;
    private TextInputEditText editTextBioFertilizerBaseName,  editTextBioFertilizerIfLoanTenure,  editTextBioFertilizerROI, editTextBioFertilizerQuantity;
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

    public CropInsuranceOtherFragment() {
        // Required empty public constructor
    }


    public static CropInsuranceOtherFragment newInstance(String param1, String param2) {
        CropInsuranceOtherFragment fragment = new CropInsuranceOtherFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            fpoAppModel = getArguments().getParcelable("FPOMODEL");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_crop_insurance_other, container, false);
        initViews();
        initData();
        initListeners();
        if(fpoAppModel==null)
        {
            fpoAppModel = new FPOAppModel();
        }
        return mView;
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
                getObjects(editTextSeedsBaseName.getText().toString(), radioGroupSeedsPurchaseMode.getCheckedRadioButtonId(), editTextSeedsIfLoanTenure.getText().toString(), radioGroupSeedsLoanPercentage.getCheckedRadioButtonId(), editTextseedsROI.getText().toString(), editTextseedsQuantity.getText().toString());
                getObjects(editTextPlantBaseName.getText().toString(), radioGroupPlantsPurchaseMode.getCheckedRadioButtonId(), editTextPlantIfLoanTenure.getText().toString(), radioGroupPlantsLoanPercentage.getCheckedRadioButtonId(), editTextplantROI.getText().toString(), editTextplantQuantity.getText().toString());
                getObjects(editTextFertilizerBaseName.getText().toString(), radioGroupFertilizerPurchaseMode.getCheckedRadioButtonId(), editTextFertilizerIfLoanTenure.getText().toString(), radioGroupFertilizerLoanPercentage.getCheckedRadioButtonId(), editTextFertilizerROI.getText().toString(), editTextFertilizerQuantity.getText().toString());
                getObjects(editTextPesticidesBaseName.getText().toString(), radioGroupPestPurchaseMode.getCheckedRadioButtonId(), editTextPesticidesIfLoanTenure.getText().toString(), radioGroupPestLoanPercentage.getCheckedRadioButtonId(), editTextPesticidesROI.getText().toString(), editTextPesticidesQuantity.getText().toString());
                getObjects(editTextBioFertilizerBaseName.getText().toString(), radioGroupBioFertPurchaseMode.getCheckedRadioButtonId(), editTextBioFertilizerIfLoanTenure.getText().toString(), radioGroupBioFertLoanPercentage.getCheckedRadioButtonId(), editTextBioFertilizerROI.getText().toString(), editTextBioFertilizerQuantity.getText().toString());
                getObjects(editTextOrganicManureBaseName.getText().toString(), radioGroupOrgManurePurchaseMode.getCheckedRadioButtonId(), editTextOrganicManureIfLoanTenure.getText().toString(), radioGroupOrgManureLoanPercentage.getCheckedRadioButtonId(), editTextOrganicManureROI.getText().toString(), editTextOrganicManureQuantity.getText().toString());
                getObjects(editTextToolsMachinesBaseName.getText().toString(), radioGroupToolsMachinePurchaseMode.getCheckedRadioButtonId(), editTextToolsMachinesIfLoanTenure.getText().toString(), radioGroupToolsMachineLoanPercentage.getCheckedRadioButtonId(), editTextToolsMachinesROI.getText().toString(), editTextToolsMachinesQuantity.getText().toString());

                fpoAppModel.setAgToolsPurchaseModelList(agToolsPurchaseModelList);

                cropInsureYear = editTextCropInsureYear.getText().toString();
                cropNameInsured = editTextCropName.getText().toString();
                cropParihara = editTextInsuranceReceived.getText().toString();
                premiumPaid = editTextPaidPremium.getText().toString();

                RadioGroup radioGroup = mView.findViewById(R.id.rg_crop_lost);
                RadioButton radioButton = mView.findViewById(radioGroup.getCheckedRadioButtonId());

                CropInsuranceModel cropInsuranceModel = new CropInsuranceModel();
                cropInsuranceModel.setCropName(cropNameInsured);
                cropInsuranceModel.setPaidPremium(premiumPaid);
                cropInsuranceModel.setYear(insuranceYear+"");
                cropInsuranceModel.setDidYouGetInsure(cropParihara);
                cropInsuranceModel.setIsCropLost(radioButton.getText().toString());
                List<CropInsuranceModel> cropInsuranceModelList = new ArrayList<>();
                cropInsuranceModelList.add(cropInsuranceModel);
                fpoAppModel.setCropInsuranceModels(cropInsuranceModelList);

                RadioButton radioButton1 = mView.findViewById(radioGroupSoilTest.getCheckedRadioButtonId());
                fpoAppModel.setSoilTested(radioButton1.getText().toString());

                RadioButton radioButton2 = mView.findViewById(RGAdoptedSoilTest.getCheckedRadioButtonId());
                fpoAppModel.setDidSoilTestImplemented(radioButton2.getText().toString());

                RadioButton radioButton3 = mView.findViewById(RGAdvancedCultivation.getCheckedRadioButtonId());
                fpoAppModel.setAdvanceSeedsUsed(radioButton3.getText().toString());

                RadioButton radioButton4 = mView.findViewById(RGAdvancedIrrigation.getCheckedRadioButtonId());
                fpoAppModel.setAdvancedIrrigation(radioButton4.getText().toString());

                RadioButton radioButton5 = mView.findViewById(RGPestControl.getCheckedRadioButtonId());
                fpoAppModel.setPestControlled(radioButton5.getText().toString());

                RadioButton radioButton6 = mView.findViewById(RGPestControl.getCheckedRadioButtonId());
                fpoAppModel.setPestControlled(radioButton6.getText().toString());

                RadioButton radioButton7 = mView.findViewById(RGICTComm.getCheckedRadioButtonId());
                fpoAppModel.setIctInstalled(radioButton7.getText().toString());

                RadioButton radioButton8 = mView.findViewById(RGCropTech.getCheckedRadioButtonId());
                fpoAppModel.setIctInstalled(radioButton8.getText().toString());
                if(mListener!=null)
                {
                    mListener.onFragmentInteractionCrop(fpoAppModel);
                }
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
    }

    private void initViews() {
        linearLayoutSeedsLoanLyt = mView.findViewById(R.id.seeds_loan_lyt);
        linearLayoutPlantsLoanLyt = mView.findViewById(R.id.plants_loan_lyt);
        linearLayoutFertLoanLyt = mView.findViewById(R.id.ferti_loan_lyt);
        linearLayoutPestLoanLyt = mView.findViewById(R.id.pest_loan_lyt);
        linearLayoutBioPestLoanLyt = mView.findViewById(R.id.bio_pest_lyt);
        linearLayoutOrgManureLoanLyt = mView.findViewById(R.id.org_manu_lyt);
        linearLayoutToolsLoanLyt = mView.findViewById(R.id.tools_loan_lyt);

        yearPicker = mView.findViewById(R.id.year_picker);
        mButtonSave = mView.findViewById(R.id.btn_submit);
        RGSoilTestYear = mView.findViewById(R.id.et_soil_test_year);
        mLinearLayoutSoilTest = mView.findViewById(R.id.linearlayout_soil_test);

        editTextSeedsBaseName = mView.findViewById(R.id.et_root_name);
        editTextSeedsIfLoanTenure = mView.findViewById(R.id.seeds_loan_tenure);
        editTextseedsROI = mView.findViewById(R.id.et_seeds_ri);
        editTextseedsQuantity = mView.findViewById(R.id.seeds_qty);

        editTextPlantBaseName = mView.findViewById(R.id.et_plant_root_name);
        editTextPlantIfLoanTenure = mView.findViewById(R.id.et_plant_loan_tenure);
        editTextplantROI = mView.findViewById(R.id.et_plant_loan_ri);
        editTextplantQuantity = mView.findViewById(R.id.et_plant_qty);

        editTextFertilizerBaseName = mView.findViewById(R.id.et_fertilizer_root_name);
        editTextFertilizerIfLoanTenure = mView.findViewById(R.id.et_fert_loan_tenure);
        editTextFertilizerROI = mView.findViewById(R.id.et_fert_loan_ri);
        editTextFertilizerQuantity = mView.findViewById(R.id.et_fert_qty);

        editTextOrganicManureBaseName = mView.findViewById(R.id.et_org_man_root_name);
        editTextOrganicManureIfLoanTenure = mView.findViewById(R.id.et_org_man_loan_tenure);
        editTextOrganicManureROI = mView.findViewById(R.id.et_org_man_loan_ri);
        editTextOrganicManureQuantity = mView.findViewById(R.id.et_org_man_qty);

        editTextPesticidesBaseName = mView.findViewById(R.id.et_pest_root_name);
        editTextPesticidesIfLoanTenure = mView.findViewById(R.id.et_pest_loan_tenure);
        editTextPesticidesROI = mView.findViewById(R.id.et_pest_loan_ri);
        editTextPesticidesQuantity = mView.findViewById(R.id.et_pest_qty);

        editTextToolsMachinesBaseName = mView.findViewById(R.id.et_tools_machine_root_name);
        editTextToolsMachinesIfLoanTenure = mView.findViewById(R.id.et_tools_machine_loan_tenure);
        editTextToolsMachinesROI = mView.findViewById(R.id.et_tools_machine_loan_ri);
        editTextToolsMachinesQuantity = mView.findViewById(R.id.et_tools_machine_qty);

        editTextBioFertilizerBaseName = mView.findViewById(R.id.et_bio_pest_root_name);
        editTextBioFertilizerIfLoanTenure = mView.findViewById(R.id.et_bio_pest_loan_tenure);
        editTextBioFertilizerROI = mView.findViewById(R.id.et_bio_pest_loan_ri);
        editTextBioFertilizerQuantity = mView.findViewById(R.id.et_bio_pest_qty);

        editTextCropInsureYear = mView.findViewById(R.id.et_year);
        editTextCropName = mView.findViewById(R.id.et_crop_name);
        editTextPaidPremium = mView.findViewById(R.id.et_premium);
        editTextInsuranceReceived = mView.findViewById(R.id.et_parihara);

        radioGroupSeedsPurchaseMode = mView.findViewById(R.id.seeds_purchase_method);
        radioGroupPlantsPurchaseMode = mView.findViewById(R.id.plant_purchase_method);
        radioGroupFertilizerPurchaseMode = mView.findViewById(R.id.fert_purchase_method);
        radioGroupPestPurchaseMode = mView.findViewById(R.id.pest_purchase_method);
        radioGroupOrgManurePurchaseMode = mView.findViewById(R.id.org_man_purchase_method);
        radioGroupToolsMachinePurchaseMode = mView.findViewById(R.id.tools_machine_purchase_method);
        radioGroupBioFertPurchaseMode = mView.findViewById(R.id.bio_pest_purchase_method);

        radioGroupSeedsLoanPercentage = mView.findViewById(R.id.seeds_purc_loan_perc);
        radioGroupPlantsLoanPercentage = mView.findViewById(R.id.plant_purc_loan_perc);
        radioGroupFertilizerLoanPercentage = mView.findViewById(R.id.fert_purc_loan_perc);
        radioGroupOrgManureLoanPercentage = mView.findViewById(R.id.org_man_purc_loan_perc);
        radioGroupPestLoanPercentage = mView.findViewById(R.id.pest_purc_loan_perc);
        radioGroupToolsMachineLoanPercentage = mView.findViewById(R.id.tools_machine_purc_loan_perc);
        radioGroupBioFertLoanPercentage = mView.findViewById(R.id.bio_pest_purc_loan_perc);

        radioGroupCropLost = mView.findViewById(R.id.rg_crop_lost);
        radioGroupSoilTest = mView.findViewById(R.id.rg_soil_test);
        RGAdoptedSoilTest = mView.findViewById(R.id.rg_soil_test_rec);
        RGAdvancedCultivation = mView.findViewById(R.id.rg_improve_reform);
        RGAdvancedIrrigation = mView.findViewById(R.id.rg_improve_irrigation);
        RGPestControl = mView.findViewById(R.id.rg_improve_pest_control);
        RGICTComm = mView.findViewById(R.id.rg_met_ict);
        RGCropTech = mView.findViewById(R.id.rg_impl_samagra_crop);

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
        RadioButton radioButtonLoan = mView.findViewById(radioButtonId);
        RadioButton mode = mView.findViewById(checkedRadioButtonId);
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

    private OnFragmentInteractionListener mListener;
    public interface OnFragmentInteractionListener {
        public void onFragmentInteractionCrop(FPOAppModel uri);
    }
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
}