package com.javarush.task.task19.task1906;

import java.io.*;
import java.util.ArrayList;

/* 
Четные символы
*/

public class Solution {
    public static void main(String[] args) {
        try( BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
             FileReader fileReader = new FileReader(bufferedReader.readLine());
             FileWriter fileWriter = new FileWriter(bufferedReader.readLine())){
            int count = 0;
            while (true){
                int i = fileReader.read();
                if (i == -1){
                    break;
                }
                if (count%2!=0){
                    fileWriter.write(i);
                }
                count++;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
