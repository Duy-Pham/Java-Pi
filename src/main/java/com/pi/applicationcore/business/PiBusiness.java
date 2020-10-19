package com.pi.applicationcore.business;

import com.pi.applicationcore.dto.PiArray;
import com.pi.applicationcore.dto.PiRequest;
import com.pi.applicationcore.dto.PiResponseResult;
import com.pi.applicationcore.interfaces.PiFormulaLocal;
import com.pi.applicationcore.interfaces.PiValidationLocal;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Stateless
public class PiBusiness implements com.pi.applicationcore.interfaces.PiBusinessLocal {
    private final PiValidationLocal _piValidationLocal;
    private final int LEN_OF_PI_ARR = 10000;
    private final PiFormulaLocal _formulaLocal;
    private static ExecutorService _executorService;

    public PiBusiness(PiValidationLocal piValidationLocal, PiFormulaLocal formulaLocal) {
        _piValidationLocal = piValidationLocal;
        _formulaLocal = formulaLocal;
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
    public PiResponseResult stopAndGetResult() {
        var sum = stopAndGetResultFun();
        var res = new PiResponseResult();
        res.setValue(sum);
        return res;
    }

    private PiResponseResult cal(PiRequest request) {
        _executorService = createThreadPool();
        List<PiArray> piArrs = createArrItems(request);

        PiResponseResult piResponseResult = new PiResponseResult();

        var sum = _formulaLocal.exec(_executorService, piArrs);
        piResponseResult.setValue(sum);

        return piResponseResult;
    }

    private ExecutorService createThreadPool(){
        int procNb = Runtime.getRuntime().availableProcessors();
        var executorService = Executors.newFixedThreadPool(procNb);
        return executorService;
    }

    private List<PiArray> createArrItems(PiRequest request){
        List<PiArray> piArrays = new ArrayList<>();
        long n = request.getValue();
        long startIndex = 1;

        do{
            PiArray piArray = new PiArray();
            piArray.setStart(startIndex);
            startIndex += LEN_OF_PI_ARR;
            if (startIndex > n) {
                startIndex = n;
            }
            piArray.setEnd(startIndex);
            piArrays.add(piArray);
        }while (startIndex < n);

        return piArrays;
    }

    private double stopAndGetResultFun(){
        var executor = _executorService;
        //call shutdown to prevent new tasks from being submitted
        executor.shutdown();

        //get a reference to the Queue
        final BlockingQueue<Runnable> blockingQueue = ((ThreadPoolExecutor)executor).getQueue();

        //clear the Queue
        blockingQueue.clear();
        //or else copy its contents here with a while loop and remove()

        //wait for active tasks to be completed
//        try {
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        try {

            var rr = executor.awaitTermination(1, TimeUnit.SECONDS);
            return _formulaLocal.stopAndGetResult();

//            var sum = _formulaLocal.getResults();
//            return sum.stream().reduce(0.0, Double::sum) + 1;

//            var tasks = _formulaLocal.getTasks();
//            var completedTaskCount = tasks.stream().filter(t -> t.isDone()).count();
//            var sum = tasks.stream()
//                    .filter(t -> t.isDone())
//                    .mapToDouble(d-> {
//                        try {
//                            return d.get();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        } catch (ExecutionException e) {
//                            e.printStackTrace();
//                        }
//                        return 0;
//                    })
//                    .reduce(0.0, Double::sum) + 1;
//
//            return sum;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return 0.0;
    }
}
