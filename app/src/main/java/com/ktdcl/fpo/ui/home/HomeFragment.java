package com.ktdcl.fpo.ui.home;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;
import com.ktdcl.fpo.R;
import com.ktdcl.fpo.model.DistrictModel;
import com.ktdcl.fpo.model.TalukModel;
import com.ktdcl.fpo.model.VillageModel;
import com.ktdcl.fpo.network.ApiInterface;
import com.ktdcl.fpo.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    private HomeViewModel homeViewModel;
    private List<DistrictModel> districtModelList;
    private List<TalukModel> talukModelList;
    private List<VillageModel> villageModelList;

    private List<String> mDistrictNamesList;
    private List<String> mAssemblyNamesList;
    private List<String> mTalukNames;
    private List<String> mTandaNames;
    private List<String> mQualificationList;
    private List<String> mMemberList;

    private Spinner spinnerTaluk, spinnerDist, spinnerTanda, spinnerQualification, spinnerMember;
    private String dist = null,  taluk = null, village = null;
    private String qual=null;
    private String memmber=null;

    private Button mImageButtonAddLand, mImageButtonRemoveLand;
    private LinearLayout mLinearLayoutLand;
    private int count=0;
    private Button mButtonSubmit;

    private  View[] views = new View[15];
    private View root;
    private Spinner spNegilu, spBullCart, spKoorige, spNatiMac, spPumpset, spTiller, spTractor, spSprayer, spHygrometer, spHumidityFire, spDrip,
    spHarvesting, spWeeder, spOkkane, spGroundNut, spMaize, spGrassCutter, spJCB, spThermometer, spFogger, spOthers,
    spOx, spBuffalo, spcow, spHfCow, spSheep, spGoat, spPoultry, spOthersVet, spPig, spDustSprayer;

    private List<String> numbers;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        spinnerDist = root.findViewById(R.id.sp_dist);
        spinnerTaluk = root.findViewById(R.id.sp_taluk);
        spinnerTanda = root.findViewById(R.id.sp_village);
        spinnerQualification = root.findViewById(R.id.sp_qualification);
        spinnerMember = root.findViewById(R.id.sp_member);
        mImageButtonAddLand = root.findViewById(R.id.add_land);
        mImageButtonRemoveLand = root.findViewById(R.id.remove_land);
        mLinearLayoutLand=root.findViewById(R.id.landView);
        mButtonSubmit = root.findViewById(R.id.btn_submit);

        spNegilu = root.findViewById(R.id.negilu_spinner);
        spBullCart = root.findViewById(R.id.bull_cart_spinner);
        spKoorige = root.findViewById(R.id.koorige_spinner);
        spNatiMac = root.findViewById(R.id.seeding_spinner);
        spPumpset = root.findViewById(R.id.irrigation_pumpset_spinner);
        spTiller = root.findViewById(R.id.itiller_spinner);
        spTractor = root.findViewById(R.id.tractor_spinner);
        spSprayer = root.findViewById(R.id.sprayer_spinner);
        spSheep = root.findViewById(R.id.sheep_spinner);
        spHygrometer = root.findViewById(R.id.hygro_spinner);
        spHumidityFire = root.findViewById(R.id.hmidipere_spinner);
        spDrip = root.findViewById(R.id.drip_spinner);
        spHarvesting = root.findViewById(R.id.harvest_spinner);
        spWeeder = root.findViewById(R.id.weed_spinner);
        spOkkane = root.findViewById(R.id.okkane_spinner);
        spGroundNut = root.findViewById(R.id.ground_nut_spinner);
        spMaize = root.findViewById(R.id.maize_spinner);
        spGrassCutter = root.findViewById(R.id.grass_spinner);
        spJCB = root.findViewById(R.id.jcb_spinner);
        spThermometer = root.findViewById(R.id.thermometer_spinner);
        spFogger = root.findViewById(R.id.foggers_spinner);
        spOthers = root.findViewById(R.id.others_spinner);
        spOx = root.findViewById(R.id.ox_spinner);
        spBuffalo = root.findViewById(R.id.buffalo_spinner);
        spcow = root.findViewById(R.id.local_cow_spinner);
        spHfCow = root.findViewById(R.id.hf_cow_spinner);
        spGoat = root.findViewById(R.id.goat_spinner);
        spPoultry = root.findViewById(R.id.poultry_spinner);
        spOthersVet = root.findViewById(R.id.vet_others_spinner);
        spPig = root.findViewById(R.id.pig_spinner);
        spDustSprayer = root.findViewById(R.id.dust_spinner);

        initData();
        initListeners();
        setUpNetwork();
        getDistricts();
        return root;
    }

    private void initData() {
        mQualificationList = new ArrayList<>();
        mQualificationList.add("ವಿದ್ಯಾಭ್ಯಾಸ ಆಯ್ಕೆಮಾಡಿ");
        mQualificationList.add("ಅನಕ್ಷರಸ್ತರು");
        mQualificationList.add("ಓದು ಬರಹ ಬಲ್ಲವರು");
        mQualificationList.add("ಪ್ರಾಥಮಿಕ ಶಿಕ್ಷಣ");
        mQualificationList.add("ಮಾಧ್ಯಮಿಕ");
        mQualificationList.add("ಪ್ರೌಢ ಪದವಿ ಪೂರ್ವ");
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

        for(int i=0;i<views.length; i++)
        {
            View view = getLayoutInflater().inflate(R.layout.land_details, null);
            RadioGroup radioGroup = view.findViewById(R.id.rg);
            radioGroup.setId(i+11);
            view.findViewById(R.id.et_land_dimen).setTag(i+15);
            view.findViewById(R.id.et_survey_no).setTag(i+14);
            view.findViewById(R.id.et_land_value).setTag(i+16);
            view.findViewById(R.id.crop_grown).setTag(i+17);
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

    private void initListeners() {

        spinnerDist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //  product = mDistrictNamesList.get(position);
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

        mButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<count; i++)
                {
                    TextInputEditText text =root.findViewWithTag(i+15);
                    Log.d(TAG, "onClick: "+text.getText());
                }
            }
        });
    }

    private ApiInterface apiInterface;

    private void setUpNetwork() {
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        retrofitInstance.setClient();
        apiInterface = retrofitInstance.getClient().create(ApiInterface.class);

    }

    private void getTaluks(String dist_id) {
        Call<List<TalukModel>> listCall = apiInterface.getTaluks(dist_id);
        listCall.enqueue(new Callback<List<TalukModel>>() {
            @Override
            public void onResponse(Call<List<TalukModel>> call, Response<List<TalukModel>> response) {
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
                Toast.makeText(getContext(), R.string.something_wrong, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getTanda(String tlkdId) {
        Call<List<VillageModel>> listCall = apiInterface.getTandasByTaluk(tlkdId);
        listCall.enqueue(new Callback<List<VillageModel>>() {
            @Override
            public void onResponse(Call<List<VillageModel>> call, Response<List<VillageModel>> response) {
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
            Call<List<DistrictModel>> listCall = apiInterface.getAllDistricts();
            listCall.enqueue(new Callback<List<DistrictModel>>() {
                @Override
                public void onResponse(Call<List<DistrictModel>> call, Response<List<DistrictModel>> response) {
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
                    Toast.makeText(getContext(), R.string.something_wrong, Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(getContext(), "No internet", Toast.LENGTH_LONG).show();
        }
    }
}