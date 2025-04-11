package com.csds.transactiondissplay.loginform;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.csds.transactiondissplay.R;
import com.csds.transactiondissplay.dasshboard.ActivityHomescreen;
import com.csds.transactiondissplay.databinding.ActivityLoginactivityBinding;
import com.csds.transactiondissplay.models.loginRequestModel;
import com.csds.transactiondissplay.models.loginResponseModel;
import com.csds.transactiondissplay.networkutility.RetroFitObject;
import com.csds.transactiondissplay.utility.SharedPrefsUtility;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class loginactivity extends AppCompatActivity {
    ActivityLoginactivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_loginactivity);
        binding.butLogin.setOnClickListener(view -> {
            String uname = Objects.requireNonNull(binding.edtUname.getText()).toString();
            String pwd = Objects.requireNonNull(binding.editPwd.getText()).toString();
            if (uname.trim().isEmpty() || pwd.trim().isEmpty()) {
                showToast("Enter Valid Username and Password");
                return;
            } else {
                RetroFitObject.getRetrofit().tryLogin(new loginRequestModel(uname, pwd)).enqueue(new Callback<loginResponseModel>() {
                    @Override
                    public void onResponse(@NonNull Call<loginResponseModel> call, @NonNull Response<loginResponseModel> response) {

                        loginResponseModel body = response.body();
                        if (body != null) {
                            if (body.isWasSuccess()) {
                                showToast("Login Success Redirecting");
                                SharedPrefsUtility.saveUserLoginToken(body.getToken(), uname, pwd);
                                startActivity(new Intent(loginactivity.this, ActivityHomescreen.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                finish();
                            } else {
                                showToast("Invalid Credentials Retry");
                            }

                        } else {
                            showToast("Invalid Crededntials Retry");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<loginResponseModel> call, @NonNull Throwable t) {
                        showToast("Invalid Crededntials Retry");
                    }
                });

            }


        });

    }

    private void showToast(String message) {
        Toast.makeText(loginactivity.this, message, Toast.LENGTH_LONG).show();
    }
}