package com.pi.applicationcore.business;

import com.pi.applicationcore.dto.PiArray;
import com.pi.applicationcore.dto.PiRequest;
import com.pi.applicationcore.dto.PiResponseResult;
import com.pi.applicationcore.interfaces.PiFormulaLocal;
import com.pi.applicationcore.interfaces.PiValidationLocal;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Stateless
public class PiBusiness implements com.pi.applicationcore.interfaces.PiBusinessLocal {
    private final PiValidationLocal _piValidationLocal;
    private final int LEN_OF_PI_ARR = 10000;
    private final PiFormulaLocal _formulaLocal;

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

    private PiResponseResult cal(PiRequest request) {
        // create threads
        ExecutorService executor = createThreadPool();
        List<PiArray> piArrs = createArrItems(request);

        PiResponseResult piResponseResult = new PiResponseResult();

        var sum = _formulaLocal.exec(executor, piArrs);
        piResponseResult.setValue(sum);

        return piResponseResult;
    }

    private ExecutorService createThreadPool(){
        int procNb = Runtime.getRuntime().availableProcessors();
        var executor = Executors.newFixedThreadPool(procNb);
        return executor;
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
}
