package com.tetapdirumah.selfcheck.presenter;

import android.content.SharedPreferences;

import com.tetapdirumah.selfcheck.contract.ContractResult;
import com.tetapdirumah.selfcheck.manager.IDataManager;
import com.tetapdirumah.selfcheck.model.ApiResponse;
import com.tetapdirumah.selfcheck.model.DataResponse;
import com.tetapdirumah.selfcheck.model.FormDiagnose;
import com.tetapdirumah.selfcheck.rest.ApiInterface;
import com.tetapdirumah.selfcheck.rest.ApiUtils;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PresenterResult implements ContractResult.Presenter {

    private IDataManager iDataManager;
    private ApiInterface mApi;
    private ContractResult.View view;

    public PresenterResult(IDataManager iDataManager, ContractResult.View view) {
        this.iDataManager = iDataManager;
        this.view = view;
    }

    @Override
    public void postData() {
        FormDiagnose diagnose = view.getDiagnose();
        mApi = ApiUtils.getAPIService();
        mApi.diagnose(diagnose)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ApiResponse<List<DataResponse>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ApiResponse<List<DataResponse>> listApiResponse) {
                        float covid = listApiResponse.get_item().get(0).get_covid();
                        float flu = listApiResponse.get_item().get(0).get_flu();
                        float cold = listApiResponse.get_item().get(0).get_cold();
                        iDataManager.updateCovid(String.valueOf(covid));
                        iDataManager.updateFlu(String.valueOf(flu));
                        iDataManager.updateCold(String.valueOf(cold));
                        view.initializeData();
                        if (covid < 20){
                            view.showMessage("ISOLASI MANDIRI");
                        } else if (covid < 60){
                            view.showMessage("DIANJURKAN UNTUK PERIKSA KE DOKTER DAN ISOLASI MANDIRI");
                        } else {
                            view.showMessage("SEGERA HUBUNGI LAYANAN 119 UNTUK PENANGANAN COVID 19");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
