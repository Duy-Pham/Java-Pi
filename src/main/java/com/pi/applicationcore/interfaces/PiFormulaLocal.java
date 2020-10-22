package com.pi.applicationcore.interfaces;

import java.util.concurrent.Callable;

/**
 * The Interface PiFormulaLocal represents the actions supported for the formula.
 */
public interface PiFormulaLocal {
    
    /**
     * Creates the callable task for the formula.
     *
     * @param startIndex the start index of the task 
     * @param size the size of the task
     * @return the callable task
     */
    Callable<Double> createCallableTask(long startIndex, long size);
    
    /**
     * Calculate result for the formula.
     *
     * @param currentResult the current result
     * @param newResult the new result
     * @return the result
     */
    double calculateResult(double currentResult, double newResult);
}
