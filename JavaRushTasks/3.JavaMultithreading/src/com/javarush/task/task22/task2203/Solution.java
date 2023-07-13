package com.javarush.task.task22.task2203;

/* 
Между табуляциями
*/

public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException{
        if (string==null){
            throw new TooShortStringException();
        }
        int count =0;
        for (char ch: string.toCharArray()) {
            if (ch == '\t' ){
                count++;
            }
        }
        if (count<2) {
            throw new TooShortStringException();
        }
        StringBuilder stringBuilder = new StringBuilder(string);
        int startIndex = stringBuilder.indexOf("\t");
        stringBuilder.deleteCharAt(startIndex);
        int endIndex = stringBuilder.indexOf("\t");

        return stringBuilder.substring(startIndex,endIndex);
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
    }
}
