package com.pi.applicationcore.business;

import com.pi.applicationcore.dto.PiRequest;
import com.pi.applicationcore.dto.PiResponseResult;
import com.pi.applicationcore.interfaces.IPiBusiness;
import com.pi.applicationcore.interfaces.IPiCalculationService;
import com.pi.applicationcore.interfaces.IPiValidation;

public class PiBusiness implements IPiBusiness {
    private final IPiCalculationService _piCalculation;
    private final IPiValidation _piValidation;

    public PiBusiness(IPiCalculationService piCalculation, IPiValidation piValidation){
        _piCalculation = piCalculation;
        _piValidation = piValidation;
    }

    @Override
    public PiResponseResult exec(PiRequest request) {
        PiResponseResult res = _piValidation.validate(request);
        if(res.getStatus() == false){
            res = _piCalculation.exec(request);
        }
        return res;
    }
}
