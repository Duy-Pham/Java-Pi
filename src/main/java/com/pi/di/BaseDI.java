package com.pi.di;

import com.pi.applicationcore.business.PiBusiness;
import com.pi.applicationcore.interfaces.IPiBusiness;

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

    public IPiBusiness getPiBusiness(){

        return null;
    }

}
