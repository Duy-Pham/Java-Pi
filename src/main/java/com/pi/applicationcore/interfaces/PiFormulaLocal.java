package com.pi.applicationcore.interfaces;

import com.pi.applicationcore.dto.PiArray;

import java.util.List;
import java.util.concurrent.Callable;

public interface PiFormulaLocal {
    List<Callable<Double>> createCallableTasks(List<PiArray> arrData);
    double calculate(List<Double> resultTasks);
}
