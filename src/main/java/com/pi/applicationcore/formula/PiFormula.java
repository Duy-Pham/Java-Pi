package com.pi.applicationcore.formula;

import com.pi.applicationcore.dto.PiArray;
import com.pi.applicationcore.interfaces.PiFormulaLocal;
import com.pi.applicationcore.thread.PiCalculationThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class PiFormula implements PiFormulaLocal {
    @Override
    public double exec(ExecutorService executorService, List<PiArray> arrData) {
        try {
            var tasks = createRunableTasks(arrData);
            double sum = executeAll(executorService, tasks)
                    .stream()
                    .reduce(0.0, Double::sum) + 1;

            return sum;

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (ExecutionException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    private List<PiCalculationThread> createRunableTasks(List<PiArray> piArrays){
        List<PiCalculationThread> piCalculationThreads = new ArrayList<PiCalculationThread>();

        for(var item : piArrays){
            piCalculationThreads.add(new PiCalculationThread(item.getStart(), 1, item.getEnd()));
        }

        return piCalculationThreads;
    }

    public List<Double> executeAll(ExecutorService executor, List<PiCalculationThread> tasks) throws InterruptedException, ExecutionException {
        List<Future<Double>> futures = executor.invokeAll(tasks);
        List<Double> aggregatedResults = new ArrayList<Double>();
        for (Future<Double> future : futures) {
            aggregatedResults.add(future.get());
        }
        return aggregatedResults;
    }
}
