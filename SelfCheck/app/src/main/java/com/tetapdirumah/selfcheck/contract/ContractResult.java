package com.tetapdirumah.selfcheck.contract;

import com.tetapdirumah.selfcheck.model.FormDiagnose;

public class ContractResult {
    public interface Presenter{
        void postData();
    }

    public interface View{
        void initializeData();
        FormDiagnose getDiagnose();
        void showMessage(String s);
        void showLoadingAnimation();
        void disposeLoadingAnimation();
    }
}
