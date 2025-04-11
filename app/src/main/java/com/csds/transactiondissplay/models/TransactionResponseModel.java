package com.csds.transactiondissplay.models;

import java.util.List;

public class TransactionResponseModel {

    List<TransactionModel> transactionList;

    public List<TransactionModel> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<TransactionModel> transactionList) {
        this.transactionList = transactionList;
    }

    public TransactionResponseModel() {
    }

    public TransactionResponseModel(List<TransactionModel> transactionList) {
        this.transactionList = transactionList;
    }
}
