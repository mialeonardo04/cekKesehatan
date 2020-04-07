package com.tetapdirumah.selfcheck.rest;

import com.tetapdirumah.selfcheck.model.ApiResponse;
import com.tetapdirumah.selfcheck.model.DataResponse;
import com.tetapdirumah.selfcheck.model.FormDiagnose;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("diagnose/covid")
    Observable<ApiResponse<List<DataResponse>>> diagnose(@Body FormDiagnose formDiagnose);
}
