package com.tetapdirumah.selfcheck.contract;

import com.tetapdirumah.selfcheck.sqlite.DataDiagnosa;

import java.util.List;

public class ContractRiwayat {

    public interface Presenter {
        void getData();
    }

    public interface View{
        void initializeData(List<DataDiagnosa> dataDiagnosas);
    }
}
