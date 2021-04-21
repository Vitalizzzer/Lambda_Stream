package org.example.lambda;

import org.example.interfaces.MyComparator;
import org.example.interfaces.ZeroParams;

import java.util.Comparator;
import java.util.function.IntFunction;

public class LambdaSyntax {

    public static void main(String[] args) {
        // No Params
        ZeroParams zero = () -> System.out.println("Hello, friend!");
        zero.doSmth();

        // 1 param
        IntFunction<Integer> integerIntFunction = p -> p + 5;
        Integer apply = integerIntFunction.apply(3);
        System.out.println(apply);

        // Many params
        Comparator<Integer> tComparator = (p, t) -> p + t;
        System.out.println(tComparator.compare(5,7));

        // Many params with code block
        MyComparator myComparator = (int p, int t) -> {
          if(p > t){
              System.out.println(p + " is bigger than "+ t);
          }
          else{
              System.out.println(p + " is smaller than "+ t);
          }
        };

        myComparator.compare(6, 9);
        myComparator.compare(24, 4);
    }
}
