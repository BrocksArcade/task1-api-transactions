package com.csds.transactiondissplay.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "transactionTB")
public class TransactionModel {
    public TransactionModel() {
    }

    @PrimaryKey
    @SerializedName("id")
    private int id;
    @ColumnInfo(name = "date")
    @SerializedName("date")
    private String date;
    @ColumnInfo(name = "amount")
    @SerializedName("amount")
    private int amount;
    public int getId() {
        return id;
    }
    public String getidString(){return String.valueOf(id);}

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmount() {
        return (amount);
    }
    public String getAmountString(){return String.valueOf(amount);}

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @ColumnInfo(name = "category")
    @SerializedName("category")
    private String category;
    @ColumnInfo(name = "description")
    @SerializedName("description")
    private String description;

    public boolean checkIfStringExist(String s)
    {
        return category.trim().toLowerCase().contains(s.trim().toLowerCase()) || description.trim().toLowerCase().contains(s.trim().toLowerCase());
    }
}
