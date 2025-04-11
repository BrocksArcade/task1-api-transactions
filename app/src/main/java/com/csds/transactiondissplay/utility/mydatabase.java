package com.csds.transactiondissplay.utility;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.csds.transactiondissplay.models.TransactionModel;
import com.csds.transactiondissplay.networkutility.DAO_transaction;

@Database(entities = {TransactionModel.class},exportSchema = false,version = 1)
public abstract class mydatabase extends RoomDatabase {
    public abstract DAO_transaction trnsactionDAO();
}
