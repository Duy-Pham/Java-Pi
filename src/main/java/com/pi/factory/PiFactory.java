package com.pi.factory;

import com.pi.applicationcore.interfaces.PiBusiness;
import com.pi.applicationcore.interfaces.PiCalculationService;
import com.pi.applicationcore.interfaces.PiValidation;

public class PiFactory {
    private PiBusiness _piBusiness;
    private PiValidation _piValidation;
    private PiCalculationService _piCalculationService;

    public PiFactory() {
        init();
    }

    private void init() {
        _piValidation = new com.pi.applicationcore.validation.PiValidation();
        _piCalculationService = new com.pi.applicationcore.services.PiCalculationService();

        _piBusiness = new com.pi.applicationcore.business.PiBusiness(_piCalculationService, _piValidation);
    }

    public PiBusiness getPiBusiness() {
        return _piBusiness;
    }
    public PiValidation getPiValidation() {
        return _piValidation;
    }
    public PiCalculationService getPiCalculationService() {
        return _piCalculationService;
    }
}
