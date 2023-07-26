package com.javarush.task.task24.task2411;

public interface JustAnInterface {
    com.javarush.task.task24.task2411.JustAnInterface.B B = new com.javarush.task.task24.task2411.JustAnInterface.B();

    class B extends C {
        public B() {
            System.out.print("B");
        }
    }
}
