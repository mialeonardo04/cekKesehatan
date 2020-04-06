package com.tetapdirumah.selfcheck.contract;

import android.os.Bundle;

public class ContractFragmentForm {

    public interface Presenter{
        void storeData();
    }

    public interface View{
        void initializeDialog();
        void onItemSelected(String poin, String text);
        String store();
    }
}
