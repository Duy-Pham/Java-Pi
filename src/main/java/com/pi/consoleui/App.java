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

        PiRequest piRequest = new PiRequest();
//        piRequest.setRawNumber(number);
        piRequest.setRawNumber("9876543");
        PiResponseResult piResponseResult = piBus.exec(piRequest);

        if (piResponseResult.hasError()) {
            System.out.println(piResponseResult.getError().getMessage());
        } else {
            System.out.println("Result : " + piResponseResult.getValue());
        }
    }
}
