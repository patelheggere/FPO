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
import android.widget.EditText;
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
import com.ktdcl.fpo.model.DistrictModel;
import com.ktdcl.fpo.model.FPOAppModel;
import com.ktdcl.fpo.model.LandDetailsModel;
import com.ktdcl.fpo.model.TalukModel;
import com.ktdcl.fpo.model.VillageModel;
import com.ktdcl.fpo.network.ApiInterface;
import com.ktdcl.fpo.network.RetrofitInstance;
import com.ktdcl.fpo.utils.SharedPrefsHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BasicDetailsFragment extends Fragment {
    private static final String TAG = "BasicDetailsFragment";
    private View mView;
    private Button mButtonSave;
    private List<DistrictModel> districtModelList;
    private List<TalukModel> talukModelList;
    private List<VillageModel> villageModelList;

    private List<String> mDistrictNamesList;
    private List<String> mAssemblyNamesList;
    private List<String> mTalukNames;
    private List<String> mTandaNames;
    private List<String> mQualificationList;
    private List<String> mMemberList;
    private List<String> numbers;

    private List<String> sourceOfIncomes, secondaryOfIncomes, cropList;


    private Spinner spinnerTaluk, spinnerDist, spinnerTanda, spinnerQualification,
            spinnerMember, spGents, spWomen, spChildren, spEducated, spWorkers,
            spMainSourceIncome, spSecondarySourceIncome, spCropList;
    private String dist = null,  taluk = null, village = null;
    private String qual=null;
    private String memmber=null;

    private Button mImageButtonAddLand, mImageButtonRemoveLand;
    private LinearLayout mLinearLayoutLand;
    private int count=0;
    private Button mButtonSubmit;

    private  View[] views = new View[15];

    private TextInputEditText editTextAadhar, editTextFarmerName, editTextFatherName, editTextMobile, editTextFRUITSNo, editTextAge,
             editTextFamilyIncomeSource, editTextSecondIncomeSource;
    private String gender, aadhar, farmerName, fatherName, phone, fruitsNo, age, menCount, womenCount, workerCount, childCount, educatedCount, mainIncome, secondIncome;

    private RadioGroup mRadioGroupGender;
    private LinearLayout progressbarLyt;


    public BasicDetailsFragment() {
        // Required empty public constructor
    }


    public static BasicDetailsFragment newInstance(String param1, String param2) {
        BasicDetailsFragment fragment = new BasicDetailsFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
         //   mParam1 = getArguments().getString(ARG_PARAM1);
         //   mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_basic_details, container, false);
        mButtonSave = mView.findViewById(R.id.btn_submit);
        initViews();
        initData();
        initListeners();
        return mView;
    }

    private void initData() {
        mQualificationList = new ArrayList<>();
        mQualificationList.add("ವಿದ್ಯಾಭ್ಯಾಸ ಆಯ್ಕೆಮಾಡಿ");
        mQualificationList.add("ಅನಕ್ಷರಸ್ತರು");
        mQualificationList.add("ಓದು ಬರಹ ಬಲ್ಲವರು");
        mQualificationList.add("ಪ್ರಾಥಮಿಕ ಶಿಕ್ಷಣ");
        mQualificationList.add("ಮಾಧ್ಯಮಿಕ");
        mQualificationList.add("ಪ್ರೌಢ");
        mQualificationList.add("ಪದವಿ ಪೂರ್ವ");
        mQualificationList.add("ಪದವಿ");
        mQualificationList.add("ಡಿಪ್ಲೋಮ್");
        mQualificationList.add("ಐಟಿಐ");
        mQualificationList.add("ಸ್ನಾತ್ತಕೋತ್ತರ");

        ArrayAdapter aa = new ArrayAdapter(getContext(), R.layout.spinner_item, mQualificationList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerQualification.setAdapter(aa);
        spinnerQualification.setSelection(0);

        mMemberList = new ArrayList<>();
        mMemberList.add("ಸ್ಥಳೀಯ ಸಂಸ್ಥೆಗಳ ಸದಸ್ಯತ್ವ ಆಯ್ಕೆಮಾಡಿ");
        mMemberList.add("ಗ್ರಾಮ ಪಂಚಾಯ್ತಿ");
        mMemberList.add("ತಾಲ್ಲೂಕು ಪಂಚಾಯ್ತಿ");
        mMemberList.add("ಜಿಲ್ಲಾ ಪಂಚಾಯ್ತಿ");
        mMemberList.add("ಎನಜಿಒ");
        mMemberList.add("ಸ್ವಯಂ ಸೇವಾ ಸಂಸ್ಥೆ");
        mMemberList.add("ಹಾಲು ಉತ್ಪಾದಕರ ಸಹಕಾರ ಸಂಘ");
        mMemberList.add("ಸ್ತ್ರೀ ಶಕ್ತಿ");
        mMemberList.add("ಸ್ವಸಹಾಯ ಸಂಘ");
        mMemberList.add("ಬಳಕೆಧಾರರ ಸಂಘ");
        mMemberList.add("ರೈತ ಸಂಘ");
        mMemberList.add("ಇತರೆ");
        mMemberList.add("ಯಾವುದೂ ಇಲ್ಲ");

        ArrayAdapter memAdapter = new ArrayAdapter(getContext(), R.layout.spinner_item, mMemberList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerMember.setAdapter(memAdapter);
        spinnerMember.setSelection(0);

        sourceOfIncomes = new ArrayList<>();
        sourceOfIncomes.add(getString(R.string.main_income_source));
        sourceOfIncomes.add("ಕೃಷಿ");
        sourceOfIncomes.add("ಕೂಲಿ");
        sourceOfIncomes.add("ಹೈನುಗಾರಿಕೆ");
        sourceOfIncomes.add("ಕುರಿಸಾಕಾಣಿಕೆ");;
        sourceOfIncomes.add("ಕೋಳಿಸಾಕಾಣಿಕೆ");
        sourceOfIncomes.add("ಇತರೆ");

        ArrayAdapter mainIncomeAdapter = new ArrayAdapter(getContext(), R.layout.spinner_item, sourceOfIncomes);
        mainIncomeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spMainSourceIncome.setAdapter(mainIncomeAdapter);
        spMainSourceIncome.setSelection(0);

        secondaryOfIncomes = new ArrayList<>();

        secondaryOfIncomes.add(getString(R.string.sec_income_src));
        secondaryOfIncomes.add("ಕೃಷಿ");
        secondaryOfIncomes.add("ಕೂಲಿ");
        secondaryOfIncomes.add("ಹೈನುಗಾರಿಕೆ");
        secondaryOfIncomes.add("ಕುರಿಸಾಕಾಣಿಕೆ");;
        secondaryOfIncomes.add("ಕೋಳಿಸಾಕಾಣಿಕೆ");
        secondaryOfIncomes.add("ಇತರೆ");
        ArrayAdapter secondAdapter = new ArrayAdapter(getContext(), R.layout.spinner_item, secondaryOfIncomes);
        secondAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spSecondarySourceIncome.setAdapter(secondAdapter);
        spSecondarySourceIncome.setSelection(0);

        cropList = new ArrayList<>();
        cropList.add("ಮೆಕ್ಕೆ ಜೋಳ");
        cropList.add("ಬಿಳಿ ಜೋಳ");
        cropList.add("ಸೂರ್ಯಕಾಂತಿ");
        cropList.add("ರಾಗಿ");
        cropList.add("ಭತ್ತ");
        cropList.add("ಭತ್ತ");
        cropList.add("ಕಬ್ಬು");

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
                initCropData();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void initCropData(){
        for(int i=0;i<views.length; i++)
        {
            View view = getLayoutInflater().inflate(R.layout.land_details, null);
            view.findViewById(R.id.rg).setTag(i+11);
            view.findViewById(R.id.et_survey_no).setTag(i+14);
            view.findViewById(R.id.et_land_dimen).setTag(i+15);
            view.findViewById(R.id.et_land_value).setTag(i+16);
            view.findViewById(R.id.sp_crop_list).setTag(i+17);


            view.findViewById(R.id.et_district).setTag(i+18);
            view.findViewById(R.id.et_taluk).setTag(i+19);
            view.findViewById(R.id.et_village).setTag(i+20);

         //   view.findViewById(R.id.sp_land_exist_dist).setTag(i+18);
          //  view.findViewById(R.id.sp_land_exist_taluk).setTag(i+19);
          //  view.findViewById(R.id.sp_land_exist_hobli).setTag(i+20);
          //  view.findViewById(R.id.sp_land_exist_village).setTag(i+21);
            //   view.findViewById(R.id.sp_land_exist_dist).setTag(i+18);
            //  view.findViewById(R.id.sp_land_exist_taluk).setTag(i+19);
            //  view.findViewById(R.id.sp_land_exist_hobli).setTag(i+20);
            //  view.findViewById(R.id.sp_land_exist_village).setTag(i+21);

            Spinner cropSpinner = view.findViewById(R.id.sp_crop_list);
            ArrayAdapter cropAdapter = new ArrayAdapter(getContext(), R.layout.spinner_item, cropList);
            cropAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            cropSpinner.setAdapter(cropAdapter);
            cropSpinner.setSelection(0);

            views[i] = view;
        }

        mLinearLayoutLand.addView(views[count]);
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

        spChildren.setAdapter(num);
        spChildren.setSelection(0);

        spEducated.setAdapter(num);
        spEducated.setSelection(0);

        spWorkers.setAdapter(num);
        spWorkers.setSelection(0);

        spWomen.setAdapter(num);
        spWomen.setSelection(0);

        spGents.setAdapter(num);
        spGents.setSelection(0);

        setUpNetwork();
        getDistricts();
    }

    private void initViews() {
        progressbarLyt = mView.findViewById(R.id.progressbarLayt);
        spinnerDist = mView.findViewById(R.id.sp_dist);
        spinnerTaluk = mView.findViewById(R.id.sp_taluk);
        spinnerTanda = mView.findViewById(R.id.sp_village);
        spGents = mView.findViewById(R.id.sp_gents);
        spWomen = mView.findViewById(R.id.sp_women);
        spChildren = mView.findViewById(R.id.sp_child);
        spEducated = mView.findViewById(R.id.sp_educated);
        spWorkers = mView.findViewById(R.id.sp_working);

        spinnerQualification = mView.findViewById(R.id.sp_qualification);
        spinnerMember = mView.findViewById(R.id.sp_member);
        spMainSourceIncome = mView.findViewById(R.id.sp_main_source_of_income);
        spSecondarySourceIncome = mView.findViewById(R.id.sp_second_source_of_income);

        mImageButtonAddLand = mView.findViewById(R.id.add_land);
        mImageButtonRemoveLand = mView.findViewById(R.id.remove_land);
        mLinearLayoutLand = mView.findViewById(R.id.landView);

        editTextAadhar = mView.findViewById(R.id.et_uid);
        editTextFarmerName = mView.findViewById(R.id.et_name);
        editTextFatherName = mView.findViewById(R.id.et_fname);
        editTextMobile = mView.findViewById(R.id.et_phone);
        editTextFRUITSNo = mView.findViewById(R.id.et_fruits);
        editTextAge = mView.findViewById(R.id.et_age);
        editTextFamilyIncomeSource = mView.findViewById(R.id.et_main_income_source);
        editTextSecondIncomeSource = mView.findViewById(R.id.et_sec_income_source);
    }

    FPOAppModel fpoAppModel = new FPOAppModel();
    private void initListeners() {
        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null)
                {
                    if(dist!=null)
                    {
                        fpoAppModel.setDist(dist);
                    }
                    else
                    {
                        Toast.makeText(getContext(), getString(R.string.select_dist), Toast.LENGTH_LONG).show();
                        return;
                    }
                    if(taluk!=null)
                    {
                        fpoAppModel.setTaluk(taluk);
                    }
                    else
                    {
                        Toast.makeText(getContext(), getString(R.string.select_taluk), Toast.LENGTH_LONG).show();
                        return;
                    }
                    if(village!=null)
                    {
                        fpoAppModel.setVillage(village);
                    }
                    else
                    {
                        Toast.makeText(getContext(), getString(R.string.select_village), Toast.LENGTH_LONG).show();
                        return;
                    }

                    if(editTextFarmerName.getText()!=null && editTextFarmerName.getText().toString().length()>0)
                    {
                        fpoAppModel.setName(editTextFarmerName.getText().toString());
                    }
                    else
                    {
                        Toast.makeText(getContext(), getString(R.string.enter_name), Toast.LENGTH_LONG).show();
                        editTextFarmerName.setError(getContext().getString(R.string.enter_name));
                        return;
                    }
                    if(editTextFatherName.getText()!=null && editTextFatherName.getText().toString().length()>0)
                    {
                        fpoAppModel.setFname(editTextFatherName.getText().toString());
                    }
                    else
                    {
                        Toast.makeText(getContext(), getString(R.string.enter_father_name), Toast.LENGTH_LONG).show();
                        editTextFatherName.setError(getString(R.string.enter_father_name));
                        return ;
                    }
                    if(editTextAadhar.getText()!=null && editTextAadhar.getText().toString().length()>0 && editTextAadhar.getText().toString().length()==12 )
                    {
                        fpoAppModel.setAadha(editTextAadhar.getText().toString());
                    }
                    else
                    {
                        Toast.makeText(getContext(), getString(R.string.enter_aadhar), Toast.LENGTH_LONG).show();
                        editTextAadhar.setError(getString(R.string.enter_aadhar));
                        return ;
                    }

                    if(editTextMobile.getText()!=null && editTextMobile.getText().toString().length()>0 && editTextMobile.getText().toString().length()==10 )
                    {
                        fpoAppModel.setPhone(editTextMobile.getText().toString());
                    }
                    else
                    {
                        Toast.makeText(getContext(), getString(R.string.enter_phone), Toast.LENGTH_LONG).show();
                        editTextMobile.setError(getString(R.string.enter_phone));
                        return ;
                    }

                    if(editTextFRUITSNo.getText()!=null && editTextFRUITSNo.getText().toString().length()>0  )
                    {
                        fpoAppModel.setFruitsNo(editTextFRUITSNo.getText().toString());
                    }

                    if(editTextAge.getText()!=null && editTextAge.getText().toString().length()>0 && Integer.parseInt(editTextAge.getText().toString())<100 )
                    {
                        fpoAppModel.setPhone(editTextMobile.getText().toString());
                    }
                    else
                    {
                        Toast.makeText(getContext(), getString(R.string.enter_age), Toast.LENGTH_LONG).show();
                        editTextAge.setError(getString(R.string.enter_age));
                        return ;
                    }
                    if(memmber!=null)
                    {
                        fpoAppModel.setMemOfLocalOrg(memmber);
                    }
                    else
                    {
                        Toast.makeText(getContext(), getString(R.string.select_membership), Toast.LENGTH_LONG).show();
                        return;
                    }
                    if(qual!=null)
                    {
                        fpoAppModel.setQualification(qual);
                    }
                    else
                    {
                        Toast.makeText(getContext(), getString(R.string.select_education), Toast.LENGTH_LONG).show();
                        return;
                    }
                    mRadioGroupGender = mView.findViewById(R.id.gender_radio_gp);
                    RadioButton radioButton = mView.findViewById(mRadioGroupGender.getCheckedRadioButtonId());
                    fpoAppModel.setGender(radioButton.getText().toString());
                    fpoAppModel.setMainIncomeSource(editTextFamilyIncomeSource.getText().toString());
                    fpoAppModel.setSecondIncomeSource(editTextSecondIncomeSource.getText().toString());

                    if(mainIncome!=null)
                    {
                        fpoAppModel.setMainIncomeSource(mainIncome);
                    }
                    else {
                        Toast.makeText(getContext(), getString(R.string.select_main_income), Toast.LENGTH_LONG).show();
                    }

                    if(secondIncome!=null)
                    {
                        fpoAppModel.setSecondIncomeSource(secondIncome);
                    }
                    else {
                        Toast.makeText(getContext(), getString(R.string.select_second_income), Toast.LENGTH_LONG).show();
                    }

                    List<LandDetailsModel> landDetailsModelList = new ArrayList<>();
                    for(int i=0;i<count; i++)
                    {
                        TextInputEditText survey = mView.findViewWithTag(i+14);
                        TextInputEditText area = mView.findViewWithTag(i+15);
                        TextInputEditText value = mView.findViewWithTag(i+16);
                        Spinner crop = mView.findViewWithTag(i+17);
                        RadioGroup radioGroup = mView.findViewWithTag(i+11);


                        TextInputEditText dist = mView.findViewWithTag(i+18);
                        TextInputEditText taluk = mView.findViewWithTag(i+19);
                        TextInputEditText village = mView.findViewWithTag(i+20);


                        LandDetailsModel landDetailsModel = new LandDetailsModel();
                        landDetailsModel.setSurveyNumber(survey.getText().toString());
                        landDetailsModel.setLand(area.getText().toString());
                        landDetailsModel.setCrop(crop.getSelectedItem().toString());
                        landDetailsModel.setLandValue(value.getText().toString());

                        landDetailsModel.setDistrict(dist.getText().toString());
                        landDetailsModel.setTaluk(taluk.getText().toString());
                        landDetailsModel.setVillage(village.getText().toString());

                        RadioButton radioButton2 = mView.findViewById(radioGroup.getCheckedRadioButtonId());
                        if(R.id.irrigation==radioGroup.getCheckedRadioButtonId()){
                            Log.d(TAG, "onClick: "+radioButton.getText());
                        }
                        else if(R.id.dry==radioGroup.getCheckedRadioButtonId()) {
                            Log.d(TAG, "onClick: "+radioButton.getText());
                        }
                        else if(R.id.waste==radioGroup.getCheckedRadioButtonId())
                        {
                            Log.d(TAG, "onClick: "+radioButton.getText());
                        }
                        landDetailsModel.setLandType(radioButton2.getText().toString());
                        landDetailsModelList.add(landDetailsModel);
                    }
                    fpoAppModel.setNoLadies(womenCount);
                    fpoAppModel.setNoGents(menCount);
                    fpoAppModel.setNoChildren(childCount);
                    fpoAppModel.setNoOfEducated(educatedCount);
                    fpoAppModel.setWorkingInLand(workerCount);

                    fpoAppModel.setLandDetailsModelList(landDetailsModelList);
                    DatabaseReference databaseReference = KtdclApplication.getFireBaseRef();
                    databaseReference = databaseReference.child("FPO").child("DataSave").child(SharedPrefsHelper.getInstance().get("FPO_ID").toString());
                    databaseReference.setValue(fpoAppModel);
                    databaseReference = databaseReference.child("FPO").child("DataSaveStage").child(SharedPrefsHelper.getInstance().get("FPO_ID").toString());
                    databaseReference.setValue("Basic");
                    mListener.onFragmentInteractionBasic(fpoAppModel);

                }
            }
        });


        spMainSourceIncome.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if(position!=0)
                {
                    mainIncome = sourceOfIncomes.get(position);
                }
                else
                {
                    mainIncome = null;
                    secondIncome = null;
                    spSecondarySourceIncome.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spSecondarySourceIncome.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if(position!=0)
                {
                    if(mainIncome.equalsIgnoreCase(secondaryOfIncomes.get(position))){
                        Toast.makeText(getContext(), getString(R.string.both_income_same), Toast.LENGTH_LONG).show();
                        spSecondarySourceIncome.setSelection(0);
                    }
                    else {
                        secondIncome = secondaryOfIncomes.get(position);
                    }
                }
                else
                {
                    secondIncome = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinnerDist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0)
                {
                    dist = districtModelList.get(position-1).getDist_name();
                    getTaluks(districtModelList.get(position-1).getDist_id());
                }
                else
                {
                    dist = null;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerTaluk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //  product = mDistrictNamesList.get(position);
                if(position!=0)
                {
                    taluk = talukModelList.get(position-1).getName();
                    getTanda(talukModelList.get(position-1).getTlk_id());
                }
                else
                {
                    taluk = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        spinnerTanda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if(position!=0)
                {
                    village = villageModelList.get(position-1).getTanda_name();
                }
                else
                {
                    village = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerQualification.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i!=0)
                {
                    qual = mQualificationList.get(i);
                }
                else
                {
                    qual = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerMember.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i!=0)
                {
                    memmber = mMemberList.get(i);
                }
                else
                {
                    memmber = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spGents.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                menCount = numbers.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spWomen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                womenCount = numbers.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spChildren.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                childCount = numbers.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spEducated.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                educatedCount = numbers.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spWorkers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                workerCount = numbers.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mImageButtonAddLand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLinearLayoutLand.addView(views[count]);
                count++;
            }
        });
        mImageButtonRemoveLand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count>0) {
                    count--;
                    mLinearLayoutLand.removeView(views[count]);
                }
            }
        });


    }

    private OnFragmentInteractionListener mListener;
    public interface OnFragmentInteractionListener {
        public void onFragmentInteractionBasic(FPOAppModel uri);
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
    private ApiInterface apiInterface;

    private void setUpNetwork() {
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        retrofitInstance.setClient();
        apiInterface = retrofitInstance.getClient().create(ApiInterface.class);
    }

    private void getTaluks(String dist_id) {
        progressbarLyt.setVisibility(View.VISIBLE);
        Call<List<TalukModel>> listCall = apiInterface.getTaluks(dist_id);
        listCall.enqueue(new Callback<List<TalukModel>>() {
            @Override
            public void onResponse(Call<List<TalukModel>> call, Response<List<TalukModel>> response) {
                progressbarLyt.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    talukModelList = response.body();
                    mTalukNames = new ArrayList<>();
                    mTalukNames.add(getActivity().getString(R.string.Select_Taluk));
                    if (talukModelList != null && talukModelList.size() > 0) {
                        for (int i = 0; i < talukModelList.size(); i++) {
                            mTalukNames.add(talukModelList.get(i).getName());
                        }
                        ArrayAdapter aa = new ArrayAdapter(getContext(), R.layout.spinner_item, mTalukNames);
                        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerTaluk.setAdapter(aa);
                        spinnerTaluk.setSelection(0);

                    }
                }
            }

            @Override
            public void onFailure(Call<List<TalukModel>> call, Throwable t) {
                progressbarLyt.setVisibility(View.GONE);
                Toast.makeText(getContext(), R.string.something_wrong, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getTanda(String tlkdId) {
        progressbarLyt.setVisibility(View.VISIBLE);
        Call<List<VillageModel>> listCall = apiInterface.getTandasByTaluk(tlkdId);
        listCall.enqueue(new Callback<List<VillageModel>>() {
            @Override
            public void onResponse(Call<List<VillageModel>> call, Response<List<VillageModel>> response) {
                progressbarLyt.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    villageModelList = response.body();
                    mTandaNames = new ArrayList<>();
                    mTandaNames.add(getContext().getString(R.string.Select_Tanda));
                    if (villageModelList != null && villageModelList.size() > 0) {
                        for (int i = 0; i < villageModelList.size(); i++) {
                            mTandaNames.add(villageModelList.get(i).getTanda_name());
                        }
                        ArrayAdapter aa = new ArrayAdapter(getContext(), R.layout.spinner_item, mTandaNames);
                        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        spinnerTanda.setAdapter(aa);
                        spinnerTanda.setSelection(0);

                    }
                }
            }

            @Override
            public void onFailure(Call<List<VillageModel>> call, Throwable t) {
                progressbarLyt.setVisibility(View.GONE);
                Toast.makeText(getContext(), R.string.something_wrong, Toast.LENGTH_LONG).show();

            }
        });
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

    private void getDistricts() {
        if (checkInternet()) {
            progressbarLyt.setVisibility(View.VISIBLE);
            Call<List<DistrictModel>> listCall = apiInterface.getAllDistricts();
            listCall.enqueue(new Callback<List<DistrictModel>>() {
                @Override
                public void onResponse(Call<List<DistrictModel>> call, Response<List<DistrictModel>> response) {
                    progressbarLyt.setVisibility(View.GONE);
                    if (response.isSuccessful()) {
                        districtModelList = response.body();
                        mDistrictNamesList = new ArrayList<>();
                        mDistrictNamesList.add(getActivity().getString(R.string.Select_District));
                        for (int i = 0; i < districtModelList.size(); i++) {
                            mDistrictNamesList.add(districtModelList.get(i).getDist_name());
                        }
                        ArrayAdapter aa = new ArrayAdapter(getActivity(), R.layout.spinner_item, mDistrictNamesList);
                        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerDist.setAdapter(aa);
                        spinnerDist.setSelection(0);

                    }
                }

                @Override
                public void onFailure(Call<List<DistrictModel>> call, Throwable t) {
                    progressbarLyt.setVisibility(View.GONE);
                    Toast.makeText(getContext(), R.string.something_wrong, Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(getContext(), "No internet", Toast.LENGTH_LONG).show();
        }
    }
}