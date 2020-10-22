package com.pi.applicationcore.interfaces;

import com.pi.applicationcore.dto.PiRequest;
import com.pi.applicationcore.dto.PiResponseResult;

import javax.ejb.Local;
import java.util.concurrent.ExecutionException;

@Local
public interface PiCalculationBusinessLocal {
    PiResponseResult execCalculate(PiRequest request) throws ExecutionException, InterruptedException;
    void stopCalculate();
    long getCurrentNumber();
}
