package com.tetapdirumah.selfcheck.presenter;

import com.tetapdirumah.selfcheck.contract.ContractForm;
import com.tetapdirumah.selfcheck.contract.ContractFormIdentitas;
import com.tetapdirumah.selfcheck.manager.DataManager;
import com.tetapdirumah.selfcheck.manager.IDataManager;

public class PresenterFormIdentitas implements ContractFormIdentitas.Presenter {

    private ContractFormIdentitas.View view;
    private IDataManager dataManager;

    public PresenterFormIdentitas(ContractFormIdentitas.View view, IDataManager dataManager) {
        this.view = view;
        this.dataManager = dataManager;
    }

    @Override
    public void data() {
        dataManager.createData(view.nama(), view.kota());
    }
}
