package com.tetapdirumah.selfcheck.contract;

public class ContractFormIdentitas {

    public interface Presenter{
        void data();
    }

    public interface View{
        void nextPage();
        String nama();
        String kota();
    }
}
