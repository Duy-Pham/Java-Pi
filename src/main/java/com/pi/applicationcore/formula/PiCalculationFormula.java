package com.pi.applicationcore.formula;

import com.pi.applicationcore.interfaces.PiFormulaLocal;

import java.util.concurrent.Callable;


public class PiCalculationFormula implements PiFormulaLocal {

    @Override
    public Callable<Double> createCallableTask(long startIndex, long size) {
        return new PiCalculationFormulaCallable(startIndex, size);
    }

    @Override
    public double calculateResult(double currentResult, double newResult) {
        if (currentResult == 0.0)
            currentResult = 1.0;

        return currentResult + newResult;
    }

}
