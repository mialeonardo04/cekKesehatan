package com.tetapdirumah.selfcheck.presenter;

import com.tetapdirumah.selfcheck.contract.ContractRiwayat;
import com.tetapdirumah.selfcheck.sqlite.DataDiagnosa;
import com.tetapdirumah.selfcheck.sqlite.HandlerDiagnosa;

import java.util.ArrayList;
import java.util.List;

public class PresenterRiwayat implements ContractRiwayat.Presenter {

    private ContractRiwayat.View view;
    private List<DataDiagnosa> mData;
    private HandlerDiagnosa hData;

    public PresenterRiwayat(ContractRiwayat.View view, HandlerDiagnosa hData) {
        this.view = view;
        this.mData = new ArrayList<>();
        this.view.initializeData(mData);
        this.hData = hData;
    }

    @Override
    public void getData() {
        mData.addAll(hData.getData());
    }
}
