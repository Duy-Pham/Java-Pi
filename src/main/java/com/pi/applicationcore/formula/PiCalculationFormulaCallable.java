package com.pi.applicationcore.formula;

import java.util.concurrent.Callable;

public class PiCalculationFormulaCallable implements Callable<Double> {
    private final long start;
    private final long end;

    public PiCalculationFormulaCallable(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Double call() {
        double res = 0.0;
        for (long i = start; i < end; i++) {
            if (isEvenNumber(i)) {
                res += 1.0 / (2.0 * i + 1.0);
            } else {
                res += -1.0 / (2.0 * i + 1.0);
            }
        }

        return res;
    }

    private boolean isEvenNumber(long number) {
        return number % 2 == 0;
    }

}
