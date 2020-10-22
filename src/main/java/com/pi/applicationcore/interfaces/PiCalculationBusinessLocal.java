package com.pi.applicationcore.interfaces;

import com.pi.applicationcore.dto.PiRequest;
import com.pi.applicationcore.dto.PiResponseResult;

import java.util.concurrent.ExecutionException;


/**
 * The Interface PiCalculationBusinessLocal represents the actions supported for the calculation business.
 */
public interface PiCalculationBusinessLocal {
    
    /**
     * Execute calculate for the pi request.
     *
     * @param request the pi request
     * @return the result after calculate
     * @throws ExecutionException the execution exception
     * @throws InterruptedException the interrupted exception
     */
    PiResponseResult executeCalculate(PiRequest request) throws ExecutionException, InterruptedException;
    
    /**
     * Stop calculating when we don't want to calculate the number.
     */
    void stopCalculate();
    
    /**
     * Gets the number have calculated.
     *
     * @return the number calculated
     */
    long getNumberCalculated();
}
