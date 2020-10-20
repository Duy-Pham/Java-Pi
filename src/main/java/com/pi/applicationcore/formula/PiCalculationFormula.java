package com.pi.applicationcore.formula;

import com.pi.applicationcore.dto.PiArray;
import com.pi.applicationcore.interfaces.PiFormulaLocal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class PiCalculationFormula implements PiFormulaLocal {

    @Override
    public List<Callable<Double>> createCallableTasks(List<PiArray> arrData) {
        List<Callable<Double>> tasks = new ArrayList<>();

        for(var item : arrData){
            tasks.add(new PiCalculationFormulaThread(item.getStart(), 1, item.getEnd()));
        }

        return tasks;
    }

    @Override
    public double calculate(List<Double> resultTasks) {
        double sum = resultTasks
                .stream()
                .reduce(0.0, Double::sum) + 1;

        return sum;
    }

}
