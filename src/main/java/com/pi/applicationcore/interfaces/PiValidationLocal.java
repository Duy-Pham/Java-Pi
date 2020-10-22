package com.pi.applicationcore.interfaces;

import com.pi.applicationcore.dto.PiRequest;
import com.pi.applicationcore.dto.PiResponseResult;


/**
 * The Interface PiValidationLocal represents the actions supported for the validation.
 */
public interface PiValidationLocal {
    
	/**
     * Checks the request must be suitable with the calculation business.
     *
     * @param request from user request
     * @return the pi response result. The result has an error if the request can't pass validation. 
     */
    PiResponseResult validate(PiRequest request);
}
