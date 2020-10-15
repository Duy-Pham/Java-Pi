package com.pi.applicationcore.interfaces;

import com.pi.applicationcore.dto.PiRequest;
import com.pi.applicationcore.dto.PiResponseResult;

import javax.ejb.Local;

@Local
public interface PiBusinessLocal {
    PiResponseResult exec(PiRequest request);
}
