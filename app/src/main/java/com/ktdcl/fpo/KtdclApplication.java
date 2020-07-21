package com.ktdcl.fpo;

import android.app.Application;
import android.os.StrictMode;


public class KtdclApplication extends Application {
    private static KtdclApplication mInstance;
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
    /*public static synchronized DatabaseReference getFireBaseRef()
    {
        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        if(BuildConfig.DEBUG) {
            databaseReference = FirebaseDatabase.getInstance().getReference().child("KTDCL").child("test");
        }
        else {
            databaseReference = FirebaseDatabase.getInstance().getReference().child("KTDCL").child("prod");
        }
        return databaseReference;
    }*/

    public static synchronized KtdclApplication getInstance() {
        return mInstance;
    }

}