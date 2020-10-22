package com.pi.applicationcore.validation;

import com.pi.applicationcore.dto.PiRequest;
import com.pi.applicationcore.dto.PiResponseResult;


/**
 * The PiValidation is an implementation for the PiValidationLocal interface.
 */
public class PiValidation implements com.pi.applicationcore.interfaces.PiValidationLocal {

    /**
     * Checks the request must be suitable with the calculation business.
     *
     * @param request from user request
     * @return the pi response result. The result has an error if the request can't pass validation. 
     */
    @Override
    public PiResponseResult validate(PiRequest request) {
        PiResponseResult result = new PiResponseResult();
        try {
            long value = Long.parseLong(request.getRawNumber(), 10);

            if (value < 0){
                result = addErrorMessage(result);
            }

            request.setValue(value);
        } catch (NumberFormatException e) {
            result = addErrorMessage(result);
        }
        return result;
    }

    
    
    /**
     * Adds error message.
     *
     * @param result the result
     * @return the pi response result
     */
    private PiResponseResult addErrorMessage(PiResponseResult result) {
        Error error = new Error("Your input is not correct.");
        result.setError(error);
        return result;
    }
}
