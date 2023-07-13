package com.javarush.task.task19.task1926;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* 
Перевертыши
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = bufferedReader.readLine();
        bufferedReader.close();

        try(BufferedReader bufferedReader1 = new BufferedReader(new FileReader(fileName))){
            String buffer;
            while(( buffer  = bufferedReader1.readLine()) != null){
                System.out.println(new StringBuffer(buffer).reverse().toString());
            }
        }

    }
}
