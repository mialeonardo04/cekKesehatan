package com.tetapdirumah.selfcheck.model;

import com.google.gson.annotations.SerializedName;

public class FormDiagnose {
    @SerializedName("nama")
    private String _nama;
    @SerializedName("no_telp")
    private String _telp;
    @SerializedName("usia")
    private String _usia;
    @SerializedName("asal_kota")
    private String _kota;
    @SerializedName("kecamatan")
    private String _kecataman;
    @SerializedName("koordinat")
    private String _koordinat;
    @SerializedName("batuk")
    private int _batuk;
    @SerializedName("demam")
    private int _demam;
    @SerializedName("meler")
    private int _hidung;
    @SerializedName("sakit_tenggorokan")
    private int _tenggorokan;
    @SerializedName("sesak_nafas")
    private int _sesak;
    @SerializedName("sakit_kepala")
    private int _kepala;
    @SerializedName("pegal")
    private int _pegal;
    @SerializedName("bersin")
    private int _bersin;
    @SerializedName("lelah")
    private int _lelah;
    @SerializedName("diare")
    private int _diare;

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

    public String get_kecataman() {
        return _kecataman;
    }

    public void set_kecataman(String _kecataman) {
        this._kecataman = _kecataman;
    }

    public String get_koordinat() {
        return _koordinat;
    }

    public void set_koordinat(String _koordinat) {
        this._koordinat = _koordinat;
    }

    public String get_kota() {
        return _kota;
    }

    public void set_kota(String _kota) {
        this._kota = _kota;
    }

    public int get_batuk() {
        return _batuk;
    }

    public void set_batuk(int _batuk) {
        this._batuk = _batuk;
    }

    public int get_demam() {
        return _demam;
    }

    public void set_demam(int _demam) {
        this._demam = _demam;
    }

    public int get_hidung() {
        return _hidung;
    }

    public void set_hidung(int _hidung) {
        this._hidung = _hidung;
    }

    public int get_tenggorokan() {
        return _tenggorokan;
    }

    public void set_tenggorokan(int _tenggorokan) {
        this._tenggorokan = _tenggorokan;
    }

    public int get_sesak() {
        return _sesak;
    }

    public void set_sesak(int _sesak) {
        this._sesak = _sesak;
    }

    public int get_kepala() {
        return _kepala;
    }

    public void set_kepala(int _kepala) {
        this._kepala = _kepala;
    }

    public int get_pegal() {
        return _pegal;
    }

    public void set_pegal(int _pegal) {
        this._pegal = _pegal;
    }

    public int get_bersin() {
        return _bersin;
    }

    public void set_bersin(int _bersin) {
        this._bersin = _bersin;
    }

    public int get_lelah() {
        return _lelah;
    }

    public void set_lelah(int _lelah) {
        this._lelah = _lelah;
    }

    public int get_diare() {
        return _diare;
    }

    public void set_diare(int _diare) {
        this._diare = _diare;
    }
}
