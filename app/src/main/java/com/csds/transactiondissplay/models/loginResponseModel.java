package com.csds.transactiondissplay.models;

import com.google.gson.annotations.SerializedName;

public class loginResponseModel {
    @SerializedName("success")
    boolean wasSuccess;
    @SerializedName("token")
    String token;
    @SerializedName("message")
    String message;

    public loginResponseModel() {
    }

    public boolean isWasSuccess() {
        return wasSuccess;
    }

    public void setWasSuccess(boolean wasSuccess) {
        this.wasSuccess = wasSuccess;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public loginResponseModel(boolean wasSuccess, String token) {
        this.wasSuccess = wasSuccess;
        this.token = token;
    }
}
