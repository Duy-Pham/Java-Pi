package com.pi.applicationcore.business;

import com.pi.applicationcore.dto.PiArray;
import com.pi.applicationcore.dto.PiRequest;
import com.pi.applicationcore.dto.PiResponseResult;
import com.pi.applicationcore.formula.PiCalculationFormulaThread;
import com.pi.applicationcore.interfaces.PiFormulaLocal;
import com.pi.applicationcore.interfaces.PiValidationLocal;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Stateless
public class PiCalculationBusiness implements com.pi.applicationcore.interfaces.PiBusinessLocal {
    private final int LEN_OF_PI_ARR = 10000;
    private final int MAXIMUM_SIZE_OF_FUTURE = 50;

    private final PiValidationLocal _piValidationLocal;
    private final PiFormulaLocal _formulaLocal;
    private static ExecutorService _executorService;
    private AtomicBoolean _isStop;

    public PiCalculationBusiness(PiValidationLocal piValidationLocal, PiFormulaLocal formulaLocal) {
        _piValidationLocal = piValidationLocal;
        _formulaLocal = formulaLocal;
        _isStop = new AtomicBoolean();
    }

    @Override
    public PiResponseResult exec(PiRequest request) {
        PiResponseResult res = _piValidationLocal.validate(request);
        if (!res.hasError()) {
            res = cal(request);
        }
        return res;
    }

    @Override
    public void stop() {
        _isStop.set(true);
    }

    private PiResponseResult cal(PiRequest request) {
        try {
            List<Double> resultTasks = null;
            _executorService = createThreadPool();
            List<PiArray> piArrs = createArrItems(request);

            var callableTasks = _formulaLocal.createCallableTasks(piArrs);
            resultTasks = executeAll(_executorService, callableTasks);
            var result = _formulaLocal.calculate(resultTasks);

            PiResponseResult piResponseResult = new PiResponseResult();
            piResponseResult.setValue(result);
            return piResponseResult;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }

    private ExecutorService createThreadPool(){
        int procNb = Runtime.getRuntime().availableProcessors();
        var executorService = Executors.newFixedThreadPool(procNb);
        return executorService;
    }

    private List<PiArray> createArrItems(PiRequest request){
        List<PiArray> piArrays = new ArrayList<>();
        long number = request.getValue();
        long startIndex = 1;

        do{
            PiArray piArray = new PiArray();
            piArray.setStart(startIndex);
            startIndex += LEN_OF_PI_ARR;
            if (startIndex > number) {
                startIndex = number;
            }
            piArray.setEnd(startIndex);
            piArrays.add(piArray);
        }while (startIndex < number);

        return piArrays;
    }

    private List<Double> executeAll(ExecutorService executor, List<Callable<Double>> tasks) throws InterruptedException, ExecutionException {
        List<Double> aggregatedResults = new ArrayList<>();
        List<Future<Double>> futures = new ArrayList<>();

        for(int indexOfTasks = 0, len = tasks.size(); indexOfTasks < len && !_isStop.get(); indexOfTasks++){
            var task = tasks.get(indexOfTasks);
            futures.add(executor.submit(task));
            if (futures.size() >= MAXIMUM_SIZE_OF_FUTURE){
                for(var future : futures){
                    aggregatedResults.add(future.get());
                }
                futures.clear();
            }
        }

        // for items in
        for(var future : futures){
            aggregatedResults.add(future.get());
        }

        return aggregatedResults;
    }
}
