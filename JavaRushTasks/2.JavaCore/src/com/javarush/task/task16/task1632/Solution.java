package com.javarush.task.task16.task1632;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/* 
Клубок
*/

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);

    public static void main(String[] args) {

    }
    static {
        threads.add(new MyThread());
        threads.add(new MyThread2());
        threads.add(new MyThread3());
        threads.add(new MyThread4());
        threads.add(new MyThread5());

    }
   static class MyThread extends Thread{
        @Override
        public void run() {
            while (true){
            }
        }
    }
    static  class MyThread2 extends Thread{
        @Override
        public void run() {
        try {
             Thread.sleep(1);
        }catch (InterruptedException interruptedException){
            System.out.println("InterruptedException");
        }
        }
    }
    static class MyThread3 extends Thread{
        @Override
        public void run() {
            while (true) {
                System.out.println("Ура");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    static class MyThread4 extends Thread implements Message{
        private boolean interrupted = false;
        @Override
        public void run() {
            while (!interrupted){
                run();
            }
        }

        @Override
        public void showWarning() {
            interrupted = true;
        }
    }
    static class MyThread5 extends Thread {
        private boolean interrupted = false;
        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);
            List<Integer> list = new ArrayList<>();
            while (true){
                try{
                    int  integer = scanner.nextInt();
                    list.add(integer);
                }catch (Exception exc){
                    int sum = 0;
                    for (Integer i: list ) {
                        sum = sum + i;
                    }
                    System.out.println(sum);
                }

            }
        }

    }
    }