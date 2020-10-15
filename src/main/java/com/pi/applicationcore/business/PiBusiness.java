package com.pi.applicationcore.business;

import com.pi.applicationcore.dto.PiRequest;
import com.pi.applicationcore.dto.PiResponseResult;
import com.pi.applicationcore.interfaces.PiCalculationBusiness;
import com.pi.applicationcore.interfaces.PiValidation;

public class PiBusiness implements com.pi.applicationcore.interfaces.PiBusiness {
    private final PiCalculationBusiness _piCalculationBusiness;
    private final PiValidation _piValidation;

    public PiBusiness(PiCalculationBusiness piCalculationBusiness, PiValidation piValidation) {
        _piCalculationBusiness = piCalculationBusiness;
        _piValidation = piValidation;
    }

    @Override
    public PiResponseResult exec(PiRequest request) {
        PiResponseResult res = _piValidation.validate(request);
        if (!res.hasError()) {
            res = _piCalculationBusiness.exec(request);
        }
        return res;
    }
}
