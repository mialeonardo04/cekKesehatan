package com.tetapdirumah.selfcheck.contract;

import android.os.Bundle;

import com.rey.material.widget.Button;

import java.util.List;

public class ContractFragmentForm {

    public interface Presenter{
        void updateBatuk();
        void updateDemam();
        void updateHidung();
        void updateTenggorokan();
        void updateSesak();
        void updateKepala();
        void updatePegal();
        void updateBersin();
        void updateLelah();
        void updateDiare();
    }

    public interface View{
        void initializeDialog();
        void onItemSelected(String poin, String text);
        String store();
        void log();
    }
}
