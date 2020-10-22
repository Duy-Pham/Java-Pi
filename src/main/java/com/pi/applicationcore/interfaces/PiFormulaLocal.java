package com.pi.applicationcore.interfaces;

import java.util.concurrent.Callable;

public interface PiFormulaLocal {
    Callable<Double> createCallableTask(long startIndex, long size);
    double calculateResult(double currentResult, double newResult);
}
