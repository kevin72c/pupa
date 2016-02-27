package com.cmy.basis.script;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Script {

    public static void main(String[] args) {
//        eval("10000000000+1111111111111111111111");
        String str = "111111";
        char[] charArray = str.toCharArray();
        int x;
        for (int i = 0; i < charArray.length; i++) {
            
        }
         BigInteger b = new
         BigInteger("1000000000000333333333333333333333333333333333333000000000000006666666666666666666666666666666666600000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
         BigInteger add = b.add(b);
//         b.subtract(val);
         System.out.println(add);
//         System.out.println(Integer.MAX_VALUE);
//         System.out.println(Double.MAX_VALUE);
    }

    private static final ScriptEngine engine;
    static {
        engine = new ScriptEngineManager().getEngineByExtension("js");

        try {
            String scriptMAX = "function MAX(VAL1,VAL2){ return Math.max(VAL1,VAL2);}";
            engine.eval(scriptMAX);
            String scriptMIN = "function MIN(VAL1,VAL2){ return Math.min(VAL1,VAL2);}";
            engine.eval(scriptMIN);
            String scriptROUND = "function ROUND(VAL1,DIGITS){ return VAL1.toFixed(DIGITS);}";
            engine.eval(scriptROUND);
            String scriptIF = "function IF(COND,VAL1,VAL2){ return COND?VAL1:VAL2;}";
            engine.eval(scriptIF);
            String scriptABS = "function ABS(VAL1){return Math.abs(VAL1)}";
            engine.eval(scriptABS);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    public static void eval(String script) {
        try {
            System.out.println(engine.eval(script));
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}
