package com.csds.transactiondissplay.dasshboard;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.csds.transactiondissplay.R;
import com.csds.transactiondissplay.databinding.ActivityHomescreenBinding;
import com.csds.transactiondissplay.loginform.loginactivity;
import com.csds.transactiondissplay.models.TransactionModel;
import com.csds.transactiondissplay.networkutility.RetroFitObject;
import com.csds.transactiondissplay.utility.SharedPrefsUtility;
import com.csds.transactiondissplay.utility.database_util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityHomescreen extends AppCompatActivity {
    ActivityHomescreenBinding binding;
    Adapter_transaction adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_homescreen);
        binding.butLogout.setOnClickListener(view -> {
            SharedPrefsUtility.logout(this);
            startActivity(new Intent(this, loginactivity.class));
            finish();
        });
        adapter = new Adapter_transaction(new ArrayList<>());
        binding.rvTransactionview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.rvTransactionview.setAdapter(adapter);
        binding.searchBar.setOnCloseListener(() -> {
            adapter.resetList();
            return true;
        });
        binding.materialToolbar.setTitle("Welcome, "+ SharedPrefsUtility.getusernmae());
        binding.searchBar.setIconifiedByDefault(false);
        binding.searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (s.trim().isEmpty() || s.trim().length() < 3) {

                    adapter.resetList();
                } else {
                    if (!adapter.filterList(s)) {
                        showToast("No items Found");
                    }
                }

                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.trim().isEmpty()) {
                    adapter.resetList();
                } else {
                    adapter.filterList(s);
                }
                return false;
            }
        });
        binding.swipetorefehs.setOnRefreshListener(this::refreshTransactionList);
        binding.ivDarkmode.setOnClickListener(view -> {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES ? AppCompatDelegate.MODE_NIGHT_NO : AppCompatDelegate.MODE_NIGHT_YES);
        });
        refreshTransactionList();

    }

    private void setRefreshing(boolean refresh) {
        binding.swipetorefehs.setRefreshing(refresh);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {

        AlertDialog.Builder exitdialog = new AlertDialog.Builder(this);
        exitdialog.setPositiveButton("Exit", (dialogInterface, i) -> System.exit(0));
        exitdialog.setNegativeButton("Cancel", (dialog, i) -> dialog.dismiss());
        exitdialog.setMessage("Do you wanna exit?");
        exitdialog.setTitle("Exit Dialog");
        AlertDialog dialog = exitdialog.create();
        dialog.show();


    }

    private void refreshTransactionList() {
        if(true) {
            RetroFitObject.getRetrofit().getAllTransactions(SharedPrefsUtility.getToken()).enqueue(new Callback<List<TransactionModel>>() {
                @Override
                public void onResponse(Call<List<TransactionModel>> call, Response<List<TransactionModel>> response) {
                    if (response.body() != null) {

                        runOnUiThread(() -> {
                            try {
                                database_util.insertAlltransactions(response.body());
                                adapter.setOriginalTransactionList(response.body());
                                binding.tvTranactiontotal.setText("Total Transactions:" + response.body().size());
                                setRefreshing(false);
                                showToast("Transaction Refreshed");
                            } catch (Exception e) {
                                showToast(e.getMessage());
                                setRefreshing(false);
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<List<TransactionModel>> call, Throwable t) {

                    database_util.getAllTransactions(models -> runOnUiThread(() -> {
                        showToast("Network Failure, watching offline data");
                        binding.tvTranactiontotal.setText("Total Transactions:" + models.size());
                        setRefreshing(false);
                        adapter.setOriginalTransactionList(models);
                    }));
                    setRefreshing(false);
                }
            });
        }

    }

    private void showToast(String s) {
        Toast.makeText(ActivityHomescreen.this, s, Toast.LENGTH_LONG).show();
    }
}