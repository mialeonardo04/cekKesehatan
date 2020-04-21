package com.tetapdirumah.selfcheck.rest;

import com.tetapdirumah.selfcheck.model.ApiResponse;
import com.tetapdirumah.selfcheck.model.DataResponse;
import com.tetapdirumah.selfcheck.model.FormDiagnose;
import com.tetapdirumah.selfcheck.model.FormUpdate;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {

    @POST("diagnose/covid")
    Observable<ApiResponse<List<DataResponse>>> diagnose(@Body FormDiagnose formDiagnose);

    @POST("diagnose/covid/{id}")
    Observable<DataResponse> update(@Path("id") String id, @Body FormUpdate formUpdate);
}
