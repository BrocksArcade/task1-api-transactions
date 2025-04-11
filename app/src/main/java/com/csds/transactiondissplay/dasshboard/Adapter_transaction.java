package com.csds.transactiondissplay.dasshboard;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.csds.transactiondissplay.R;
import com.csds.transactiondissplay.databinding.ItemTranactionBinding;
import com.csds.transactiondissplay.models.TransactionModel;

import java.util.ArrayList;
import java.util.List;

public class Adapter_transaction extends RecyclerView.Adapter<Adapter_transaction.viewholderclass> {
    List<TransactionModel> OriginalTransactionList;
    List<TransactionModel> filteredList;

    public Adapter_transaction(List<TransactionModel> transactionModelList) {
        this.OriginalTransactionList = transactionModelList;
        filteredList = OriginalTransactionList;
    }

    public void setOriginalTransactionList(List<TransactionModel> newlist) {
        OriginalTransactionList = newlist;
        filteredList = newlist;
        notifyDataSetChanged();
    }

    public void resetList() {
        filteredList = OriginalTransactionList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public viewholderclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewholderclass(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_tranaction, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewholderclass holder, int position) {
        holder.bind(filteredList.get(position));
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public boolean filterList(String s) {

        List<TransactionModel> filteredList = new ArrayList<>();
        OriginalTransactionList.forEach(it -> {
            if (it.checkIfStringExist(s)) {
                filteredList.add(it);
            }
        });
        setFilteredList(filteredList);
        return !filteredList.isEmpty();
    }

    private void setFilteredList(List<TransactionModel> filteredList) {
        this.filteredList = filteredList;
        notifyDataSetChanged();
    }

    public static class viewholderclass extends RecyclerView.ViewHolder {
        ItemTranactionBinding binding;

        public viewholderclass(@NonNull ItemTranactionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(TransactionModel model) {
            if (binding != null) {
                binding.setModel(model);
            }

        }
    }
}
