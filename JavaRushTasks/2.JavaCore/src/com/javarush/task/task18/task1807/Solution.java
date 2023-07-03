package com.javarush.task.task18.task1807;

import java.io.*;
import java.util.Scanner;

/* 
Подсчет запятых
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        FileInputStream fileInputStream = new FileInputStream(scanner.nextLine());
        if(fileInputStream.available()>0){
            int count=0;
            byte[] bytes = new byte[fileInputStream.available()];
            fileInputStream.read(bytes);
            for (byte b:bytes) {
                if (b==','){
                    count++;
                }
            }
            System.out.println(count);
        }
        fileInputStream.close();
    }
}
