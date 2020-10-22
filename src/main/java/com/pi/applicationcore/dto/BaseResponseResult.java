package com.pi.applicationcore.dto;

/**
 * The BaseResponseResult is representative of all response models.
 */
public class BaseResponseResult {
    
    /** The error. */
    protected Error error;

    /**
     * Gets the error.
     *
     * @return the error
     */
    public Error getError() {
        return error;
    }

    /**
     * Sets the error.
     *
     * @param error the new error
     */
    public void setError(Error error) {
        this.error = error;
    }

    /**
     * Checks for error.
     *
     * @return true, if has error
     */
    public boolean hasError() { return error != null; }
}
