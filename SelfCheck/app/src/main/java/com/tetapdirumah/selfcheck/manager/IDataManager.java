package com.tetapdirumah.selfcheck.manager;

public interface IDataManager {
    void createData(String nama, String kota);
    void updateNama(String s);
    void updateTelp(String s);
    void updateUsia(String s);
    void updateKota(String s);
    void updateKecamatan(String s);
    void updateLongitude(String s);
    void updateLatitude(String s);
    void updateBatuk(String s);
    void updateDemam(String s);
    void updateHidung(String s);
    void updateTenggorokan(String s);
    void updateSesak(String s);
    void updateKepala(String s);
    void updatePegal(String s);
    void updateBersin(String s);
    void updateLelah(String s);
    void updateDiare(String s);
    void updateCovid(String s);
    void updateFlu(String s);
    void updateCold(String s);
}
