package com.pi.di;

import com.pi.applicationcore.interfaces.PiBusiness;
import com.pi.applicationcore.interfaces.PiCalculationBusiness;
import com.pi.applicationcore.interfaces.PiValidation;

public class PiDI {
    private PiBusiness _piBusiness;
    private PiValidation _piValidation;
    private PiCalculationBusiness _piCalculationBusiness;

    public PiDI() {
        init();
    }

    private void init() {
        _piValidation = new com.pi.applicationcore.validation.PiValidation();
        _piCalculationBusiness = new com.pi.applicationcore.business.PiCalculationBusiness();

        _piBusiness = new com.pi.applicationcore.business.PiBusiness(_piCalculationBusiness, _piValidation);
    }

    public PiBusiness getPiBusiness() {
        return _piBusiness;
    }
    public PiValidation getPiValidation() {
        return _piValidation;
    }
    public PiCalculationBusiness getPiCalculationService() {
        return _piCalculationBusiness;
    }
}
