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
    private List<Double> _aggregatedResults;
    private AtomicBoolean _isStop;
    private final int MAXIMUM_SIZE_OF_FUTURE = 50;

    public PiCalculationFormula(){
        _aggregatedResults = new ArrayList<Double>();
        _isStop = new AtomicBoolean();
    }

    @Override
    public double exec(ExecutorService executorService, List<PiArray> arrData) {
        try {
            var tasks = createTasks(arrData);
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
    public void stopCalculate() {
        _isStop.set(true);
    }

    private List<PiCalculationFormulaThread> createTasks(List<PiArray> piArrays){
        List<PiCalculationFormulaThread> tasks = new ArrayList<PiCalculationFormulaThread>();

        for(var item : piArrays){
            tasks.add(new PiCalculationFormulaThread(item.getStart(), 1, item.getEnd()));
        }

        return tasks;
    }

    private List<Double> executeAll(ExecutorService executor, List<PiCalculationFormulaThread> tasks) throws InterruptedException, ExecutionException {
        List<Future<Double>> futures = new ArrayList<>();

        for(int indexOfTasks = 0, len = tasks.size(); indexOfTasks < len && !_isStop.get(); indexOfTasks++){
            var task = tasks.get(indexOfTasks);
            futures.add(executor.submit(task));
            if (futures.size() >= MAXIMUM_SIZE_OF_FUTURE){
                for(var future : futures){
                    _aggregatedResults.add(future.get());
                }
                futures.clear();
            }
        }

        // for items in
        for(var future : futures){
            _aggregatedResults.add(future.get());
        }

        return _aggregatedResults;
    }

}
