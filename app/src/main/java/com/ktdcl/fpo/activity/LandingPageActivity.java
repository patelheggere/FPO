

package com.ktdcl.fpo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ktdcl.fpo.R;
import com.ktdcl.fpo.SecondMainActivity;
import com.ktdcl.fpo.utils.SharedPrefsHelper;

public class LandingPageActivity extends AppCompatActivity {
    private static final String TAG = "LandingPageActivity";
    private Button Register, Dashboard, Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        Register = findViewById(R.id.btn_register);
        Dashboard = findViewById(R.id.btn_dashboard);
        Logout = findViewById(R.id.btn_logout);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LandingPageActivity.this, BasicDetailsActivity.class);
                startActivity(intent);
            }
        });

        Dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LandingPageActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefsHelper.getInstance().clearAllData();
                Intent intent = new Intent(LandingPageActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}