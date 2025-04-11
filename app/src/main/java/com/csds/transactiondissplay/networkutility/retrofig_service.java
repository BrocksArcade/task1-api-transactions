package com.csds.transactiondissplay.networkutility;

import com.csds.transactiondissplay.models.TransactionResponseModel;
import com.csds.transactiondissplay.models.loginRequestModel;
import com.csds.transactiondissplay.models.loginResponseModel;
import com.csds.transactiondissplay.models.TransactionModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface retrofig_service {
    @Headers("Accept: application/json")
    @POST("/login")
    Call<loginResponseModel> tryLogin(@Body loginRequestModel userinfo);

    @GET("/transactions")
    Call<List<TransactionModel>> getAllTransactions(@Header("Authorization") String token);
}
