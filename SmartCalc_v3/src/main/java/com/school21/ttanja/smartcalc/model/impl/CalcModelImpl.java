package com.school21.ttanja.smartcalc.model.impl;

import com.school21.ttanja.smartcalc.controller.impl.CalcControllerImpl;
import com.school21.ttanja.smartcalc.core.CalcCore;
import com.school21.ttanja.smartcalc.exceptions.MathException;
import com.school21.ttanja.smartcalc.model.CalcModel;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class CalcModelImpl implements CalcModel {

    private String FILE_HISTORY = "history.txt";
    static CalcModelImpl calcService;
    private CalcModelImpl(){
    }
    public static CalcModelImpl getCalcModel(){
        if (null == calcService)
            calcService = new CalcModelImpl();
        return calcService;
    }

    private static final Logger log = LoggerFactory.getLogger(CalcControllerImpl.class);

    public static String expression = "";
    static boolean flagDot = false;

    private List<String> expressionsHistory = readHistory();
    public List<String> getExpressionsHistory() {
        return expressionsHistory;
    }
    @Override
    public String show(){
        return expression;
    }

    @Override
    public void setItem(String str){
        if (isEmpty())
            expression = (str.trim() + " ");
        else
            expression = (expression + " " + str + " ");
        flagDot = false;
    }

    public void setExpressionFromHistory(String str){
        expression = str;
    }

    @Override
    public void saveFunction() {
        saveHistory();
    }

    public void clean(){
        expression = "0";
        flagDot = false;
    }

    private void setDigit(char ch){
        if (isAllowedInput())
            expression = (expression + ch);
        else {
            StringBuilder stringBuilder = new StringBuilder(expression);
            stringBuilder.setCharAt(stringBuilder.length() - 1, ch);
            expression = stringBuilder.toString();
        }
    }
    @Override
    public void set0() {
        setDigit('0');
    }

    @Override
    public void set1() {
        setDigit('1');
    }

    @Override
    public void set2() {
        setDigit('2');
    }

    @Override
    public void set3() {
        setDigit('3');
    }

    @Override
    public void set4() {
        setDigit('4');
    }

    @Override
    public void set5() {
        setDigit('5');
    }

    @Override
    public void set6() {
        setDigit('6');
    }

    @Override
    public void set7() {
        setDigit('7');
    }

    @Override
    public void set8() {
        setDigit('8');
    }

    @Override
    public void set9() {
        setDigit('9');
    }

    @Override
    public void summ() {
        if (isOperator(getLastToken()))
            removeLastItem();
        setItem("+");
    }

    @Override
    public void subtle() {
        if (isOperator(getLastToken()))
            removeLastItem();
        setItem("-");
    }

    @Override
    public void divide() {
        if (isOperator(getLastToken()))
            removeLastItem();
        setItem("/");
    }

    @Override
    public void multiply() {
        if (isOperator(getLastToken()))
            removeLastItem();
        setItem("*");
    }

    @Override
    public void dot() {
        if (!flagDot && Character.isDigit(getLastSymbol())) {
            expression = (expression + '.');
            flagDot = true;
        }
        else if (getLastSymbol() == '.'){
            removeLastChar();
            flagDot = false;
        }
    }

    @Override
    public void result() {
        expressionsHistory.add(expression);
        try {
            expression =
                    expression + '\n' +
                            calculate(expression) + "";
            saveHistory();
        }
        catch (RuntimeException e){
            log.error("Error calculate {}", expression);
            expression =
                    expression + '\n' +
                            "ERROR";
        }
    }

    public String calculate(String toCalculate) {
        BigDecimal calculate = CalcCore.eval(toCalculate);
        String fromCalc = String.valueOf(calculate);
        if (!fromCalc.contains("E") && fromCalc.split("\\.").length == 2)
            if (Double.parseDouble(fromCalc) % 1 == 0)
                fromCalc = fromCalc.split("\\.")[0];
        return fromCalc;
    }

    @Override
    public void sqrt() {
        setItem("sqrt(");
    }

    @Override
    public void setC() {
        if (getCountToken() < 2)
            clean();
        else
            removeLastItem();
    }


    public void setOpenBracket(){
        setItem("(");
    }

    public void setClosingBracket() {
        setItem(")");
    }

    public void setNegative() {
        if (isLastItemOperation())
            return;
        double tmp = Double.parseDouble(getLastToken());
        tmp *= -1;
        removeLastItem();
        if (tmp % 1 == 0)
            setItem(String.valueOf((int)tmp));
        else
            setItem(String.valueOf(tmp));
    }

    public  void setX() {
        setItem("x");
    }

    public void setLog(){
        setItem("log(");
    }

    public void setAtan() {
        setItem("atan(");
    }

    public void setSin(){
        setItem("sin(");
    }

    public void setLn(){
        setItem("ln(");
    }
    public void setCos(){
        setItem("cos(");
    }

    public void setTan(){
        setItem("tan(");
    }


    public void setAsin(){
        setItem("asin(");
    }

    public void setPi(){
        setItem("3.1415");
    }

    public void setAcos(){
        setItem("acos(");
    }

    public void setDegree(){
        if (!isOperator(getLastToken()))
            setItem("^");
        else
            expression = "0^";
    }


    private boolean isLastItemOperation(){
        StringBuilder stringBuilder = new StringBuilder(getLastToken());
        char lastItem;
        lastItem = stringBuilder.charAt(stringBuilder.length() - 1);
        return !Character.isDigit(lastItem) &&
                (lastItem != '.');
    }


    private void removeLastChar(){
        StringBuilder stringBuilder = new StringBuilder(expression);
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        expression = stringBuilder.toString();
    }
    private ArrayList<String> getTokens(){
        ArrayList<String> token = new ArrayList<>(Arrays.asList(expression.split(" ")));
        token.forEach(String::trim);
        return token;
    }

    private int getCountToken(){
        return expression.split(" ").length;
    }

    private String getLastToken(){
        return getTokens().get(getTokens().size() - 1);
    }

    private char getLastSymbol(){
        return getLastToken().toCharArray()[getLastToken().toCharArray().length - 1];
    }

    private boolean isOperator(String str){
        return  !(Character.isDigit(str.charAt(0)) || str.charAt(0) == '(' || str.charAt(0) == ')' || str.charAt(0) == 'x');
    }
    private boolean isAllowedInput() {
        int lenStr = expression.toCharArray().length;
        if (lenStr == 0)
            return true;
        return !getLastToken().equals("0") || getLastSymbol() == '.' || flagDot;
    }

    private boolean isEmpty(){
        return (getCountToken() == 1 && getTokens().get(0).equals("0"));
    }

    private void removeLastItem() {
        StringBuilder result = new StringBuilder();
        ArrayList<String> tmp = getTokens();
        tmp.remove(getTokens().size() - 1);
        tmp.forEach(it -> result.append(" ").append(it));
        expression = result.toString().trim();
    }

    public void removeHistory(){
        expressionsHistory.removeAll(expressionsHistory);
        saveHistory();
    }

    private static boolean isFileExists(File file) {
        return file.exists() && !file.isDirectory();
    }

    private ArrayList<String> readHistory(){
        List<String> list = new ArrayList<>();
        File save = new File(FILE_HISTORY);
        if (!isFileExists(save)) {
            try {
                save.createNewFile();
            } catch (IOException e) {
                log.error(e.getMessage());
                throw new RuntimeException(e);
            }
        }
        try {
            list = Files.readAllLines(Paths.get(FILE_HISTORY));
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return (ArrayList<String>) list;
    }

    private void saveHistory(){
        File save = new File(FILE_HISTORY);
        if (!isFileExists(save)) {
            try {
                save.createNewFile();
            } catch (IOException e) {
                log.error(e.getMessage());
                throw new RuntimeException(e);
            }
        }
        try {
            Files.write(Paths.get(FILE_HISTORY), expressionsHistory);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}


