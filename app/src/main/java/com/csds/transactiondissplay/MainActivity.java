package com.csds.transactiondissplay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;

import com.csds.transactiondissplay.dasshboard.ActivityHomescreen;
import com.csds.transactiondissplay.loginform.loginactivity;
import com.csds.transactiondissplay.utility.SharedPrefsUtility;
import com.csds.transactiondissplay.utility.database_util;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        findViewById(R.id.iv_bio).setOnClickListener(view -> {
            showBioPrompt();

        });
        SharedPrefsUtility.initializeSF(getApplicationContext());
        database_util.initDB(this);
        showBioPrompt();




    }

    private void proceedToNextScreen() {

        if (!SharedPrefsUtility.isLoggedIn()) {
            startActivity(new Intent(MainActivity.this, loginactivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            finish();
        } else {
            startActivity(new Intent(MainActivity.this, ActivityHomescreen.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            finish();
        }
    }

    private void showBioPrompt() {
        BiometricPrompt.PromptInfo promptinfo = new BiometricPrompt.PromptInfo.Builder().setTitle("Biometric Lock").setDescription("To use this App you req to unlock via device key").setNegativeButtonText("Cancel").setSubtitle("Put your fingerprint on sensor or unlock via device pin/path").build();

        BiometricPrompt biometricPrompt = new BiometricPrompt(this,getMainExecutor(), new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                showToast("Error Please Retry");
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                proceedToNextScreen();

            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                showToast("Invalid Credentials , Please Retry");
            }


        });
        biometricPrompt.authenticate(promptinfo);
    }

    private void showToast(String s) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
}