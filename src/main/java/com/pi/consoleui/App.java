package com.pi.consoleui;

import com.pi.applicationcore.dto.PiRequest;
import com.pi.applicationcore.dto.PiResponseResult;
import com.pi.applicationcore.interfaces.PiBusinessLocal;
import com.pi.di.PiDI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        PiDI di = new PiDI();
        PiBusinessLocal piBus = di.getPiBusiness();

        Scanner in = new Scanner(System.in);
//        System.out.print("Please input your number: ");
//        String number = in.nextLine();

        waitEventFromUser(piBus);

        System.out.println();

        PiRequest piRequest = new PiRequest();
//        piRequest.setRawNumber(number);
        piRequest.setRawNumber("9876543210");
        try {
            PiResponseResult piResponseResult = piBus.exec(piRequest);
            if (piResponseResult.hasError()) {
                System.out.println(piResponseResult.getError().getMessage());
            } else {
                System.out.println("Result: " + piResponseResult.getValue());
            }
        }catch (Exception e){

        }

    }

    private static void waitEventFromUser(PiBusinessLocal piBus){
        new Thread(() -> {
            System.out.print("Press y to stop and get immediate results without high precision: ");
            var reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String stop = reader.readLine();

                if (stop.equalsIgnoreCase("y")){
                    piBus.stop();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();
    }

}
