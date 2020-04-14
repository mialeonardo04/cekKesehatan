package com.tetapdirumah.selfcheck.contract;

public class ContractHome {

    public interface Presenter{}

    public interface View{
        void speedDial();
        void goToActivity();
        void call();
        void credit();
        void browser();
        boolean checkPermission();
        void requestPermission();
        boolean isLocationEnabled();
        void getLocation();
    }
}
