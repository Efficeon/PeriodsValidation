package com.validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StartValidator {
    public static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        System.out.println("Введите данные для проверки: ");

        String line = readString();

        if (line != null){
            PeriodsValidation periodsValidation = new PeriodsValidation();
            System.out.println(periodsValidation.findIntersections(line));
        }
    }

    public static String readString() {
        String line = null;
        try {
            line = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }
}
