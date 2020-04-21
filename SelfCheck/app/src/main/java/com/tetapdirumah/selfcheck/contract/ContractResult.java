package com.tetapdirumah.selfcheck.contract;

import com.tetapdirumah.selfcheck.model.FormDiagnose;
import com.tetapdirumah.selfcheck.model.FormUpdate;

public class ContractResult {
    public interface Presenter{
        void postData();
        void updateData();
    }

    public interface View{
        void initializeData();
        void setId(String s);
        String id();
        FormDiagnose getDiagnose();
        FormUpdate formUpadate();
        void showMessage(String s);
        void btnShow();
        void goToHistory();
        void showLoadingAnimation();
        void disposeLoadingAnimation();
        void saveData(String nama, String covid, String flu, String cold, String date_created);
    }
}
