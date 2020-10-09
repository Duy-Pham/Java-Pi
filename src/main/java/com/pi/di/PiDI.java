package com.pi.di;

import com.pi.applicationcore.interfaces.PiBusiness;
import com.pi.applicationcore.interfaces.PiCalculationService;
import com.pi.applicationcore.interfaces.PiValidation;

public class PiDI {
    private PiBusiness _piBusiness;

    public PiDI() {
        init();
    }

    private void init() {
        PiValidation piValidation = new com.pi.applicationcore.validation.PiValidation();
        PiCalculationService piCalculationService = new com.pi.applicationcore.services.PiCalculationService();

        _piBusiness = new com.pi.applicationcore.business.PiBusiness(piCalculationService, piValidation);
    }

    public PiBusiness getPiBusiness() {
        return _piBusiness;
    }

}
