package com.school21.ttanja.smartcalc.impl;

import com.school21.ttanja.smartcalc.model.impl.CalcModelImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class CalcModelImplTest {
    CalcModelImpl calcModel = CalcModelImpl.getCalcModel();

    @BeforeEach
    void init(){
        calcModel.clean();
    }

    @Test
    void getCalcService() {
        CalcModelImpl moreCalcModel = CalcModelImpl.getCalcModel();
        Assertions.assertEquals(calcModel, moreCalcModel);
    }

    @Test
    void getExpressionsHistory() {
        Assertions.assertNotNull(calcModel.getExpressionsHistory());
    }

    @Test
    void show() {
        Assertions.assertNotNull(calcModel.show());
    }

    @Test
    void setItem() {
        calcModel.setItem("111");
        Assertions.assertEquals("111 ", calcModel.show());
    }

    @Test
    void setExpressionFromHistory() {
        calcModel.setExpressionFromHistory("1 + 2");
        Assertions.assertEquals("1 + 2", calcModel.show());
    }

    @Test
    void clean() {
        calcModel.setExpressionFromHistory("1 + 2");
        Assertions.assertEquals("1 + 2", calcModel.show());
        calcModel.clean();
        Assertions.assertEquals("0", calcModel.show());
    }

    @Test
    void set0() {
        calcModel.set0();
        Assertions.assertEquals("0", calcModel.show());
    }

    @Test
    void set1() {
        calcModel.set1();
        Assertions.assertEquals("1", calcModel.show());
    }

    @Test
    void set2() {
        calcModel.set2();
        Assertions.assertEquals("2", calcModel.show());
    }

    @Test
    void set3() {
        calcModel.set3();
        Assertions.assertEquals("3", calcModel.show());
    }

    @Test
    void set4() {
        calcModel.set4();
        Assertions.assertEquals("4", calcModel.show());
    }

    @Test
    void set5() {
        calcModel.set5();
        Assertions.assertEquals("5", calcModel.show());
    }

    @Test
    void set6() {
        calcModel.set6();
        Assertions.assertEquals("6", calcModel.show());
    }

    @Test
    void set7() {
        calcModel.set7();
        Assertions.assertEquals("7", calcModel.show());
    }

    @Test
    void set8() {
        calcModel.set8();
        Assertions.assertEquals("8", calcModel.show());
    }

    @Test
    void set9() {
        calcModel.set9();
        Assertions.assertEquals("9", calcModel.show());
    }

    @Test
    void summ() {
        calcModel.set2();
        calcModel.summ();
        Assertions.assertEquals("2 + ", calcModel.show());
    }

    @Test
    void subtle() {
        calcModel.set5();
        calcModel.subtle();
        Assertions.assertEquals("5 - ", calcModel.show());
    }

    @Test
    void divide() {
        calcModel.set2();
        calcModel.divide();
        Assertions.assertEquals("2 / ", calcModel.show());
    }

    @Test
    void multiply() {
        calcModel.set2();
        calcModel.multiply();
        Assertions.assertEquals("2 * ", calcModel.show());
    }

    @Test
    void dot() {
        calcModel.set2();
        calcModel.dot();
        Assertions.assertEquals("2.", calcModel.show());
    }

    @Test
    void result() {
        calcModel.set2();
        calcModel.summ();
        calcModel.set2();
        calcModel.result();
        Assertions.assertEquals("2 + 2\n4", calcModel.show());
    }

    @Test
    void calculate() {
        String calc = calcModel.calculate("5 * 5");
        Assertions.assertEquals("25", calc);
    }

    @Test
    void sqrt() {
        calcModel.sqrt();
        Assertions.assertEquals("sqrt( ", calcModel.show());
    }

    @Test
    void setC() {
        calcModel.set2();
        calcModel.summ();
        calcModel.set2();
        Assertions.assertEquals("2 + 2", calcModel.show());
        calcModel.setC();
        Assertions.assertEquals("2 +", calcModel.show());
    }

    @Test
    void setOpenBracket() {
        calcModel.setOpenBracket();
        Assertions.assertEquals("( ", calcModel.show());
    }

    @Test
    void setClosingBracket() {
        calcModel.setClosingBracket();
        Assertions.assertEquals(") ", calcModel.show());
    }

    @Test
    void setNegative() {
        calcModel.set2();
        calcModel.set2();
        Assertions.assertEquals("22", calcModel.show());
        calcModel.setNegative();
        Assertions.assertEquals(" -22 ", calcModel.show());
        calcModel.setNegative();
        Assertions.assertEquals(" 22 ", calcModel.show());
    }

    @Test
    void setX() {
        calcModel.setX();
        Assertions.assertEquals("x ", calcModel.show());
    }

    @Test
    void setLog() {
        calcModel.setLog();
        Assertions.assertEquals("log( ", calcModel.show());
    }

    @Test
    void setAtan() {
        calcModel.setAtan();
        Assertions.assertEquals("atan( ", calcModel.show());
    }

    @Test
    void setSin() {
        calcModel.setSin();
        Assertions.assertEquals("sin( ", calcModel.show());
    }

    @Test
    void setLn() {
        calcModel.setLn();
        Assertions.assertEquals("ln( ", calcModel.show());
    }

    @Test
    void setCos() {
        calcModel.setCos();
        Assertions.assertEquals("cos( ", calcModel.show());
    }

    @Test
    void setTan() {
        calcModel.setTan();
        Assertions.assertEquals("tan( ", calcModel.show());
    }

    @Test
    void setAsin() {
        calcModel.setAsin();
        Assertions.assertEquals("asin( ", calcModel.show());
    }

    @Test
    void setPi() {
        calcModel.setPi();
        Assertions.assertEquals("3.1415 ", calcModel.show());
    }

    @Test
    void setAcos() {
        calcModel.setAcos();
        Assertions.assertEquals("acos( ", calcModel.show());
    }

    @Test
    void setDegree() {
        calcModel.set3();
        calcModel.setDegree();
        calcModel.set2();
        Assertions.assertEquals("3 ^ 2", calcModel.show());
    }

    @Test
    void removeHistory() {
        calcModel.setExpressionFromHistory("2 + 2");
        calcModel.result();
        calcModel.removeHistory();
        Assertions.assertEquals(0, (long) calcModel.getExpressionsHistory().size());
    }

    @Test
    void setExpressionsHistory() {
        ArrayList<String> list = new ArrayList<>();
        list.add("one");
        Assertions.assertNotEquals(list, calcModel.getExpressionsHistory());
        calcModel.setExpressionsHistory(list);
        Assertions.assertEquals(list, calcModel.getExpressionsHistory());
    }

}