package com.tetapdirumah.selfcheck.model;

import com.google.gson.annotations.SerializedName;

public class FormUpdate {
    @SerializedName("nama")
    String _nama;
    @SerializedName("no_telp")
    String _telp;
    @SerializedName("usia")
    String _usia;
    @SerializedName("asal_kota")
    String _kota;
    @SerializedName("kecamatan")
    String _kecamatan;
    @SerializedName("koordinat")
    String _koordinat;

    public String get_nama() {
        return _nama;
    }

    public void set_nama(String _nama) {
        this._nama = _nama;
    }

    public String get_telp() {
        return _telp;
    }

    public void set_telp(String _telp) {
        this._telp = _telp;
    }

    public String get_usia() {
        return _usia;
    }

    public void set_usia(String _usia) {
        this._usia = _usia;
    }

    public String get_kota() {
        return _kota;
    }

    public void set_kota(String _kota) {
        this._kota = _kota;
    }

    public String get_kecamatan() {
        return _kecamatan;
    }

    public void set_kecamatan(String _kecamatan) {
        this._kecamatan = _kecamatan;
    }

    public String get_koordinat() {
        return _koordinat;
    }

    public void set_koordinat(String _koordinat) {
        this._koordinat = _koordinat;
    }
}
