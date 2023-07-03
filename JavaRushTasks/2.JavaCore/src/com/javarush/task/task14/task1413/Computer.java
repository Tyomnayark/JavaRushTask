package com.javarush.task.task14.task1413;

public class Computer {
    private Keyboard keyboard;
    private Mouse mouse;
    private Monitor monitor;
    public  Computer (Keyboard k, Mouse m,Monitor mon){
        this.keyboard = k;
        this.mouse = m;
        this.monitor = mon;
    }
    public Keyboard getKeyboard(){
        return keyboard;
    }
    public Monitor getMonitor(){
        return monitor;
    }
    public Mouse getMouse(){
        return mouse;
    }
}
