package com.ktdcl.fpo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.ktdcl.fpo.fragments.BasicDetailsFragment;
import com.ktdcl.fpo.fragments.CropInsuranceOtherFragment;
import com.ktdcl.fpo.fragments.CropVetToolsFragment;
import com.ktdcl.fpo.fragments.MarketingDetailsFragment;
import com.ktdcl.fpo.model.FPOAppModel;
import com.ktdcl.fpo.model.ResponseModel;
import com.ktdcl.fpo.network.ApiInterface;
import com.ktdcl.fpo.network.RetrofitInstance;
import com.ktdcl.fpo.utils.AppUtils;
import com.ktdcl.fpo.utils.SharedPrefsHelper;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondMainActivity extends AppCompatActivity implements BasicDetailsFragment.OnFragmentInteractionListener, CropInsuranceOtherFragment.OnFragmentInteractionListener, CropVetToolsFragment.OnFragmentInteractionListener, MarketingDetailsFragment.OnFragmentInteractionListener{
    private static final String TAG = "SecondMainActivity";
    private FragmentManager fragmentManager;// = getSupportFragmentManager();
    private FragmentTransaction fragmentTransaction;// = fragmentManager.beginTransaction();
    private FPOAppModel fpoAppModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_main);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        BasicDetailsFragment fragment = new BasicDetailsFragment();
        fragmentTransaction.add(R.id.container, fragment, "basic");
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteractionBasic(FPOAppModel uri) {
        fpoAppModel = uri;
        Bundle bundle = new Bundle();
        bundle.putParcelable("FPOMODEL",uri);
        fragmentTransaction = fragmentManager.beginTransaction();
        CropVetToolsFragment hello = new CropVetToolsFragment();
        hello.setArguments(bundle);
        fragmentTransaction.replace(R.id.container, hello, "VET");
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteractionCrop(FPOAppModel uri) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("FPOMODEL",uri);
        fragmentTransaction = fragmentManager.beginTransaction();
        MarketingDetailsFragment hello = new MarketingDetailsFragment();
        hello.setArguments(bundle);
        fragmentTransaction.replace(R.id.container, hello, "MARKET");
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteractionVet(FPOAppModel uri) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("FPOMODEL",uri);
        fragmentTransaction = fragmentManager.beginTransaction();
        CropInsuranceOtherFragment hello = new CropInsuranceOtherFragment();
        hello.setArguments(bundle);
        fragmentTransaction.replace(R.id.container, hello, "INSUR");
        fragmentTransaction.commit();
    }
    private boolean checkInternet() {
        ConnectivityManager mgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
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

    @Override
    public void onFragmentInteractionMarket(FPOAppModel uri) {
        fpoAppModel = uri;
        if(checkInternet()) {
            Gson gson = new Gson();
            String jsonObject = gson.toJson(uri);
            setUpNetwork();
            fpoAppModel.setCeo_id(SharedPrefsHelper.getInstance().get("FPO_ID").toString());
            Call<ResponseModel> responseModelCall = apiInterface.insertFPO(fpoAppModel);
            responseModelCall.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    Log.d(TAG, "onResponse: ");
                    if (response.isSuccessful() && response.body().isStatus()) {
                        AppUtils.showToast(getString(R.string.save_success));
                        fpoAppModel = null;
                        fragmentTransaction = fragmentManager.beginTransaction();
                        BasicDetailsFragment fragment = new BasicDetailsFragment();
                        fragmentTransaction.replace(R.id.container, fragment, "basic");
                        fragmentTransaction.commit();
                    }
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Log.d(TAG, "onFailure: ");
                    AppUtils.showToast(getString(R.string.failure));
                }
            });
        }else{
            AppUtils.showToast(getString(R.string.internet));
        }

    }
    private ApiInterface apiInterface;

    private void setUpNetwork() {
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        retrofitInstance.setClient();
        apiInterface = retrofitInstance.getClient().create(ApiInterface.class);
    }
}