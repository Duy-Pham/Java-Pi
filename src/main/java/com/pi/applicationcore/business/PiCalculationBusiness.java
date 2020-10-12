package com.pi.applicationcore.business;

import com.pi.applicationcore.dto.PiRequest;
import com.pi.applicationcore.dto.PiResponseResult;
import com.pi.applicationcore.thread.PiCalculationThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PiCalculationBusiness implements com.pi.applicationcore.interfaces.PiCalculationBusiness {

    @Override
    public PiResponseResult exec(PiRequest request) {
        PiResponseResult piResponseResult = new PiResponseResult();

        int procNb = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(procNb);

        ArrayList<PiCalculationThread> piCalculationThreads = new ArrayList<PiCalculationThread>();

        for (int i = 0; i < procNb; i++) {
            piCalculationThreads.add(new PiCalculationThread(i + 1, procNb, request.getValue()));
        }

        try {
            double sum = executeAll(piCalculationThreads, executor)
                    .stream()
                    .reduce(0.0, Double::sum) + 1;

            piResponseResult.setValue(sum);

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (ExecutionException ex) {
            ex.printStackTrace();
        }

        return piResponseResult;
    }

    private List<Double> executeAll(List<PiCalculationThread> piCalculationThreads, ExecutorService executor) throws InterruptedException, ExecutionException {
        List<Future<Double>> futures = executor.invokeAll(piCalculationThreads);
        List<Double> aggregatedResults = new ArrayList<Double>();
        for (Future<Double> future : futures) {
            aggregatedResults.add(future.get());
        }
        return aggregatedResults;
    }

}
