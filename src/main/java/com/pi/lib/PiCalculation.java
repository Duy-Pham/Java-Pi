package com.pi.lib;

import java.math.BigInteger;

public class PiCalculation {

    public BigInteger exec(BigInteger n) {
        BigInteger res = new BigInteger("0");
        BigInteger i = new BigInteger("0");

        double re = 1.0;
        for(int j = 1; j < 10; j+=2) {
            re += 1.0/((j * 2) + 1.0);
        }
        for(int j = 2; j < 10; j+=2) {
            re += -1.0/((j * 2) + 1.0);
        }

//        for (; i.compareTo(n) < 1; i.add(new BigInteger("1"))) {
//            BigInteger s = i.multiply(new BigInteger("2")).add(new BigInteger("1"));
//            if (isEvenNumber(i)) {
//                res = res.add(new BigInteger("1").divide(s));
//            } else {
//                res = res.add(new BigInteger("-1").divide(s));
//            }
//        }

        return res;
    }

    private boolean isEvenNumber(BigInteger number) {
        return number.divideAndRemainder(new BigInteger("2"))[1].equals(0);
    }
}
