package com.hk.mvvmtest.ui.holiday.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hk.mvvmtest.data.models.HolidayModel;
import com.hk.mvvmtest.R;
import com.hk.mvvmtest.databinding.ItemHolidayJavaBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HolidayAdapter extends RecyclerView.Adapter<HolidayAdapter.MyViewHolder> {

    private List<HolidayModel> holidayList;

    public HolidayAdapter() {
        holidayList = new ArrayList<>();
    }

    public void addHolidayList(List<HolidayModel> currencyList) {
        this.holidayList = currencyList;
    }

    @Override
    public int getItemCount() {
        return holidayList != null ? holidayList.size() : 0;
    }

    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        ItemHolidayJavaBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_holiday_java, parent, false);

        return new MyViewHolder(binding);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemHolidayJavaBinding binding;

        MyViewHolder(ItemHolidayJavaBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.binding.setModel( holidayList.get(position) );
    }

}
