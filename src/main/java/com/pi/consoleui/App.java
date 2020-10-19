package com.pi.consoleui;

import com.pi.applicationcore.dto.PiRequest;
import com.pi.applicationcore.dto.PiResponseResult;
import com.pi.applicationcore.interfaces.PiBusinessLocal;
import com.pi.di.PiDI;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Please input your number : ");
//        String number = in.nextLine();

        PiDI di = new PiDI();
        PiBusinessLocal piBus = di.getPiBusiness();

//        registerStopEvent(piBus);
        autoShutdown(piBus);

        PiRequest piRequest = new PiRequest();
//        piRequest.setRawNumber(number);
        piRequest.setRawNumber("9876543210");
        try {
            PiResponseResult piResponseResult = piBus.exec(piRequest);

            if (piResponseResult.hasError()) {
                System.out.println(piResponseResult.getError().getMessage());
            } else {
                System.out.println("Result : " + piResponseResult.getValue());
            }
        }catch (Exception e){

        }

    }

    private static void autoShutdown(PiBusinessLocal piBus) {
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        // your code here
                        System.out.println("----ket qua----");
                        var res = piBus.stopAndGetResult();
                        System.out.println("ket qua : " + res.getValue());
                    }
                },
                10000
        );
    }

    private static void registerStopEvent(PiBusinessLocal piBus) {
        final long start = System.currentTimeMillis();
//        Runtime.getRuntime().ad
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                double hours = (System.currentTimeMillis() - start) / 3600000d;
                System.out.println("Please enter the hourly rate");
//                double hourlyRate = new Scanner(System.in).nextDouble();
//                System.out.format("Program ran for %01.3f hours and cost $%02.2f", hours, hourlyRate * hours);

                var res = piBus.stopAndGetResult();
                System.out.println("ket qua : " + res.getValue());
            }
        }));
//        try {
//            Thread.sleep(Long.MAX_VALUE);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

}
