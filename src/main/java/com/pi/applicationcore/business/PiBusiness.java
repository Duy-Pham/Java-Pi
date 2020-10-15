package com.pi.applicationcore.business;

import com.pi.applicationcore.dto.PiRequest;
import com.pi.applicationcore.dto.PiResponseResult;
import com.pi.applicationcore.interfaces.PiBusinessLocal;
import com.pi.applicationcore.interfaces.PiCalculationBusinessLocal;
import com.pi.applicationcore.interfaces.PiValidationLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
//@EJB(name="java:global:/MYSTUFF", beanInterface=PiBusinessLocal.class)
public class PiBusiness implements com.pi.applicationcore.interfaces.PiBusinessLocal {
//    private final PiCalculationBusinessLocal _piCalculationBusinessLocal;
//    private final PiValidationLocal _piValidationLocal;

//    public PiBusiness(PiCalculationBusinessLocal piCalculationBusinessLocal, PiValidationLocal piValidationLocal) {
//        _piCalculationBusinessLocal = piCalculationBusinessLocal;
//        _piValidationLocal = piValidationLocal;
//    }

    @Override
    public PiResponseResult exec(PiRequest request) {
//        PiResponseResult res = _piValidationLocal.validate(request);
//        if (!res.hasError()) {
//            res = _piCalculationBusinessLocal.exec(request);
//        }
//        return res;
        return null;
    }
}
