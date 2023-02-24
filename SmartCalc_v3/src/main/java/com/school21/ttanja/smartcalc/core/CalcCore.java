package com.school21.ttanja.smartcalc.core;

import java.math.BigDecimal;

public class CalcCore {

    public static BigDecimal eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            BigDecimal parse() {
                nextChar();
                BigDecimal x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            BigDecimal parseExpression() {
                BigDecimal x = parseTerm();
                for (;;) {
                    if      (eat('+')) x = x.add(new BigDecimal(String.valueOf(parseTerm())));
                    else if (eat('-')) x = x.subtract(new BigDecimal(String.valueOf(parseTerm())));
                    else return x;
                }
            }

            BigDecimal parseTerm() {
                BigDecimal x = parseFactor();
                for (;;) {
                    if      (eat('*')) x = x.multiply(new BigDecimal(String.valueOf(parseFactor())));
                    else if (eat('/')) x = x.divide(new BigDecimal(String.valueOf(parseFactor())));
                    else return x;
                }
            }

            BigDecimal parseFactor() {
                if (eat('-')) return parseFactor().multiply(new BigDecimal(-1));
                if (eat('+')) return parseFactor().multiply(new BigDecimal(1));


                BigDecimal x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    if (!eat(')')) throw new RuntimeException("Missing ')'");
                } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = BigDecimal.valueOf(Double.parseDouble(str.substring(startPos, this.pos)));
                } else if (ch >= 'a' && ch <= 'z') {
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    if (eat('(')) {
                        x = parseExpression();
                        if (!eat(')')) throw new RuntimeException("Missing ')' after argument to " + func);
                    } else {
                        x = parseFactor();
                    }
                    if (func.equals("sqrt")) x = BigDecimal.valueOf(Math.sqrt(x.doubleValue()));
                    else if (func.equals("sin")) x = BigDecimal.valueOf(Math.sin(Math.toRadians(x.doubleValue())));
                    else if (func.equals("cos")) x = BigDecimal.valueOf(Math.cos(Math.toRadians(x.doubleValue())));
                    else if (func.equals("tan")) x = BigDecimal.valueOf(Math.tan(Math.toRadians(x.doubleValue())));
                    else if (func.equals("asin")) x = BigDecimal.valueOf(Math.asin(Math.toRadians(x.doubleValue())));
                    else if (func.equals("acos")) x = BigDecimal.valueOf(Math.acos(Math.toRadians(x.doubleValue())));
                    else if (func.equals("atan")) x = BigDecimal.valueOf(Math.atan(Math.toRadians(x.doubleValue())));
                    else if (func.equals("ln")) x = BigDecimal.valueOf(Math.log(x.doubleValue()));
                    else if (func.equals("log")) x = BigDecimal.valueOf(Math.log10(x.doubleValue()));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = BigDecimal.valueOf(Math.pow(x.doubleValue(), parseFactor().doubleValue()));

                return x;
            }
        }.parse();
    }

}
