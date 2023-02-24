package com.school21.ttanja.smartcalc.core;

import com.school21.ttanja.smartcalc.core.CalcCore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class CalcCoreTest {

    @Test
    void sum() {
        String toSum = "1 + 1";

        BigDecimal result = CalcCore.eval(toSum);

        Assertions.assertEquals("2.0", result.toString());
    }

    @Test
    void subtle() {
        String toSum = "44 - 1";

        BigDecimal result = CalcCore.eval(toSum);

        Assertions.assertEquals("43.0", result.toString());
    }

    @Test
    void multiply() {
        String toSum = "44 * 2";

        BigDecimal result = CalcCore.eval(toSum);

        Assertions.assertEquals("88.00", result.toString());
    }

    @Test
    void divide() {
        String toSum = "44 / 2";

        BigDecimal result = CalcCore.eval(toSum);

        Assertions.assertEquals("22", result.toString());
    }
}