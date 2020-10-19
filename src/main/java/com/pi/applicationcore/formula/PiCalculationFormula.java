package com.pi.applicationcore.formula;

import com.pi.applicationcore.dto.PiArray;
import com.pi.applicationcore.interfaces.PiFormulaLocal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

public class PiCalculationFormula implements PiFormulaLocal {
    public List<Future<Double>> futures;
    List<Double> aggregatedResults;
    private AtomicBoolean _isStop;

    public PiCalculationFormula(){
        futures = new ArrayList<>();
        aggregatedResults = new ArrayList<Double>();
        _isStop = new AtomicBoolean();
    }

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

var arrResult = aggregatedResults
        .stream()
        .reduce(0.0, Double::sum);

sum += arrResult;

        return sum;
    }

    @Override
    public List<Future<Double>> getTasks() {
        return futures;
//        return null;
    }

    @Override
    public void stop() {
        _isStop.set(true);
    }

    @Override
    public double getResult() {
        return aggregatedResults
                .stream()
                .reduce(0.0, Double::sum) + 1;
    }

    private List<PiCalculationFormulaThread> createRunableTasks(List<PiArray> piArrays){
        List<PiCalculationFormulaThread> tasks = new ArrayList<PiCalculationFormulaThread>();

        for(var item : piArrays){
            tasks.add(new PiCalculationFormulaThread(item.getStart(), 1, item.getEnd()));
        }

        return tasks;
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

//        // new ways to exec tasks
        //var indexOfTasks = 0;
        var nThreads = 8;

        for(int indexOfTasks = 0, len = tasks.size(); indexOfTasks < len; indexOfTasks++){

            if(_isStop.get() == false){
                var task = tasks.get(indexOfTasks);
                futures.add(executor.submit(task));
                if (futures.size() >= nThreads * 5){
                    var currentItemInFuture = 0;
                    for(var future : futures){
                        aggregatedResults.add(future.get());
//                    currentItemInFuture++;
//                    if(( futures.size() - currentItemInFuture) < nThreads){
//                        break;
//                    }
                    }

                    // bad version
                    futures.clear();
                }
            }

        }

        // for items in
        for(var future : futures){
            aggregatedResults.add(future.get());
        }

        return aggregatedResults;




//        futures = executor.invokeAll(tasks);
//        aggregatedResults = new ArrayList<Double>();
//        for (Future<Double> future : futures) {
//            aggregatedResults.add(future.get());
//        }
//        return aggregatedResults;
    }


}
