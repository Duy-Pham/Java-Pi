package com.pi.calculation;

import com.pi.applicationcore.business.PiBusiness;
import com.pi.applicationcore.dto.PiRequest;
import com.pi.applicationcore.interfaces.IPiBusiness;
import com.pi.lib.PiCalculation;

import java.math.BigInteger;

public class App {
//    private final IPiBusiness _piBusiness;
//    private final IPiValidation _piValidation;
//
//    public App(IPiBusiness piBusiness, IPiValidation piValidation){
//        _piBusiness = piBusiness;
//        _piValidation = piValidation;
//    }

    public static void main( String[] args )
    {
        // tasks need to do
        // 1. User can input long value()
        // 2. Validation data of user (need a number)
        // 3. Algorithm to get result
        // 4. Print to screen

        System.out.print( "Please input your n : " );

//        IPiValidation piValidation = new PiValidation();
//        IPiBusiness piBusiness = new PiBusiness();
//        PiRequest piRequest = _pi


        PiCalculation pi = new PiCalculation();
        BigInteger res = pi.exec(new BigInteger("123456789123456789123465798123456798213546789123465879213645879132654879321654987321654987321654987321654987321654987"));
        System.out.println("Ket qua : " + res.toString());

        // dat van de ???
        // 1. Thuat toan can do chinh xac nhu the nao -> Ko can cao chi can (long)
        // 2. Limit of the number?
        // 3.


        // input from user
//        System.in.read()
        // output to user
//        Scanner myObj = new Scanner(System.in);
    }
}
