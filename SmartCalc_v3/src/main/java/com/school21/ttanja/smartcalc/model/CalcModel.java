package com.school21.ttanja.smartcalc.model;

public interface CalcModel {

    String calculate(String toCalculate);
    String show();
    void setItem(String str);
    void clean();
    void set0();
    void set1();
    void set2();
    void set3();
    void set4();
    void set5();
    void set6();
    void set7();
    void set8();
    void set9();
    void dot();
    void summ();
    void subtle();
    void divide();
    void multiply();
    void result();
    void sqrt();
    void setC();
    void setOpenBracket();
    void setClosingBracket();
    void setNegative();
    void setX();
    void setAtan();
    void setSin();
    void setLn();
    void setLog();
    void setCos();
    void setTan();
    void setAsin();
    void setPi();
    void setAcos();
    void setDegree();
    void removeHistory();
    void setExpressionFromHistory(String str);

    void saveFunction();
}

