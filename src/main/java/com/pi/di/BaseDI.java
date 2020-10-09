package com.pi.di;

import com.pi.applicationcore.interfaces.PiBusiness;

public class BaseDI {
    public BaseDI() {

    }

    private void init(){
        initBusiness();
//        initValidation();
//        initService();
    }

    private void initBusiness(){
//        PiBusiness pi = new PiBusiness()
    }

    public PiBusiness getPiBusiness(){

        return null;
    }

}
