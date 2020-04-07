package com.tetapdirumah.selfcheck.presenter;

import android.util.Log;

import com.tetapdirumah.selfcheck.contract.ContractFragmentForm;
import com.tetapdirumah.selfcheck.manager.DataManagerWrapper;
import com.tetapdirumah.selfcheck.manager.IDataManager;

import static android.content.ContentValues.TAG;

public class PresenterBatukFragment implements ContractFragmentForm.Presenter {

    private ContractFragmentForm.View view;
    private IDataManager iDataManager;

    public PresenterBatukFragment(ContractFragmentForm.View view, IDataManager iDataManager) {
        this.view = view;
        this.iDataManager = iDataManager;
    }


    @Override
    public void updateBatuk() {
        iDataManager.updateBatuk(view.store());
        Log.d(TAG, "batuk: stored= " + view.store());
    }

    @Override
    public void updateDemam() {
        iDataManager.updateDemam(view.store());
        Log.d(TAG, "demam: stored= " + view.store());
    }

    @Override
    public void updateHidung() {
        iDataManager.updateHidung(view.store());
        Log.d(TAG, "hidung: stored= " + view.store());
    }

    @Override
    public void updateTenggorokan() {
        iDataManager.updateTenggorokan(view.store());
        Log.d(TAG, "tenggorokan: stored= " + view.store());
    }

    @Override
    public void updateSesak() {
        iDataManager.updateSesak(view.store());
        Log.d(TAG, "sesak: stored= " + view.store());
    }

    @Override
    public void updateKepala() {
        iDataManager.updateKepala(view.store());
        Log.d(TAG, "kepala: stored= " + view.store());
    }

    @Override
    public void updatePegal() {
        iDataManager.updatePegal(view.store());
        Log.d(TAG, "pegal: stored= " + view.store());
    }

    @Override
    public void updateBersin() {
        iDataManager.updateBersin(view.store());
        Log.d(TAG, "bersin: stored= " + view.store());
    }

    @Override
    public void updateLelah() {
        iDataManager.updateLelah(view.store());
        Log.d(TAG, "lelah: stored= " + view.store());
    }

    @Override
    public void updateDiare() {
        iDataManager.updateDiare(view.store());
        Log.d(TAG, "diare: stored= " + view.store());
    }
}
