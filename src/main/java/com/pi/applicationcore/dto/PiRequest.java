package com.pi.applicationcore.dto;

/**
 * The PiRequest is to represent a calculation request.
 */
public class PiRequest extends BaseRequest {
    
    /** The raw number. */
    private String rawNumber;
    
    /** The value. */
    private long value;

    /**
     * Gets the value.
     *
     * @return the value
     */
    public long getValue() {
        return value;
    }

    /**
     * Sets the value.
     *
     * @param value the new value
     */
    public void setValue(long value) {
        this.value = value;
    }

    /**
     * Gets the raw number.
     *
     * @return the raw number
     */
    public String getRawNumber() {
        return rawNumber;
    }

    /**
     * Sets the raw number.
     *
     * @param rawNumber the new raw number
     */
    public void setRawNumber(String rawNumber) {
        this.rawNumber = rawNumber;
    }
}
