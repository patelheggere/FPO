package com.ktdcl.fpo;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.StrictMode;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class KtdclApplication extends Application {
    private static KtdclApplication mInstance;
    private static DatabaseReference databaseReference;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());


        // ApiClient.intialise();
       /* if(isDeve()) {
            ApiClient.setBaseUrl(AppConstants.appBaseUrlDev);
        }
        else
        {
            ApiClient.setBaseUrl(AppConstants.appBaseUrl);

        }*/

    }
    public static synchronized DatabaseReference getFireBaseRef()
    {
        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        if(BuildConfig.DEBUG) {
            databaseReference = FirebaseDatabase.getInstance().getReference().child("FPO").child("test");
        }
        else {
            databaseReference = FirebaseDatabase.getInstance().getReference().child("FPO").child("prod");
        }
        return databaseReference;
    }


    public static synchronized KtdclApplication getInstance() {
        return mInstance;
    }

}