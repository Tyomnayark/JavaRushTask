package com.javarush.task.task13.task1326;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/*
Сортировка четных чисел из файла
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String sourceFileName = reader.readLine();
        FileInputStream fileInputStream = new FileInputStream(sourceFileName);

        Scanner scanner = new Scanner(fileInputStream);
        List<Integer> list=new ArrayList<>();
        while (scanner.hasNextLine()) {
            list.add(scanner.nextInt());

        }
        list.sort(Comparator.comparingInt(o -> o));
        List<Integer> list2=  list.stream().filter(o->o.intValue()%2==0).collect(Collectors.toList());
        list2.forEach(System.out::println);
        scanner.close();
        reader.close();
    }
}