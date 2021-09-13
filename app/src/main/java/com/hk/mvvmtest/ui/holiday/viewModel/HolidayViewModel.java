package com.hk.mvvmtest.ui.holiday.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hk.mvvmtest.data.models.HolidayModel;
import com.hk.mvvmtest.HolidayRepo;

import java.util.List;

public class HolidayViewModel extends ViewModel {
    private HolidayRepo holidayRepo;
    private MutableLiveData<List<HolidayModel>> mutableLiveData;

    public HolidayViewModel(){
        holidayRepo = new HolidayRepo();
    }

    public LiveData<List<HolidayModel>> getHolidays() {
        if(mutableLiveData==null){
            mutableLiveData = holidayRepo.requestHolidays();
        }
        return mutableLiveData;
    }
}
