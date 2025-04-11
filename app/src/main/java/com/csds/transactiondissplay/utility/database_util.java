package com.csds.transactiondissplay.utility;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.csds.transactiondissplay.models.TransactionModel;
import com.csds.transactiondissplay.networkutility.DAO_transaction;

import java.util.List;

public class database_util {
    public static DAO_transaction daoTransaction;
    static mydatabase db;

    static public synchronized void initDB(Context context) {
        if (db == null) {
            {
                try {
                    db = Room.databaseBuilder(context, mydatabase.class, "mydatabase").build();
                    daoTransaction = db.trnsactionDAO();
                } catch (Exception e) {
                    Log.e("Eoor", e.getMessage());
                }
            }

        }


    }

    public static void insertAlltransactions(List<TransactionModel> body) {
        new Thread(() -> db.trnsactionDAO().insertAll(body)).start();

    }

    public static void getAllTransactions(dblistener listener) {
        new Thread(() -> {
            listener.onTransactionRecived(daoTransaction.getAllTransactoins());
        }).start();
    }

    public interface dblistener {
        void onTransactionRecived(List<TransactionModel> models);
    }
}
