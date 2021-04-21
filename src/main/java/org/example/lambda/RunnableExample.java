package org.example.lambda;

public class RunnableExample {

    public static void main(String[] args){

        Thread myThread = new Thread(() -> System.out.println("Runnable is running..."));

        myThread.start();
    }
}
