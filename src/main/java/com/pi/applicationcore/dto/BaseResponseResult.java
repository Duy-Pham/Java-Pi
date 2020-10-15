package com.pi.applicationcore.dto;

public class BaseResponseResult {
    protected Error error;

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public boolean hasError() { return error != null; }
}
