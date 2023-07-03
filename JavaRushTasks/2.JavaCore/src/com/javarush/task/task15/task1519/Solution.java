package com.javarush.task.task15.task1519;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/* 
Разные методы для разных типов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //напиште тут ваш код
        Scanner scanner = new Scanner(System.in);
        while (true){
            boolean isPrinted = false;
            String console = scanner.nextLine();
            if (console.equals("exit")){
                return;
            }
            try {
                for (int i = 0; i < console.length(); i++) {
                    if (console.charAt(i) == '.') {
                        print(Double.parseDouble(console));
                        isPrinted = true;
                        break;
                    }
                }
                if (!isPrinted) {
                    int number = Integer.parseInt(console);
                    if (number > 0 && number < 128) {
                        print((short) number);
                        isPrinted = true;
                    } else if (number <= 0 || number >= 128) {
                        print(number);
                    }
                }
            }catch (Exception e){
                print(console);

            }
        }
    }

    public static void print(Double value) {
        System.out.println("Это тип Double, значение " + value);
    }

    public static void print(String value) {
        System.out.println("Это тип String, значение " + value);
    }

    public static void print(short value) {
        System.out.println("Это тип short, значение " + value);
    }

    public static void print(Integer value) {
        System.out.println("Это тип Integer, значение " + value);
    }
}
