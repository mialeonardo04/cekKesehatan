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
    public void storeData() {
        iDataManager.updateBatuk(view.store());
        Log.d(TAG, "batuk: stored= " + view.store());
    }
}
