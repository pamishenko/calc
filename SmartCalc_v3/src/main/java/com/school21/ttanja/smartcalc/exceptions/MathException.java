package com.school21.ttanja.smartcalc.exceptions;

public class MathException extends RuntimeException{
    @Override
    public String getMessage() {
        return "Error call math operation";
    }
}
