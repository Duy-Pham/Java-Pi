package com.pi.applicationcore.interfaces;

import com.pi.applicationcore.dto.PiArray;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public interface PiFormulaLocal {
    double exec(ExecutorService executorService, List<PiArray> arrData);
    double stopAndGetResult();
}
