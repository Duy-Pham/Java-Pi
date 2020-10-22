package com.pi.di;

import com.pi.applicationcore.business.PiCalculationBusiness;
import com.pi.applicationcore.formula.PiCalculationFormula;
import com.pi.applicationcore.interfaces.PiCalculationBusinessLocal;
import com.pi.applicationcore.interfaces.PiFormulaLocal;
import com.pi.applicationcore.interfaces.PiValidationLocal;
import com.pi.applicationcore.validation.PiValidation;

/**
 * The PiDI represents the setup configuration of the system.
 */
public class PiDI {
    
    /** The pi calculation business local. */
    private PiCalculationBusinessLocal piCalculationBusinessLocal;
    
    /** The pi validation local. */
    private PiValidationLocal piValidationLocal;
    
    /** The pi formula local. */
    private PiFormulaLocal piFormulaLocal;

    /**
     * Instantiates a new pi DI.
     */
    public PiDI() {
        init();
    }

    /**
     * Inits all service for the system.
     */
    private void init() {
        piFormulaLocal = new PiCalculationFormula();
        piValidationLocal = new PiValidation();

        piCalculationBusinessLocal = new PiCalculationBusiness(piValidationLocal, piFormulaLocal);
    }

    /**
     * Gets the pi business.
     *
     * @return the pi business
     */
    public PiCalculationBusinessLocal getPiBusiness() {
        return piCalculationBusinessLocal;
    }
    
    /**
     * Gets the pi validation.
     *
     * @return the pi validation
     */
    public PiValidationLocal getPiValidation() {
        return piValidationLocal;
    }
}
