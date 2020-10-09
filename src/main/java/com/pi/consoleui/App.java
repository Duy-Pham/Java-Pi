package com.pi.consoleui;

import com.pi.applicationcore.dto.PiRequest;
import com.pi.applicationcore.dto.PiResponseResult;
import com.pi.applicationcore.interfaces.PiBusiness;
import com.pi.di.PiDI;

public class App {

    public static void main( String[] args )
    {
        // tasks need to do
        // 1. User can input long value()
        // 2. Validation data of user (need a number)
        // 3. Algorithm to get result
        // 4. Print to screen

        System.out.print( "Please input your n : " );

        PiDI di = new PiDI();
        PiBusiness piBus = di.getPiBusiness();

        PiRequest piRequest = new PiRequest();
        piRequest.setRawNumber("9876543219");
        PiResponseResult piResponseResult = piBus.exec(piRequest);

        if (piResponseResult.getError() != null){
            System.out.println(piResponseResult.getError().getMessage());
        }
        else {
            System.out.println("Ket qua : " + piResponseResult.getValue());
        }

        int x = 1;
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
