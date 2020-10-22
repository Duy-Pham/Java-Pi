package com.pi.applicationcore.validation;

import com.pi.applicationcore.dto.PiRequest;
import com.pi.applicationcore.dto.PiResponseResult;

import javax.ejb.Stateless;

@Stateless
public class PiValidation implements com.pi.applicationcore.interfaces.PiValidationLocal {

    @Override
    public PiResponseResult validate(PiRequest request) {
        PiResponseResult result = new PiResponseResult();
        try {
            long value = Long.parseLong(request.getRawNumber(), 10);
            request.setValue(value);
        } catch (NumberFormatException e) {
            Error error = new Error("Your input is not correct.");
            result.setError(error);
        }
        return result;
    }
}
