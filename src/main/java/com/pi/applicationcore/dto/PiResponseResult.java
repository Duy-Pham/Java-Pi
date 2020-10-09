package com.pi.applicationcore.dto;

public class PiResponseResult extends BaseResponseResult {
    private double value;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
