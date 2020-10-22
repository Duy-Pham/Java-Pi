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

/**
 * The main console app.
 */
public class App {

    /** The logger. */
    private static final Logger logger = LogManager.getLogger(App.class);

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        logger.info("Server started");
        PiDI piDI = new PiDI();
        PiCalculationBusinessLocal piCalculationBusinessLocal = piDI.getPiBusiness();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Please input your number: ");
        String number = scanner.nextLine();

        registerEventFromUser(piCalculationBusinessLocal);

        System.out.println();

        PiRequest piRequest = new PiRequest();
        piRequest.setRawNumber(number);
        try {
            PiResponseResult piResponseResult = piCalculationBusinessLocal.executeCalculate(piRequest);
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

    /**
     * Register event from user.
     *
     * @param piCalculationBusinessLocal the pi calculation business local
     */
    private static void registerEventFromUser(PiCalculationBusinessLocal piCalculationBusinessLocal){
        new Thread(() -> {
            System.out.print("Press y to stop and get immediate results without high precision: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String stop = reader.readLine();

                if (stop.equalsIgnoreCase("y")){
                    piCalculationBusinessLocal.stopCalculate();
                    long numberCalculated = piCalculationBusinessLocal.getNumberCalculated();
                    System.out.println("Calculate for number: " + numberCalculated);
                }

            } catch (IOException e) {
                e.printStackTrace();
                logger.error("Server has something error", e);
            }

        }).start();
    }

}
