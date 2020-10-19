package com.pi.applicationcore.formula;

import com.pi.applicationcore.dto.PiArray;
import com.pi.applicationcore.interfaces.PiFormulaLocal;
import com.pi.applicationcore.utils.ExecutorServiceUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class PiCalculationFormula implements PiFormulaLocal {
    public List<Future<Double>> futures = new ArrayList<>();
    List<Double> aggregatedResults = new ArrayList<Double>();
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

    @Override
    public double stopAndGetResult() {
//        var completedTaskCount = futures.stream().filter(t -> t.isDone()).count();
        var sum = futures.stream()
                .filter(t -> t.isDone())
                .mapToDouble(d-> {
                    try {
                        return d.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    return 0;
                })
                .reduce(0.0, Double::sum) + 1;

        return sum;
    }

    private List<PiCalculationFormulaThread> createRunableTasks(List<PiArray> piArrays){
        List<PiCalculationFormulaThread> piCalculationThreads = new ArrayList<PiCalculationFormulaThread>();

        for(var item : piArrays){
            piCalculationThreads.add(new PiCalculationFormulaThread(item.getStart(), 1, item.getEnd()));
        }

        return piCalculationThreads;
    }


//    public List<Double> executeAll(ExecutorService executor, List<PiCalculationFormulaThread> tasks) throws InterruptedException, ExecutionException {
//
//        //var nThreads = ((ThreadPoolExecutor)executor).get
//        var nThreads = 8;
//
//        ((ThreadPoolExecutor)executor).get
//        do{
//            for(var i =0; i < 8; i++){
//                executor.submit(tasks.get(i));
//            }
//        }while (true);
//
//        futures = executor.invokeAll(tasks);
//        aggregatedResults = new ArrayList<Double>();
//        for (Future<Double> future : futures) {
//            aggregatedResults.add(future.get());
//        }
//        return aggregatedResults;
//    }

    public List<Double> executeAll(ExecutorService executor, List<PiCalculationFormulaThread> tasks) throws InterruptedException, ExecutionException {


//        ((ThreadPoolExecutor)executor).getc
        for(var task : tasks){
            futures.add(executor.submit(task));
        }

//        futures = executor.invokeAll(tasks);
        aggregatedResults = new ArrayList<Double>();
        for (Future<Double> future : futures) {
            aggregatedResults.add(future.get());
        }
        return aggregatedResults;
    }


}
