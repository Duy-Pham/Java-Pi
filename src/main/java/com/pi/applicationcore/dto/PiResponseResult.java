package com.pi.applicationcore.dto;

/**
 * The PiResponseResult is the result of the response from the calculation.
 */
public class PiResponseResult extends BaseResponseResult {
    
    /** The value. */
    private double value;

    /**
     * Gets the value.
     *
     * @return the value
     */
    public double getValue() {
        return value;
    }

    /**
     * Sets the value.
     *
     * @param value the new value
     */
    public void setValue(double value) {
        this.value = value;
    }
}
