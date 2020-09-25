package com.ktdcl.fpo.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.ktdcl.fpo.KtdclApplication;
import com.ktdcl.fpo.R;
import com.ktdcl.fpo.model.AgToolsModel;
import com.ktdcl.fpo.model.BankNameModel;
import com.ktdcl.fpo.model.CropDetailsModel;
import com.ktdcl.fpo.model.FPOAppModel;
import com.ktdcl.fpo.model.VetDetailsModel;
import com.ktdcl.fpo.network.ApiInterface;
import com.ktdcl.fpo.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class CropVetToolsFragment extends Fragment {
    private static final String TAG = "CropVetToolsFragment";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button mButtonNext;
    private View mView;
    private Spinner spNegilu, spBullCart, spKoorige, spNatiMac, spPumpset, spTiller, spTractor, spSprayer, spHygrometer, spHumidityFire, spDrip,
            spHarvesting, spWeeder, spOkkane, spGroundNut, spMaize, spGrassCutter, spJCB, spThermometer, spFogger, spOthers,
            spOx, spBuffalo, spcow, spHfCow, spSheep, spGoat, spPoultry, spOthersVet, spPig, spDustSprayer, spBankList;

    private TextInputEditText editTextNegilu, editTextBullCart, editTextKoorige, editTextNatiMachine, editTextPumpset, editTextTiller, editTextTrctor, editTextSprayer,
    editTextHygrometer, editTextHumidityFire, editTextDrip, editTextHarvest, editTextWeeder, editTextOkkane, editTextGroudnut, editTextMaize, editTextGrassCutter,
    editTextJCB, editTextThermometer, editTextFogger, editTextOthersMachine,editTextSheepIncome;

    private TextInputEditText editTextOx, editTextBuffalo, editTextCow, editTextHF, editTextSheep, editTextGoat, editTextPoultry, editTextOtherVet, editTextPig, editTextDustSprayer, editTextBankList;

    private List<String> numbers;
    private String cropIncome, sericultureIncome, agLabourIncome, nonAgLabourIncome, dairyIncome, sheepIncome, cropProcessingIncome, otherIncome, bankAcctName;
    private RadioGroup mRadioGroupBank;
    private TextInputEditText editTextCrop, editTextSericulture, editTextAgLabour, editTextNonAgLabour,editTextDairy, editTextCropProcessing,
    editTextOtherIncome, editTextGovtWelfare;
    private String negilu, bullcart, koorige, matimachine, pumpset, tiller, tractor, sprayer,
            dustsprayer, hygrometer, mumidtyfire, drip, harvesting, weeder, okkane,
    groundnut, maize, grasscutter, jcb, thermometer, fogger, otherMachines;

    private String ox, buffalo, cow, hfcow, sheep, goat, poultry, otherVet, pig, bankName;

    private  View[] views = new View[15];

    public CropVetToolsFragment() {
        // Required empty public constructor
    }

    public static CropVetToolsFragment newInstance(String param1, String param2) {
        CropVetToolsFragment fragment = new CropVetToolsFragment();
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

    private FPOAppModel fpoAppModel;
    private List<AgToolsModel> agToolsModelList;
    private List<VetDetailsModel> vetDetailsModelList;
    private Button addCrop, removeCrop;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.fragment_crop_vet_tools, container, false);
        initViews();
        initData();
        initListeners();
        setUpNetwork();
        getBankList();
        return mView;
    }

    private void initListeners() {
        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null)
                {

                    List<CropDetailsModel> cropDetailsModelList = new ArrayList<>();
                    for(int i=0;i<count; i++)
                    {
                       // TextInputEditText cropName = mView.findViewWithTag(i+14);
                        Spinner crop = mView.findViewWithTag(i+14);
                        TextInputEditText area = mView.findViewWithTag(i+15);
                        TextInputEditText et_crop_variety = mView.findViewWithTag(i+16);
                        TextInputEditText et_manufacture_exp = mView.findViewWithTag(i+17);
                        Spinner et_crop_yield = mView.findViewWithTag(i+18);
                        Spinner et_crop_sub_product = mView.findViewWithTag(i+19);
                        RadioGroup season = mView.findViewWithTag(i+11);

                        CropDetailsModel cropDetailsModel = new CropDetailsModel();
                        if(crop.getSelectedItem()!=null)
                            cropDetailsModel.setCrop(crop.getSelectedItem().toString());
                        else
                            cropDetailsModel.setCrop(null);

                        if(area.getText()!=null)
                            cropDetailsModel.setArea(area.getText().toString());
                        else
                            cropDetailsModel.setArea(null);

                        if(et_crop_variety.getText()!=null)
                            cropDetailsModel.setBreed(et_crop_variety.getText().toString());
                        else
                            cropDetailsModel.setBreed(null);

                        if(et_manufacture_exp.getText()!=null)
                            cropDetailsModel.setProduceCost(et_manufacture_exp.getText().toString());
                        else
                            cropDetailsModel.setProduceCost(null);

                        if(et_crop_yield.getSelectedItem()!=null)
                            cropDetailsModel.setProduced(et_crop_yield.getSelectedItem().toString());
                        else
                            cropDetailsModel.setProduced(null);

                        if(et_crop_sub_product.getSelectedItem()!=null)
                            cropDetailsModel.setSubProduct(et_crop_sub_product.getSelectedItem().toString());
                        else
                            cropDetailsModel.setSubProduct(null);

                        RadioButton seas = mView.findViewById(season.getCheckedRadioButtonId());
                        cropDetailsModel.setSeason(seas.getText().toString());

                        cropDetailsModelList.add(cropDetailsModel);
                    }
                    fpoAppModel.setCropDetailsModelList(cropDetailsModelList);

                    fpoAppModel.setCropIncome(editTextCrop.getText().toString());
                    fpoAppModel.setSericultureIncome(editTextSericulture.getText().toString());
                    fpoAppModel.setAgWorkIncome(editTextAgLabour.getText().toString());
                    fpoAppModel.setNonAgWorkIncome(editTextNonAgLabour.getText().toString());
                    fpoAppModel.setDairyIncome(editTextDairy.getText().toString());
                    fpoAppModel.setLiveStockIncome(editTextSheepIncome.getText().toString());
                    fpoAppModel.setCropProcessingIncome(editTextCropProcessing.getText().toString());
                    fpoAppModel.setOtherIncome(editTextOtherIncome.getText().toString());
                    fpoAppModel.setGovtDevelopmentIncome(editTextGovtWelfare.getText().toString());
                    RadioButton radioButton = mView.findViewById(mRadioGroupBank.getCheckedRadioButtonId());
                    fpoAppModel.setIsThereBankAcct(radioButton.getText().toString());

                    agToolsModelList = new ArrayList<>();
                    AgToolsModel agToolsModel = new AgToolsModel();
                    agToolsModel.setType(getString(R.string.negilu));
                    agToolsModel.setUnits(spNegilu.getSelectedItem().toString());
                    agToolsModel.setRate(editTextNegilu.getText().toString());
                    agToolsModelList.add(agToolsModel);

                    AgToolsModel bullcart = new AgToolsModel();
                    bullcart.setType(getString(R.string.bull_cart));
                    bullcart.setUnits(spBullCart.getSelectedItem().toString());
                    bullcart.setRate(editTextBullCart.getText().toString());
                    agToolsModelList.add(bullcart);


                    getObject(getString(R.string.koorige), spKoorige.getSelectedItem().toString(), editTextKoorige.getText().toString());
                    getObject(getString(R.string.seeding_mac), spNatiMac.getSelectedItem().toString(), editTextNatiMachine.getText().toString());
                    getObject(getString(R.string.pumpset), spPumpset.getSelectedItem().toString(), editTextPumpset.getText().toString());
                    getObject(getString(R.string.tiller), spTiller.getSelectedItem().toString(), editTextTiller.getText().toString());
                    getObject(getString(R.string.tractor), spTractor.getSelectedItem().toString(), editTextTrctor.getText().toString());
                    getObject(getString(R.string.sprayer), spSprayer.getSelectedItem().toString(), editTextSprayer.getText().toString());
                    getObject(getString(R.string.sprayer), spDustSprayer.getSelectedItem().toString(), editTextDustSprayer.getText().toString());
                    getObject(getString(R.string.hygrometer), spHygrometer.getSelectedItem().toString(), editTextHygrometer.getText().toString());
                    getObject(getString(R.string.humidiper), spHumidityFire.getSelectedItem().toString(), editTextHumidityFire.getText().toString());
                    getObject(getString(R.string.grass_cutter), spGrassCutter.getSelectedItem().toString(), editTextGrassCutter.getText().toString());
                    getObject(getString(R.string.jcb), spJCB.getSelectedItem().toString(), editTextJCB.getText().toString());
                    getObject(getString(R.string.thermomter), spThermometer.getSelectedItem().toString(), editTextThermometer.getText().toString());
                    getObject(getString(R.string.foggers), spFogger.getSelectedItem().toString(), editTextFogger.getText().toString());
                    getObject(getString(R.string.others), spOthers.getSelectedItem().toString(), editTextOthersMachine.getText().toString());
                    getObject(getString(R.string.drip), spDrip.getSelectedItem().toString(), editTextDrip.getText().toString());
                    getObject(getString(R.string.weeder), spWeeder.getSelectedItem().toString(), editTextWeeder.getText().toString());
                    getObject(getString(R.string.harvest), spHarvesting.getSelectedItem().toString(), editTextHarvest.getText().toString());
                    getObject(getString(R.string.okkane), spOkkane.getSelectedItem().toString(), editTextOkkane.getText().toString());
                    getObject(getString(R.string.ground_nut_remover), spGroundNut.getSelectedItem().toString(), editTextGroudnut.getText().toString());
                    getObject(getString(R.string.maize), spMaize.getSelectedItem().toString(), editTextMaize.getText().toString());

                    fpoAppModel.setAgToolsModelList(agToolsModelList);

                    vetDetailsModelList = new ArrayList<>();
                    vetObject(getString(R.string.ox), spOx.getSelectedItem().toString(), editTextOx.getText().toString());
                    vetObject(getString(R.string.local_cow), spcow.getSelectedItem().toString(), editTextCow.getText().toString());
                    vetObject(getString(R.string.hf_cow), spHfCow.getSelectedItem().toString(), editTextHF.getText().toString());
                    vetObject(getString(R.string.buffalo), spBuffalo.getSelectedItem().toString(), editTextBuffalo.getText().toString());
                    vetObject(getString(R.string.sheep), spSheep.getSelectedItem().toString(), editTextSheep.getText().toString());
                    vetObject(getString(R.string.goat), spGoat.getSelectedItem().toString(), editTextGoat.getText().toString());
                    vetObject(getString(R.string.pig), spPig.getSelectedItem().toString(), editTextPig.getText().toString());
                    vetObject(getString(R.string.poultry), spPoultry.getSelectedItem().toString(), editTextPoultry.getText().toString());
                    vetObject(getString(R.string.others), spOthersVet.getSelectedItem().toString(), editTextOtherVet.getText().toString());

                    fpoAppModel.setVetDetailsModelList(vetDetailsModelList);

                    fpoAppModel.setBankName(bankName);



                    mListener.onFragmentInteractionVet(fpoAppModel);
                }
            }
        });
        mRadioGroupBank.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.bank_yes)
                {
                    spBankList.setVisibility(View.VISIBLE);
                   // spBankList.setSelection(0);
                }
                else
                {
                    spBankList.setVisibility(View.GONE);
                }
            }
        });

         spNegilu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 negilu = numbers.get(position);
             }

             @Override
             public void onNothingSelected(AdapterView<?> parent) {

             }
         });
        spBullCart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bullcart = numbers.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spKoorige.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                koorige = numbers.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spNatiMac.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                matimachine = numbers.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spPumpset.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pumpset = numbers.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spTiller.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tiller = numbers.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spTractor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tractor = numbers.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spSprayer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sprayer = numbers.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spHygrometer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hygrometer = numbers.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spHumidityFire.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mumidtyfire = numbers.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spDrip.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                drip = numbers.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spHarvesting.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                harvesting = numbers.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spWeeder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                weeder = numbers.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spWeeder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                weeder = numbers.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spWeeder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                weeder = numbers.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spOkkane.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                okkane = numbers.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spGroundNut.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                groundnut = numbers.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spMaize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maize = numbers.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spGrassCutter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                grasscutter = numbers.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spJCB.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                jcb = numbers.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spThermometer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                thermometer = numbers.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spFogger.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fogger = numbers.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spOthers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                otherMachines = numbers.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spOx.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ox = numbers.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spBuffalo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                buffalo = numbers.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spcow.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cow = numbers.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spHfCow.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hfcow = numbers.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spSheep.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sheep = numbers.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spGoat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                goat = numbers.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spPoultry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                poultry = numbers.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spOthersVet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                otherVet = numbers.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spPig.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pig = numbers.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spBankList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bankName = bankNames.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spDustSprayer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dustsprayer = numbers.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addCrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLinearLayoutCropView.addView(views[count]);
                count++;
            }
        });
        removeCrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count>0) {
                    count--;
                    mLinearLayoutCropView.removeView(views[count]);
                }
            }
        });

    }

    private LinearLayout mLinearLayoutCropView;
    private int count=0;
    private List<String> cropList;
    private List<String> cropYield;
    private void initData() {
        DatabaseReference reference = KtdclApplication.getFireBaseRef();
        reference = reference.child("CROP_LIST");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cropList = new ArrayList<>();

                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Log.d(TAG, "onDataChange: "+dataSnapshot1.getValue().toString());
                    cropList.add(dataSnapshot1.getValue().toString());
                }
                initCropDataandOthers();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void initCropDataandOthers() {
        cropYield = new ArrayList<>();
        cropYield.add("0-1");
        cropYield.add("1-5");
        cropYield.add("6-10");
        cropYield.add("11-15");
        cropYield.add("16-20");
        cropYield.add("21-25");
        cropYield.add("26-30");


        for(int i=0;i<views.length; i++)
        {
            View view = getLayoutInflater().inflate(R.layout.crop_grown_lyt, null);
            view.findViewById(R.id.rg).setTag(i+11);
            view.findViewById(R.id.sp_crop_list).setTag(i+14);
            view.findViewById(R.id.et_crop_area).setTag(i+15);
            view.findViewById(R.id.et_crop_variety).setTag(i+16);
            view.findViewById(R.id.et_manufacture_exp).setTag(i+17);
            view.findViewById(R.id.sp_crop_yield).setTag(i+18);
            view.findViewById(R.id.sp_sub_prod_yield).setTag(i+19);

            Spinner cropSpinner = view.findViewById(R.id.sp_crop_list);
            ArrayAdapter cropAdapter = new ArrayAdapter(getContext(), R.layout.spinner_item, cropList);
            cropAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            cropSpinner.setAdapter(cropAdapter);
            cropSpinner.setSelection(0);

            Spinner cropYieldSpinner = view.findViewById(R.id.sp_crop_yield);
            ArrayAdapter cropYieldAdapter = new ArrayAdapter(getContext(), R.layout.spinner_item, cropYield );
            cropYieldAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            cropYieldSpinner.setAdapter(cropYieldAdapter);
            cropYieldSpinner.setSelection(0);

            Spinner cropSubYieldSpinner = view.findViewById(R.id.sp_sub_prod_yield);
            ArrayAdapter cropSubAdapter = new ArrayAdapter(getContext(), R.layout.spinner_item, cropYield);
            cropSubAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            cropSubYieldSpinner.setAdapter(cropSubAdapter);
            cropSubYieldSpinner.setSelection(0);

            views[i] = view;
        }
        mLinearLayoutCropView.addView(views[count]);
        count++;

        numbers = new ArrayList<String>();

        numbers.add("0");
        numbers.add("1");
        numbers.add("2");
        numbers.add("3");
        numbers.add("4");
        numbers.add("5");
        numbers.add("6");
        numbers.add("7");
        numbers.add("8");
        numbers.add("9");
        numbers.add("10");
        numbers.add("11");

        ArrayAdapter num = new ArrayAdapter(getActivity(), R.layout.spinner_item, numbers);
        num.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spNatiMac.setAdapter(num);
        spNatiMac.setSelection(0);

        spNegilu.setAdapter(num);
        spNegilu.setSelection(0);
        spBullCart.setAdapter(num);
        spBullCart.setSelection(0);
        spKoorige.setAdapter(num);
        spKoorige.setSelection(0);
        spPumpset.setAdapter(num);
        spPumpset.setSelection(0);
        spTiller.setAdapter(num);
        spTiller.setSelection(0);
        spTractor.setAdapter(num);
        spTractor.setSelection(0);
        spSprayer.setAdapter(num);
        spSprayer.setSelection(0);
        spHygrometer.setAdapter(num);
        spHygrometer.setSelection(0);
        spHumidityFire.setAdapter(num);
        spHumidityFire.setSelection(0);

        spDrip.setAdapter(num);
        spDrip.setSelection(0);
        spHarvesting.setAdapter(num);
        spHarvesting.setSelection(0);
        spWeeder.setAdapter(num);
        spWeeder.setSelection(0);
        spOkkane.setAdapter(num);
        spOkkane.setSelection(0);
        spGroundNut.setAdapter(num);
        spGroundNut.setSelection(0);
        spMaize.setAdapter(num);
        spMaize.setSelection(0);

        spGrassCutter.setAdapter(num);
        spGrassCutter.setSelection(0);
        spJCB.setAdapter(num);
        spJCB.setSelection(0);
        spThermometer.setAdapter(num);
        spThermometer.setSelection(0);
        spFogger.setAdapter(num);
        spFogger.setSelection(0);

        spOthers.setAdapter(num);
        spOthers.setSelection(0);
        spOx.setAdapter(num);
        spOx.setSelection(0);
        spBuffalo.setAdapter(num);
        spBuffalo.setSelection(0);
        spcow.setAdapter(num);
        spcow.setSelection(0);
        spHfCow.setAdapter(num);
        spHfCow.setSelection(0);
        spSheep.setAdapter(num);
        spSheep.setSelection(0);
        spGoat.setAdapter(num);
        spGoat.setSelection(0);
        spPoultry.setAdapter(num);
        spPoultry.setSelection(0);
        spOthersVet.setAdapter(num);
        spOthersVet.setSelection(0);

        spPig.setAdapter(num);
        spPig.setSelection(0);
        spDustSprayer.setAdapter(num);
        spDustSprayer.setSelection(0);

    }

    private void initViews() {
        addCrop = mView.findViewById(R.id.addCrop);
        removeCrop = mView.findViewById(R.id.removeCrop);
        mLinearLayoutCropView = mView.findViewById(R.id.cropDetailsView);
        mButtonNext = mView.findViewById(R.id.next);
        spBankList = mView.findViewById(R.id.sp_bank);
        spNegilu = mView.findViewById(R.id.negilu_spinner);
        spBullCart = mView.findViewById(R.id.bull_cart_spinner);
        spKoorige = mView.findViewById(R.id.koorige_spinner);
        spNatiMac = mView.findViewById(R.id.seeding_spinner);
        spPumpset = mView.findViewById(R.id.irrigation_pumpset_spinner);
        spTiller = mView.findViewById(R.id.itiller_spinner);
        spTractor = mView.findViewById(R.id.tractor_spinner);
        spSprayer = mView.findViewById(R.id.sprayer_spinner);
        spSheep = mView.findViewById(R.id.sheep_spinner);
        spHygrometer = mView.findViewById(R.id.hygro_spinner);
        spHumidityFire = mView.findViewById(R.id.hmidipere_spinner);
        spDrip = mView.findViewById(R.id.drip_spinner);
        spHarvesting = mView.findViewById(R.id.harvest_spinner);
        spWeeder = mView.findViewById(R.id.weed_spinner);
        spOkkane = mView.findViewById(R.id.okkane_spinner);
        spGroundNut = mView.findViewById(R.id.ground_nut_spinner);
        spMaize = mView.findViewById(R.id.maize_spinner);
        spGrassCutter = mView.findViewById(R.id.grass_spinner);
        spJCB = mView.findViewById(R.id.jcb_spinner);
        spThermometer = mView.findViewById(R.id.thermometer_spinner);
        spFogger = mView.findViewById(R.id.foggers_spinner);
        spOthers = mView.findViewById(R.id.others_spinner);
        spOx = mView.findViewById(R.id.ox_spinner);
        spBuffalo = mView.findViewById(R.id.buffalo_spinner);
        spcow = mView.findViewById(R.id.local_cow_spinner);
        spHfCow = mView.findViewById(R.id.hf_cow_spinner);
        spGoat = mView.findViewById(R.id.goat_spinner);
        spPoultry = mView.findViewById(R.id.poultry_spinner);
        spOthersVet = mView.findViewById(R.id.vet_others_spinner);
        spPig = mView.findViewById(R.id.pig_spinner);
        spDustSprayer = mView.findViewById(R.id.dust_spinner);
        mRadioGroupBank = mView.findViewById(R.id.bank);

        //editTextCrop, editTextSericulture, editTextAgLabour, editTextNonAgLabour,editTextDairy, editTextSheep, editTextCropProcessing, editTextOtherIncome;
        editTextCrop = mView.findViewById(R.id.et_crop_income);
        editTextSericulture = mView.findViewById(R.id.et_sericulture_income);
        editTextAgLabour = mView.findViewById(R.id.et_ag_wage_income);
        editTextNonAgLabour = mView.findViewById(R.id.et_non_ag_wage_income);
        editTextDairy = mView.findViewById(R.id.et_dairy_income);
        editTextSheepIncome = mView.findViewById(R.id.et_other_vet_sell_income);
        editTextCropProcessing = mView.findViewById(R.id.et_crop_process_income);
        editTextOtherIncome = mView.findViewById(R.id.et_other_income);
        editTextGovtWelfare = mView.findViewById(R.id.et_govt_schemes);


        editTextNegilu = mView.findViewById(R.id.et_negilu_rate);
        editTextBullCart = mView.findViewById(R.id.et_bull_cart_rate);
        editTextKoorige = mView.findViewById(R.id.et_koorige_rate);
        editTextNatiMachine = mView.findViewById(R.id.et_seeding_rate);
        editTextPumpset = mView.findViewById(R.id.et_pumpset_rate);
        editTextTiller = mView.findViewById(R.id.et_tiller_rate);
        editTextTrctor = mView.findViewById(R.id.tractor_rate);
        editTextSprayer = mView.findViewById(R.id.sprayer_rate);
        editTextDustSprayer = mView.findViewById(R.id.dust_sprayer_rate);
        editTextHygrometer= mView.findViewById(R.id.hygrometer_rate);
        editTextHumidityFire = mView.findViewById(R.id.huidipere_rate);
        editTextDrip = mView.findViewById(R.id.drip_rate);
        editTextHarvest = mView.findViewById(R.id.harvest_rate);
        editTextWeeder = mView.findViewById(R.id.weeder_rate);
        editTextOkkane = mView.findViewById(R.id.okkane_rate);
        editTextGroudnut = mView.findViewById(R.id.ground_nut_rate);
        editTextMaize = mView.findViewById(R.id.maizw_rate);
        editTextGrassCutter = mView.findViewById(R.id.grass_rate);

        editTextJCB = mView.findViewById(R.id.jcb_rate);
        editTextThermometer = mView.findViewById(R.id.thermometer_rate);
        editTextFogger = mView.findViewById(R.id.foggers_rate);
        editTextOthersMachine = mView.findViewById(R.id.others_rate);

        editTextOx = mView.findViewById(R.id.ox_rate);
        editTextBuffalo = mView.findViewById(R.id.buffalo_rate);
        editTextCow = mView.findViewById(R.id.local_cow_rate);

        editTextHF = mView.findViewById(R.id.hf_cow_rate);
        editTextSheep = mView.findViewById(R.id.sheep_rate);
        editTextGoat = mView.findViewById(R.id.goat_rate);

        editTextPoultry = mView.findViewById(R.id.poultry_rate);
        editTextOtherVet = mView.findViewById(R.id.et_vet_others_rate);
        editTextPig = mView.findViewById(R.id.pig_rate);

    }

    private OnFragmentInteractionListener mListener;
    public interface OnFragmentInteractionListener {
        public void onFragmentInteractionVet(FPOAppModel uri);
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

    private void getObject(String type, String unit, String rate)
    {
        AgToolsModel nati = new AgToolsModel();
        nati.setType(type);
        nati.setUnits(unit);
        nati.setRate(rate);
        agToolsModelList.add(nati);
    }
    private ApiInterface apiInterface;

    private void setUpNetwork() {
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        retrofitInstance.setClient();
        apiInterface = retrofitInstance.getClient().create(ApiInterface.class);
    }
    private boolean checkInternet() {
        ConnectivityManager mgr = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = mgr.getActiveNetworkInfo();

        if (netInfo != null) {
            if (netInfo.isConnected()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    List<String> bankNames;
    private void getBankList()
    {
        if (checkInternet()) {
            retrofit2.Call<List<BankNameModel>> listCall = apiInterface.getBanks();
            listCall.enqueue(new Callback<List<BankNameModel>>() {
                @Override
                public void onResponse(retrofit2.Call<List<BankNameModel>> call, Response<List<BankNameModel>> response) {
                    if (response.isSuccessful()) {
                        bankNames = new ArrayList<>();
                        for(int i = 0; i<response.body().size(); i++)
                        {
                            bankNames.add(response.body().get(i).getName());
                        }
                        ArrayAdapter aa = new ArrayAdapter(getActivity(), R.layout.spinner_item, bankNames);
                        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spBankList.setAdapter(aa);
                        spBankList.setSelection(0);
                    }
                }

                @Override
                public void onFailure(retrofit2.Call<List<BankNameModel>> call, Throwable t) {
                    Toast.makeText(getContext(), R.string.something_wrong, Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(getContext(), "No internet", Toast.LENGTH_LONG).show();
        }
    }
    private void vetObject(String type, String unit, String rate)
    {
        VetDetailsModel nati = new VetDetailsModel();
       nati.setAnimalName(type);
       nati.setAnimalCount(unit);
        nati.setAnimalRate(rate);
        vetDetailsModelList.add(nati);
    }

}