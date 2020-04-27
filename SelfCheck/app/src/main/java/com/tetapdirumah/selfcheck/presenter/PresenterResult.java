package com.tetapdirumah.selfcheck.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.tetapdirumah.selfcheck.contract.ContractResult;
import com.tetapdirumah.selfcheck.manager.IDataManager;
import com.tetapdirumah.selfcheck.model.ApiResponse;
import com.tetapdirumah.selfcheck.model.DataResponse;
import com.tetapdirumah.selfcheck.model.FormDiagnose;
import com.tetapdirumah.selfcheck.model.FormUpdate;
import com.tetapdirumah.selfcheck.rest.ApiInterface;
import com.tetapdirumah.selfcheck.rest.ApiUtils;
import com.tetapdirumah.selfcheck.sqlite.DataDiagnosa;
import com.tetapdirumah.selfcheck.sqlite.HandlerDiagnosa;

import java.util.Date;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class PresenterResult implements ContractResult.Presenter {

    private IDataManager iDataManager;
    private ApiInterface mApi;
    private ContractResult.View view;
    private HandlerDiagnosa handlerDiagnosa;
    private Context context;

    public PresenterResult(Context context, IDataManager iDataManager, ContractResult.View view) {
        this.context = context;
        this.iDataManager = iDataManager;
        this.view = view;
        this.handlerDiagnosa = new HandlerDiagnosa(context);
    }

    @Override
    public void postData() {
        view.showLoadingAnimation();
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
                        String nama = listApiResponse.get_item().get(0).get_nama();
                        float covid = listApiResponse.get_item().get(0).get_covid();
                        float flu = listApiResponse.get_item().get(0).get_flu();
                        float cold = listApiResponse.get_item().get(0).get_cold();
                        iDataManager.updateCovid(String.valueOf(covid));
                        iDataManager.updateFlu(String.valueOf(flu));
                        iDataManager.updateCold(String.valueOf(cold));
                        view.initializeData();
                        if (covid < 10){
                            view.showMessage("");
                        } else if (covid < 50){
                            view.showMessage("");
                        } else {
                            view.btnShow();
                            view.showMessage("SEGERA HUBUNGI LAYANAN FASILITAS KESEHATAN DI DAERAH ANDA UNTUK PENANGANAN COVID 19 ATAU LANGSUNG KE RUMAH SAKIT RUJUKAN COVID 19");
                        }
                        view.setId(listApiResponse.get_item().get(0).get_id());
//                        view.disposeLoadingAnimation();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
//                        view.disposeLoadingAnimation();
                    }
                });
    }

    @Override
    public void updateData() {
        FormUpdate formUpdate = view.formUpadate();
        mApi = ApiUtils.getAPIService();
        mApi.update(view.id(), formUpdate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DataResponse dataResponse) {
                        view.goToHistory();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
