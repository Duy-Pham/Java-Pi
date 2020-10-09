package com.pi.applicationcore.validation;

import com.pi.applicationcore.dto.PiRequest;
import com.pi.applicationcore.dto.PiResponseResult;

public class PiValidation implements com.pi.applicationcore.interfaces.PiValidation {

    @Override
    public PiResponseResult validate(PiRequest request) {
        PiResponseResult result = new PiResponseResult();
        long value = tryParse(request.getRawNumber(), -1);
        if (value == -1){
            Error error = new Error("Your input is not correct.");
            result.setError(error);
        }

        request.setValue(value);
        return result;
    }

    public long tryParse(String value, int defaultVal) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }
}
