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


/**
 * The PiCalculationBusiness is an implementation for the PiCalculationBusinessLocal interface.
 */
public class PiCalculationBusiness implements PiCalculationBusinessLocal {
    
    /** The size of task. */
    private final int SIZE_OF_TASK = 1000000;
    
    /** The size of a mini calculate. */
    private final int SIZE_OF_A_MINI_CALCULATE = 100;

    /** The pi validation local. */
    private final PiValidationLocal piValidationLocal;
    
    /** The pi formula local. */
    private final PiFormulaLocal piFormulaLocal;

    /** The is stop. */
    private final AtomicBoolean isStop;
    
    /** The number calculated. */
    private long numberCalculated;

    /**
     * Instantiates a new pi calculation business.
     *
     * @param piValidationLocal the type pi validation
     * @param piFormulaLocal the algorithm of the formula
     */
    public PiCalculationBusiness(PiValidationLocal piValidationLocal, PiFormulaLocal piFormulaLocal) {
        this.piValidationLocal = piValidationLocal;
        this.piFormulaLocal = piFormulaLocal;
        this.isStop = new AtomicBoolean();
    }

    /**
     * Execute calculate for the pi request.
     *
     * @param request the request
     * @return the pi response result
     * @throws ExecutionException the execution exception
     * @throws InterruptedException the interrupted exception
     */
    @Override
    public PiResponseResult executeCalculate(PiRequest request) throws ExecutionException, InterruptedException {
        PiResponseResult piResponseResult = piValidationLocal.validate(request);
        if (!piResponseResult.hasError()) {
            piResponseResult = calculate(request);
        }
        return piResponseResult;
    }

    /**
     * Stop calculating when we don't want to continue calculate the number.
     */
    @Override
    public void stopCalculate() {
        isStop.set(true);
    }

    /**
     * Gets the number calculated.
     *
     * @return the number calculated
     */
    @Override
    public long getNumberCalculated() {
        return numberCalculated;
    }

    /**
     * Calculate the main logic.
     *
     * @param request the request
     * @return the pi response result
     * @throws ExecutionException the execution exception
     * @throws InterruptedException the interrupted exception
     */
    private PiResponseResult calculate(PiRequest request) throws ExecutionException, InterruptedException {
        ExecutorService executorService = createThreadPool();
        double result = execute(executorService, request.getValue());

        PiResponseResult piResponseResult = new PiResponseResult();
        piResponseResult.setValue(result);
        return piResponseResult;
    }

    /**
     * Creates the thread pool.
     *
     * @return the executor service
     */
    private ExecutorService createThreadPool(){
        int procNb = Runtime.getRuntime().availableProcessors();
        return Executors.newFixedThreadPool(procNb);
    }

    /**
     * Calculate the result of the number.
     *
     * @param executor the executor service
     * @param number the number
     * @return the result of the calculate
     * @throws InterruptedException the interrupted exception
     * @throws ExecutionException the execution exception
     */
    private double execute(ExecutorService executor, long number) throws InterruptedException, ExecutionException {
        List<Future<Double>> futures = new ArrayList<>();
        double currentResult = 0.0;

        for(long startIndex = 0; startIndex <= number && !isStop.get(); startIndex += SIZE_OF_TASK){
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

    /**
     * Gets the current result.
     *
     * @param futures the futures
     * @param currentResult the current result
     * @return the current result
     * @throws InterruptedException the interrupted exception
     * @throws ExecutionException the execution exception
     */
    private double getCurrentResult(List<Future<Double>> futures, double currentResult) throws InterruptedException, ExecutionException {
        for (Future<Double> future : futures) {
            currentResult = piFormulaLocal.calculateResult(currentResult, future.get());
        }
        return currentResult;
    }

    /**
     * Creates the task.
     *
     * @param startIndex the start index
     * @param number the number
     * @return the callable task
     */
    private Callable<Double> createTask(long startIndex, long number) {
        long endIndex = startIndex + SIZE_OF_TASK;
        if (endIndex > number) {
            endIndex = number + 1;
        }

        updateNumberCalculated(endIndex);
        return piFormulaLocal.createCallableTask(startIndex, endIndex);
    }

    /**
     * Update the number calculated.
     *
     * @param number the number
     */
    private void updateNumberCalculated(long number) {
        numberCalculated = number;
    }
}
