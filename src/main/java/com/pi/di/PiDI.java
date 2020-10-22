package com.pi.di;

import com.pi.applicationcore.business.PiCalculationBusiness;
import com.pi.applicationcore.formula.PiCalculationFormula;
import com.pi.applicationcore.interfaces.PiCalculationBusinessLocal;
import com.pi.applicationcore.interfaces.PiFormulaLocal;
import com.pi.applicationcore.interfaces.PiValidationLocal;
import com.pi.applicationcore.validation.PiValidation;

public class PiDI {
    private PiCalculationBusinessLocal piCalculationBusinessLocal;
    private PiValidationLocal piValidationLocal;
    private PiFormulaLocal piFormulaLocal;

    public PiDI() {
        init();
    }

    private void init() {
        piFormulaLocal = new PiCalculationFormula();
        piValidationLocal = new PiValidation();

        piCalculationBusinessLocal = new PiCalculationBusiness(piValidationLocal, piFormulaLocal);
    }

    public PiCalculationBusinessLocal getPiBusiness() {
        return piCalculationBusinessLocal;
    }
    public PiValidationLocal getPiValidation() {
        return piValidationLocal;
    }
}
