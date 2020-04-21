package com.tetapdirumah.selfcheck.model;

import com.google.gson.annotations.SerializedName;

public class DataResponse {
    @SerializedName("id")
    String _id;
    @SerializedName("nama")
    private String _nama;
    @SerializedName("usia")
    private String _usia;
    @SerializedName("asal_kota")
    private String _kota;
    @SerializedName("kecamatan")
    private String _kecamatan;
    @SerializedName("covid_19")
    private float _covid;
    @SerializedName("flu")
    private float _flu;
    @SerializedName("cold")
    private float _cold;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_nama() {
        return _nama;
    }

    public void set_nama(String _nama) {
        this._nama = _nama;
    }

    public String get_usia() {
        return _usia;
    }

    public void set_usia(String _usia) {
        this._usia = _usia;
    }

    public String get_kecamatan() {
        return _kecamatan;
    }

    public void set_kecamatan(String _kecamatan) {
        this._kecamatan = _kecamatan;
    }

    public String get_kota() {
        return _kota;
    }

    public void set_kota(String _kota) {
        this._kota = _kota;
    }

    public float get_covid() {
        return _covid;
    }

    public void set_covid(float _covid) {
        this._covid = _covid;
    }

    public float get_flu() {
        return _flu;
    }

    public void set_flu(float _flu) {
        this._flu = _flu;
    }

    public float get_cold() {
        return _cold;
    }

    public void set_cold(float _cold) {
        this._cold = _cold;
    }
}
