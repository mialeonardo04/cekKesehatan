package com.tetapdirumah.selfcheck.manager;

import android.content.Context;

public class DataManagerWrapper implements IDataManager {

    private Context context;
    DataManager dataManager;

    public DataManagerWrapper(Context context) {
        this.context = context;
        this.dataManager = new DataManager(context);
    }

    @Override
    public void createData(String nama, String kota) {
        dataManager.createData(nama, kota);
    }

    @Override
    public void updateBatuk(String s) {
        dataManager.updateBatuk(s);
    }

    @Override
    public void updateDemam(String s) {
        dataManager.updateDemam(s);
    }

    @Override
    public void updateHidung(String s) {
        dataManager.updateHidung(s);
    }

    @Override
    public void updateTenggorokan(String s) {
        dataManager.updateTenggorokan(s);
    }

    @Override
    public void updateSesak(String s) {
        dataManager.updateSesak(s);
    }

    @Override
    public void updateKepala(String s) {
        dataManager.updateKepala(s);
    }

    @Override
    public void updatePegal(String s) {
        dataManager.updatePegal(s);
    }

    @Override
    public void updateBersin(String s) {
        dataManager.updateBersin(s);
    }

    @Override
    public void updateLelah(String s) {
        dataManager.updateLelah(s);
    }

    @Override
    public void updateDiare(String s) {
        dataManager.updateDiare(s);
    }
}
