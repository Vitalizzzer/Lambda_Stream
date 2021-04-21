package org.example.lambda;

import org.example.interfaces.Greeting;

public class LambdaCreation {

//    public void greet(Greeting greeting){
//        greeting.execute();
//    }


    public static void main(String[] args) {

       // LambdaCreation lambdaCreation = new LambdaCreation();

        Greeting greeting = () ->  System.out.println("Hello!");
        greeting.execute();

    }
}
