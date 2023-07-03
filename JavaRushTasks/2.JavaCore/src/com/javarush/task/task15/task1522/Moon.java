package com.javarush.task.task15.task1522;

public class Moon implements Planet{
    private volatile static Moon instance;

    private Moon() {
        // Private constructor to prevent instantiation from outside
    }

    public static Moon getInstance() {
        if (instance == null) {
            synchronized (Moon.class) {
                if (instance == null) {
                    instance = new Moon();
                }
            }
        }
        return instance;
    }
}
