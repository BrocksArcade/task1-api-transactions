package com.csds.transactiondissplay.networkutility;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.csds.transactiondissplay.models.TransactionModel;

import java.util.List;

@Dao
public interface DAO_transaction {
    @Query("SELECT * FROM transactionTB")
    List<TransactionModel> getAllTransactoins();
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<TransactionModel> transactionModels);
}
