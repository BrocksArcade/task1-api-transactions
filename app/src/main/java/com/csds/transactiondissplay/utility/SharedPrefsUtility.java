package com.csds.transactiondissplay.utility;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Objects;

public class SharedPrefsUtility {
    public static final String TOKEN = "token";
    public static final String SAVE_GAME_NAME = "playerssave";
    public static final String UNAME = "uname";
    static String masterKey = null;
    static SharedPreferences sharedPreferences;
    static private SharedPreferences.Editor sharedPrefsEditor;

    static public void initializeSF(Context appcontext) {

        try {
            masterKey = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
            sharedPreferences = EncryptedSharedPreferences.create(SAVE_GAME_NAME, masterKey, appcontext, EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);


        } catch (GeneralSecurityException | IOException e) {
            Log.e("Excp", Objects.requireNonNull(e.getMessage()));
        }

    }

    static public void saveUserLoginToken(String token, String username, String password) {
        sharedPrefsEditor = sharedPreferences.edit();
        sharedPrefsEditor.putString(TOKEN, token);
        sharedPrefsEditor.putString(UNAME, username);
        sharedPrefsEditor.putString("pwd", password);
        sharedPrefsEditor.apply();
        sharedPrefsEditor.commit();
    }
    static public String getusernmae(){return sharedPreferences.getString(UNAME,"defaultUser");}

    static public boolean isLoggedIn() {
        String s=sharedPreferences.getString(TOKEN, "");
        return !s.trim().isEmpty();
    }

    @SuppressLint("CommitPrefEdits")
    public static void logout(Context context) {
        context.deleteSharedPreferences(SAVE_GAME_NAME);

    }

    public static String getToken() {
        return sharedPreferences.getString(TOKEN, "");
    }
}
