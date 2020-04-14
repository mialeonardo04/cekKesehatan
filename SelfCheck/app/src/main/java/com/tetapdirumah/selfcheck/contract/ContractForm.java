package com.tetapdirumah.selfcheck.contract;

import android.os.Bundle;

public class ContractForm {
    public interface Presenter{
        void initialize();
    }

    public interface View{
        void initializeFragment();
        void changePage(int id, boolean smoothScroll);
    }
}
