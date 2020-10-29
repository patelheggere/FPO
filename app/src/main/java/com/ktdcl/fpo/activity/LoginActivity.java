package com.ktdcl.fpo.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.ktdcl.fpo.MainActivity;
import com.ktdcl.fpo.R;
import com.ktdcl.fpo.model.APIResponseModel;
import com.ktdcl.fpo.model.DistrictModel;
import com.ktdcl.fpo.model.LoginModel;
import com.ktdcl.fpo.model.TalukModel;
import com.ktdcl.fpo.model.VillageModel;
import com.ktdcl.fpo.network.ApiInterface;
import com.ktdcl.fpo.utils.SharedPrefsHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ktdcl.fpo.utils.AppUtils.Constants.EMAIL;
import static com.ktdcl.fpo.utils.AppUtils.Constants.FIRST_TIME;
import static com.ktdcl.fpo.utils.AppUtils.Constants.MOBILE;
import static com.ktdcl.fpo.utils.AppUtils.Constants.NAME;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "RegistrationActivity";
    private ActionBar mActionBar;
    private Spinner spinner;
    private TextInputEditText textInputEditTextName, textInputEditTextPhone,textInputEditTextPhoneLogin, textInputEditTextEmail, textInputEditTextPwd, textInputEditTextPwdLogin;
    private String course;
    private List<String> listInterest;
    private ArrayAdapter<String> adapter;
    private Button mRegisterSubmit, mButtonLoginSubmit, mButtonLogin, mButtonRegister;
    private DatabaseReference databaseReference;
    private View registerView, loginView;
    private ProgressBar mProgressBar;
    private Button btn_upload;
    private TextView mTextViewForgot;
    private String[] PERMISSIONS = {
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            /* android.Manifest.permission.RECORD_AUDIO,*/
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    int PERMISSION_ALL = 1;
    private final int PICK_IMAGE_REQUEST = 71;
    private final int PICK_IMAGE_REQUEST_2 = 1111;
    private final int PICK_VIDEO_REQUEST_2 = 2222;
    private static final int SELECT_FILE = 1100;
    private static final int REQUEST_CAMERA = 1200;
    private static final int SELECT_VIDEO = 1300;
    private static final int REQUEST_CODE_DOC = 1400;
    private static final int RQS_RECORDING = 1500;
    private static final int VIDEO_CAPTURE = 1600;
    private static final int AUDIO_LOCAL = 1700;

    private ApiInterface apiInterface;
    private List<DistrictModel> districtModelList;
    private List<TalukModel> talukModelList;
    private List<VillageModel> villageModelList;

    private List<String> mDistrictNamesList;
    private List<String> mAssemblyNamesList;
    private List<String> mTalukNames;
    private List<String> mTandaNames;
    private Spinner mSpinnerDistrict, mSpinnerAssembly, mSpinnerTaluk, mSpinnerVillage;
    private String dist=null, taluk=null, village=null, tandaRes = "yes", desiredJob=null, qual=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
    }

    private void initData() {

    }

    private void initView() {
        textInputEditTextPwdLogin = findViewById(R.id.et_pwd_login);
        mButtonLoginSubmit = findViewById(R.id.btn_login_submit);
        textInputEditTextPhoneLogin = findViewById(R.id.et_phone_login);
        mProgressBar = findViewById(R.id.progress_bar);


        mButtonLoginSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitLoginDetails();
            }
        });
    }

    private void submitLoginDetails() {
        if(textInputEditTextPhoneLogin.getText()==null || textInputEditTextPhoneLogin.getText().length()!=10)
        {
            textInputEditTextPhoneLogin.setError(getString(R.string.phone_correct));
            return;
        }
        if(textInputEditTextPwdLogin.getText()==null || textInputEditTextPwdLogin.getText().length()<3)
        {
            textInputEditTextPwdLogin.setError(getString(R.string.enter_pwd));
            return;
        }
        mButtonLoginSubmit.setEnabled(false);
        mButtonLoginSubmit.setClickable(false);
        mProgressBar.setVisibility(View.VISIBLE);
        LoginModel loginModel = new LoginModel();
        loginModel.setPhone(textInputEditTextPhoneLogin.getText().toString());
        loginModel.setPwd(textInputEditTextPwdLogin.getText().toString());
        Call<APIResponseModel> userVerifyCall = apiInterface.verifyUser(loginModel.getPhone(), loginModel.getPwd());
        userVerifyCall.enqueue(new Callback<APIResponseModel>() {
            @Override
            public void onResponse(Call<APIResponseModel> call, Response<APIResponseModel> response) {
                mProgressBar.setVisibility(View.GONE);
                if(response.isSuccessful() && response.body().isStatus())
                {
                    SharedPrefsHelper.getInstance().save(FIRST_TIME, true);
                    SharedPrefsHelper.getInstance().save(NAME,response.body().getName());
                    SharedPrefsHelper.getInstance().save(MOBILE, response.body().getPhone());
                    SharedPrefsHelper.getInstance().save(EMAIL, response.body().getEmail());
                    SharedPrefsHelper.getInstance().save("PICK_URL", response.body().getImageURL());
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }else{
                    mButtonLoginSubmit.setEnabled(true);
                    mButtonLoginSubmit.setClickable(true);
                    showToast(getString(R.string.invalid));
                }
            }

            @Override
            public void onFailure(Call<APIResponseModel> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                mButtonLoginSubmit.setEnabled(true);
                mButtonLoginSubmit.setClickable(true);
                showToast(getString(R.string.something_wrong));
            }
        });
    }

    private void showToast(String string) {
        Toast.makeText(LoginActivity.this, string, Toast.LENGTH_LONG).show();
    }
}