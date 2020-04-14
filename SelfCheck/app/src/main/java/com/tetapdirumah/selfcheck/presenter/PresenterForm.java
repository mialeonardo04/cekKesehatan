package com.tetapdirumah.selfcheck.presenter;

import com.tetapdirumah.selfcheck.contract.ContractForm;

public class PresenterForm implements ContractForm.Presenter {

    private ContractForm.View view;

    public PresenterForm(ContractForm.View view) {
        this.view = view;
    }

    @Override
    public void initialize() {
        view.initializeFragment();
    }
}
