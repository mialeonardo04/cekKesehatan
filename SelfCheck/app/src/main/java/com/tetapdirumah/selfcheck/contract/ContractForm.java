package com.tetapdirumah.selfcheck.contract;

import android.os.Bundle;

public class ContractForm {
    public interface Presenter{}

    public interface View{
        public void initializeFragment();
        public void changePage(int id, boolean smoothScroll);
    }
}
