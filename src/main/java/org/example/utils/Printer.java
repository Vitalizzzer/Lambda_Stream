package org.example.utils;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Printer {

    public static <T> void printStream(Stream<T> stream){
        System.out.println("################################");
        stream.forEach(System.out::println);
        System.out.println("################################");
    }

    public static void printIntStream(IntStream stream){
        System.out.println("################################");
        stream.forEach(System.out::println);
        System.out.println("################################");
    }
}
