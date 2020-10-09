package com.pi.applicationcore.interfaces;

import com.pi.applicationcore.dto.PiRequest;
import com.pi.applicationcore.dto.PiResponseResult;

public interface IPiBusiness {
    PiResponseResult exec(PiRequest request);
}
