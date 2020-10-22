package com.pi.applicationcore.business;

import com.pi.applicationcore.dto.PiRequest;
import com.pi.applicationcore.dto.PiResponseResult;
import com.pi.applicationcore.interfaces.PiCalculationBusinessLocal;
import com.pi.applicationcore.interfaces.PiFormulaLocal;
import com.pi.applicationcore.interfaces.PiValidationLocal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

public class PiCalculationBusiness implements PiCalculationBusinessLocal {
    private final int SIZE_OF_TASK = 10000;
    private final int SIZE_OF_A_MINI_CALCULATE = 50;

    private final PiValidationLocal piValidationLocal;
    private final PiFormulaLocal piFormulaLocal;
    private ExecutorService executorService;
    private final AtomicBoolean isStop;
    private long currentNumber;

    public PiCalculationBusiness(PiValidationLocal piValidationLocal, PiFormulaLocal piFormulaLocal) {
        this.piValidationLocal = piValidationLocal;
        this.piFormulaLocal = piFormulaLocal;
        this.isStop = new AtomicBoolean();
    }

    @Override
    public PiResponseResult execCalculate(PiRequest request) throws ExecutionException, InterruptedException {
        PiResponseResult piResponseResult = piValidationLocal.validate(request);
        if (!piResponseResult.hasError()) {
            piResponseResult = calculate(request);
        }
        return piResponseResult;
    }

    @Override
    public void stopCalculate() {
        isStop.set(true);
    }

    @Override
    public long getCurrentNumber() {
        return currentNumber;
    }

    private PiResponseResult calculate(PiRequest request) throws ExecutionException, InterruptedException {
        executorService = createThreadPool();
        double result = executeAll(executorService, request.getValue());

        PiResponseResult piResponseResult = new PiResponseResult();
        piResponseResult.setValue(result);
        return piResponseResult;
    }

    private ExecutorService createThreadPool(){
        int procNb = Runtime.getRuntime().availableProcessors();
        return Executors.newFixedThreadPool(procNb);
    }

    private double executeAll(ExecutorService executor, long number) throws InterruptedException, ExecutionException {
        List<Future<Double>> futures = new ArrayList<>();
        double currentResult = 0;

        for(long startIndex = 0; startIndex < number && !isStop.get(); startIndex += SIZE_OF_TASK){
            Callable<Double> task = createTask(startIndex, number);
            futures.add(executor.submit(task));

            if (futures.size() >= SIZE_OF_A_MINI_CALCULATE){
                currentResult = getCurrentResult(futures, currentResult);
                futures.clear();
            }
        }

        currentResult = getCurrentResult(futures, currentResult);
        return currentResult;
    }

    private double getCurrentResult(List<Future<Double>> futures, double currentResult) throws InterruptedException, ExecutionException {
        for (Future<Double> future : futures) {
            currentResult = piFormulaLocal.calculateResult(currentResult, future.get());
        }
        return currentResult;
    }

    private Callable<Double> createTask(long startIndex, long number) {
        long endIndex = startIndex + SIZE_OF_TASK;
        if (endIndex > number) {
            endIndex = number;
        }

        updateCurrentNumberCalculated(endIndex);
        return piFormulaLocal.createCallableTask(startIndex, endIndex);
    }

    private void updateCurrentNumberCalculated(long number) {
        currentNumber = number;
    }
}
