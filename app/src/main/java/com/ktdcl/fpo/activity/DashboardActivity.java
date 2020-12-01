package com.ktdcl.fpo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.ktdcl.fpo.Adapter.FarmersAdapter;
import com.ktdcl.fpo.R;
import com.ktdcl.fpo.model.FarmerModel;
import com.ktdcl.fpo.network.ApiInterface;
import com.ktdcl.fpo.network.RetrofitInstance;
import com.ktdcl.fpo.utils.SharedPrefsHelper;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {
    private static final String TAG = "DashboardActivity";
    private ApiInterface apiInterface;
    private RecyclerView recyclerView;
    private TextView textViewTotal;
    private FarmersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        setUpNetwork();
        recyclerView = findViewById(R.id.recyclerview);
        textViewTotal = findViewById(R.id.total);
         LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DashboardActivity.this);
        //linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        getDetails();
    }

    private void setUpNetwork() {
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        retrofitInstance.setClient();
        apiInterface = retrofitInstance.getClient().create(ApiInterface.class);
    }

    private void getDetails()
    {
        String id = SharedPrefsHelper.getInstance().get("FPO_ID");
        Call<List<FarmerModel>> call = apiInterface.GetFarmersByCEO(id);
        call.enqueue(new Callback<List<FarmerModel>>() {
            @Override
            public void onResponse(Call<List<FarmerModel>> call, Response<List<FarmerModel>> response) {
                if(response!=null)
                {
                    textViewTotal.setText("Total:"+response.body().size());
                  //  Collections.reverse(response.body());
                    adapter = new FarmersAdapter(DashboardActivity.this, response.body());
                    recyclerView.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<List<FarmerModel>> call, Throwable t) {

            }
        });

    }
}