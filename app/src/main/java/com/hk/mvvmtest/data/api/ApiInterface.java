package com.hk.mvvmtest.data.api;

import com.hk.mvvmtest.data.models.HolidayModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("PublicHolidays/2021/us")
    Call<List<HolidayModel>> getHolidays();
}
