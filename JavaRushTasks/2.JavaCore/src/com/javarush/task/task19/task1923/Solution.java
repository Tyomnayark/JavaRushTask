package com.javarush.task.task19.task1923;

import java.io.*;

/* 
Слова с цифрами
*/

public class Solution {
    public static void main(String[] args) {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(args[1]))){
            String buffer;
            while((buffer = bufferedReader.readLine()) != null){
                String[] words  = buffer.split(" ");
                for (int i = 0; i < words.length; i++){
                    String w = words[i];
                    for (int j = 0; j < w.length(); j++){
                        if (Character.isDigit(w.charAt(j))){
                            bufferedWriter.write(w);
                            bufferedWriter.write(' ');
                            break;
                        }
                    }
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
