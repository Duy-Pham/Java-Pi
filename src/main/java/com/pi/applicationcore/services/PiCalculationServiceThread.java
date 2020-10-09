package com.pi.applicationcore.services;

import java.util.concurrent.Callable;

public class PiCalculationServiceThread implements Callable<Double> {
    private final long _start;
    private final int _step;
    private final long _number;

    public PiCalculationServiceThread(long start, int step, long number) {
        _start = start;
        _step = step;
        _number = number;
    }

    @Override
    public Double call() throws Exception {
        double res = 0.0;
        for (long i = _start; i < _number; i += _step) {
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
