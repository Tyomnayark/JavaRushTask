package com.javarush.task.task15.task1522;

public class Earth implements Planet{
    private volatile static Earth instance;

    private Earth() {
        // Private constructor to prevent instantiation from outside
    }

    public static Earth getInstance() {
        if (instance == null) {
            synchronized (Earth.class) {
                if (instance == null) {
                    instance = new Earth();
                }
            }
        }
        return instance;
    }
}
