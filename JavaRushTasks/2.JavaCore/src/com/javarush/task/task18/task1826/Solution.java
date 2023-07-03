package com.javarush.task.task18.task1826;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* 
Шифровка
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        String flag = args[0];
        String fileName = args[1];
        String fileOutputName = args[2];

        FileInputStream fileInputStream = new FileInputStream(fileName);
        FileOutputStream fileOutputStream = new FileOutputStream(fileOutputName);
        switch (flag) {

            case "-e":
                byte[] buffer = new byte[fileInputStream.available()];
                int count = fileInputStream.read(buffer);
                  for (int i = 0; i < buffer.length / 2; i++) {
                      byte temp = buffer[i];
                      buffer[i] = buffer[buffer.length - i - 1];
                      buffer[buffer.length - i - 1] = temp;
                  }
                 fileOutputStream.write(buffer);
                  break;

            case "-d":
                byte[] buffer2 = new byte[fileInputStream.available()];
                int count2 = fileInputStream.read(buffer2);
                for (int i = buffer2.length / 2; i >=0; i--) {
                    byte temp = buffer2[i];
                    buffer2[i] = buffer2[buffer2.length - i - 1];
                    buffer2[buffer2.length - i - 1] = temp;
                }
                fileOutputStream.write(buffer2);
                break;
        }
        fileOutputStream.close();
        fileInputStream.close();

    }

}
