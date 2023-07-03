package com.javarush.task.task18.task1809;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/* 
Реверс файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String fileName1 = scanner.nextLine();
        String fileName2 = scanner.nextLine();
        FileInputStream fileInputStream1 = new FileInputStream(fileName1);
        FileOutputStream fileOutputStream2 = new FileOutputStream(fileName2);
        scanner.close();

        if (fileInputStream1.available()>0){
            byte[] buffer = new byte[fileInputStream1.available()];
            fileInputStream1.read(buffer);
            byte tmp;
            for (int i = 0; i< buffer.length/2; i++ ){
                tmp = buffer[i];
                buffer[i] = buffer[buffer.length-1-i];
                buffer[buffer.length-1-i] = tmp;
            }
                fileOutputStream2.write(buffer);
        }
        fileInputStream1.close();
        fileOutputStream2.close();

    }
}
