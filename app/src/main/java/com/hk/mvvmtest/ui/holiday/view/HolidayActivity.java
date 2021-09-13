package com.hk.mvvmtest.ui.holiday.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hk.mvvmtest.ui.holiday.adapter.HolidayAdapter;
import com.hk.mvvmtest.data.models.HolidayModel;
import com.hk.mvvmtest.ui.holiday.viewModel.HolidayViewModel;
import com.hk.mvvmtest.R;
import com.hk.mvvmtest.databinding.ActivityHolidayBinding;


import java.util.List;

public class HolidayActivity extends AppCompatActivity {
    final String TAG = getClass().getSimpleName();
    ActivityHolidayBinding binding;
    HolidayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_holiday);
        initUI();

        if(isNetworkConnectionAvailable()) {
            binding.progressBar.setVisibility(View.VISIBLE);

            HolidayViewModel holidayViewModel = new HolidayViewModel();
            holidayViewModel.getHolidays().observe(this, new Observer<List<HolidayModel>>() {
                @Override
                public void onChanged(List<HolidayModel> currencyPojos) {
                    if (currencyPojos != null && !currencyPojos.isEmpty()) {
                        Log.e(TAG, "observe onChanged()=" + currencyPojos.size());
                        binding.progressBar.setVisibility(View.GONE);
                        adapter.addHolidayList(currencyPojos);
                        adapter.notifyDataSetChanged();
                    }
                }
            });

        }else{
            Toast.makeText(this, "No Network Available", Toast.LENGTH_LONG).show();
        }
    }

    private void initUI() {
        binding.rvHolidayList.setHasFixedSize(true);
        binding.rvHolidayList.setLayoutManager(new LinearLayoutManager(this));

        adapter = new HolidayAdapter();
        binding.rvHolidayList.setAdapter(adapter);
    }

    boolean isNetworkConnectionAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null) return false;
        NetworkInfo.State network = info.getState();
        return (network == NetworkInfo.State.CONNECTED || network == NetworkInfo.State.CONNECTING);
    }
}