package com.tetapdirumah.selfcheck.manager;

import android.content.Context;
import android.content.SharedPreferences;

public class DataManager {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    private Context _context;

    private int PRIVATE_MODE = 0;

    private static final String PREFER_NAME = "dataManager";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_TELP = "no_telp";
    private static final String KEY_USIA = "usia";
    private static final String KEY_KOTA = "kota";
    private static final String KEY_KECAMATAN = "kecamatan";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_BATUK = "batuk";
    private static final String KEY_DEMAM = "demam";
    private static final String KEY_HIDUNG = "hidung";
    private static final String KEY_TENGGOROKAN = "tenggorokan";
    private static final String KEY_SESAK = "sesak";
    private static final String KEY_KEPALA = "kepala";
    private static final String KEY_PEGAL = "pegal";
    private static final String KEY_BERSIN = "bersin";
    private static final String KEY_LELAH = "lelah";
    private static final String KEY_DIARE = "diare";
    private static final String KEY_COVID = "covid";
    private static final String KEY_FLU = "flu";
    private static final String KEY_COLD = "cold";

    public DataManager(Context _context) {
        this._context = _context;
        preferences = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public void createData(String nama, String kota){
        editor.putString(KEY_NAMA, nama);
        editor.putString(KEY_TELP, "0");
        editor.putString(KEY_USIA, "0");
        editor.putString(KEY_KOTA, kota);
        editor.putString(KEY_KECAMATAN, "0");
        editor.putString(KEY_LONGITUDE, "0");
        editor.putString(KEY_LATITUDE, "0");
        editor.putString(KEY_BATUK, "0");
        editor.putString(KEY_DEMAM, "0");
        editor.putString(KEY_HIDUNG, "0");
        editor.putString(KEY_TENGGOROKAN, "0");
        editor.putString(KEY_SESAK, "0");
        editor.putString(KEY_KEPALA, "0");
        editor.putString(KEY_PEGAL, "0");
        editor.putString(KEY_BERSIN, "0");
        editor.putString(KEY_LELAH, "0");
        editor.putString(KEY_DIARE, "0");
        editor.putString(KEY_COVID, "0");
        editor.putString(KEY_FLU, "0");
        editor.putString(KEY_COLD, "");
        editor.commit();
    }

    public void updateNama(String s){
        editor.putString(KEY_NAMA, s);
        editor.apply();
    }

    public void updateTelp(String s){
        editor.putString(KEY_TELP, s);
        editor.apply();
    }

    public void updateUsia(String s){
        editor.putString(KEY_USIA, s);
        editor.apply();
    }

    public void updateKota(String s){
        editor.putString(KEY_KOTA, s);
        editor.apply();
    }

    public void updateKecamatan(String s){
        editor.putString(KEY_KECAMATAN, s);
        editor.apply();
    }

    public void updateLongitude(String s){
        editor.putString(KEY_LONGITUDE, s);
        editor.apply();
    }

    public void updateLatitude(String s){
        editor.putString(KEY_LATITUDE, s);
        editor.apply();
    }

    public void updateCovid(String s){
        editor.putString(KEY_COVID, s);
        editor.apply();
    }

    public void updateFlu(String s){
        editor.putString(KEY_FLU, s);
        editor.apply();
    }

    public void updateCold(String s){
        editor.putString(KEY_COLD, s);
        editor.apply();
    }

    public void updateBatuk(String s){
        editor.putString(KEY_BATUK, s);
        editor.apply();
    }

    public void updateDemam(String s){
        editor.putString(KEY_DEMAM, s);
        editor.apply();
    }

    public void updateHidung(String s){
        editor.putString(KEY_HIDUNG, s);
        editor.apply();
    }

    public void updateTenggorokan(String s){
        editor.putString(KEY_TENGGOROKAN, s);
        editor.apply();
    }

    public void updateSesak(String s){
        editor.putString(KEY_SESAK, s);
        editor.apply();
    }

    public void updateKepala(String s){
        editor.putString(KEY_KEPALA, s);
        editor.apply();
    }

    public void updatePegal(String s){
        editor.putString(KEY_PEGAL, s);
        editor.apply();
    }

    public void updateBersin(String s){
        editor.putString(KEY_BERSIN, s);
        editor.apply();
    }

    public void updateLelah(String s){
        editor.putString(KEY_LELAH, s);
        editor.apply();
    }

    public void updateDiare(String s){
        editor.putString(KEY_DIARE, s);
        editor.apply();
    }

    public void clear(){
        editor.clear();
        editor.commit();
    }
}
