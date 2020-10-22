package com.pi.applicationcore.formula;

import com.pi.applicationcore.interfaces.PiFormulaLocal;

import java.util.concurrent.Callable;


/**
 * The PiCalculationFormula is an implementation for the PiFormulaLocal interface.
 */
public class PiCalculationFormula implements PiFormulaLocal {

	/**
     * Creates the callable task for the formula.
     *
     * @param startIndex the start index of the task 
     * @param size the size of the task
     * @return the callable task
     */
    @Override
    public Callable<Double> createCallableTask(long startIndex, long size) {
        return new PiCalculationFormulaCallable(startIndex, size);
    }

    /**
     * Calculate result for the formula.
     *
     * @param currentResult the current result
     * @param newResult the new result
     * @return the result
     */
    @Override
    public double calculateResult(double currentResult, double newResult) {
        if (currentResult == 0.0)
            currentResult = 1.0;

        return currentResult + newResult;
    }

}
