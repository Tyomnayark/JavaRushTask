package com.javarush.task.task15.task1529;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/* 
Осваивание статического блока
*/

public class Solution {
    public static void main(String[] args) {

    }

    static {
        //add your code here - добавьте код тут
        reset();
    }

    public static CanFly result;

    public static void reset() {
        Scanner scanner = new Scanner(System.in);
        String nameOfVehicle = scanner.nextLine();
        switch (nameOfVehicle){
            case "helicopter": result = new Helicopter();
                break;
            case "plane": result = new Plane(scanner.nextInt());
                break;
        }
        //add your code here - добавьте код тут
        scanner.close();
    }
}
