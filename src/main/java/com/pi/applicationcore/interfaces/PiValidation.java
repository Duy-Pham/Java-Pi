package com.pi.applicationcore.interfaces;

import com.pi.applicationcore.dto.PiRequest;
import com.pi.applicationcore.dto.PiResponseResult;

public interface PiValidation {
    PiResponseResult validate(PiRequest request);
}
