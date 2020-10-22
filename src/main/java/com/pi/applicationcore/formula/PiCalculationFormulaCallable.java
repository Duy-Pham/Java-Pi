package com.pi.applicationcore.formula;

import java.util.concurrent.Callable;

/**
 * The PiCalculationFormulaCallable represents how the formula is calculated.
 */
public class PiCalculationFormulaCallable implements Callable<Double> {
    
    /** The start. */
    private final long start;
    
    /** The end. */
    private final long end;

    /**
     * Instantiates a new pi calculation formula callable.
     *
     * @param start the start index to calculate
     * @param end the end index to calculate
     */
    public PiCalculationFormulaCallable(long start, long end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Calculate the formula.
     *
     * @return the double
     */
    @Override
    public Double call() {
        double res = 0.0;
        for (long i = start; i < end; i++) {
            if (isEvenNumber(i)) {
                res += 1.0 / ((2.0 * i) + 1.0);
            } else {
                res += -1.0 / ((2.0 * i) + 1.0);
            }
        }

        return res;
    }

    /**
     * Checks if is even number.
     *
     * @param number the number
     * @return true, if is even number
     */
    private boolean isEvenNumber(long number) {
        return number % 2 == 0;
    }

}
