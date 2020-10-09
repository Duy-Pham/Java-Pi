package com.pi.applicationcore.business;

import com.pi.applicationcore.dto.PiRequest;
import com.pi.applicationcore.dto.PiResponseResult;
import com.pi.applicationcore.interfaces.PiCalculationService;
import com.pi.applicationcore.interfaces.PiValidation;

public class PiBusiness implements com.pi.applicationcore.interfaces.PiBusiness {
    private final PiCalculationService _piCalculation;
    private final PiValidation _piValidation;

    public PiBusiness(PiCalculationService piCalculation, PiValidation validationPi){
        _piCalculation = piCalculation;
        _piValidation = validationPi;
    }

    @Override
    public PiResponseResult exec(PiRequest request) {
        PiResponseResult res = _piValidation.validate(request);
        if(res.getError() == null){
            res = _piCalculation.exec(request);
        }
        return res;
    }
}
