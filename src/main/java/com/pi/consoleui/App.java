package com.pi.consoleui;

import com.pi.applicationcore.dto.PiRequest;
import com.pi.applicationcore.dto.PiResponseResult;
import com.pi.applicationcore.interfaces.PiCalculationBusinessLocal;
import com.pi.di.PiDI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class App {

    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Server started");
        PiDI di = new PiDI();
        PiCalculationBusinessLocal piCalculationBusinessLocal = di.getPiBusiness();

        Scanner in = new Scanner(System.in);
        System.out.print("Please input your number: ");
        String number = in.nextLine();

        registerEventFromUser(piCalculationBusinessLocal);

        System.out.println();

        PiRequest piRequest = new PiRequest();
        piRequest.setRawNumber(number);
        try {
            PiResponseResult piResponseResult = piCalculationBusinessLocal.execCalculate(piRequest);
            if (piResponseResult.hasError()) {
                System.out.println(piResponseResult.getError().getMessage());
            } else {
                System.out.println("Result: " + piResponseResult.getValue());
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("Server has something error: ", e);
        }
    }

    private static void registerEventFromUser(PiCalculationBusinessLocal piCalculationBusinessLocal){
        new Thread(() -> {
            System.out.print("Press y to stop and get immediate results without high precision: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String stop = reader.readLine();

                if (stop.equalsIgnoreCase("y")){
                    piCalculationBusinessLocal.stopCalculate();
                    long currentCalculateNumber = piCalculationBusinessLocal.getCurrentNumber();
                    System.out.println("Calculate for number: " + currentCalculateNumber);
                }

            } catch (IOException e) {
                e.printStackTrace();
                logger.error("Server has something error", e);
            }

        }).start();
    }

}
