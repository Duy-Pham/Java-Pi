package com.pi.di;

import com.pi.applicationcore.business.PiBusiness;
import com.pi.applicationcore.business.PiCalculationBusiness;
import com.pi.applicationcore.interfaces.PiBusinessLocal;
import com.pi.applicationcore.interfaces.PiCalculationBusinessLocal;
import com.pi.applicationcore.interfaces.PiValidationLocal;
import com.pi.applicationcore.validation.PiValidation;

public class PiDI {
    private PiBusinessLocal _piBusinessLocal;
    private PiValidationLocal _piValidationLocal;
    private PiCalculationBusinessLocal _piCalculationBusinessLocal;

    public PiDI() {
        init();
    }

    private void init() {
        _piValidationLocal = new PiValidation();
        _piCalculationBusinessLocal = new PiCalculationBusiness();

//        _piBusinessLocal = new PiBusiness(_piCalculationBusinessLocal, _piValidationLocal);
    }

    public PiBusinessLocal getPiBusiness() {
        return _piBusinessLocal;
    }
    public PiValidationLocal getPiValidation() {
        return _piValidationLocal;
    }
    public PiCalculationBusinessLocal getPiCalculationService() {
        return _piCalculationBusinessLocal;
    }
}
