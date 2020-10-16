package com.pi.di;

import com.pi.applicationcore.business.PiBusiness;
import com.pi.applicationcore.formula.PiFormula;
import com.pi.applicationcore.interfaces.PiBusinessLocal;
import com.pi.applicationcore.interfaces.PiFormulaLocal;
import com.pi.applicationcore.interfaces.PiValidationLocal;
import com.pi.applicationcore.validation.PiValidation;

public class PiDI {
    private PiBusinessLocal _piBusinessLocal;
    private PiValidationLocal _piValidationLocal;
    private PiFormulaLocal _piFormulaLocal;

    public PiDI() {
        init();
    }

    private void init() {
        _piFormulaLocal = new PiFormula();
        _piValidationLocal = new PiValidation();

        _piBusinessLocal = new PiBusiness(_piValidationLocal, _piFormulaLocal);
    }

    public PiBusinessLocal getPiBusiness() {
        return _piBusinessLocal;
    }
    public PiValidationLocal getPiValidation() {
        return _piValidationLocal;
    }
}
