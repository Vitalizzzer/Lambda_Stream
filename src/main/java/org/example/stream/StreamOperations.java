package org.example.stream;

import org.example.model.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamOperations {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("first", "second", "third", "fourth", "fifth");
        List<String> stringList = new ArrayList<>(strings);

        List<List<String>> stringListOfLists = new ArrayList<>();
        stringListOfLists.add(Arrays.asList("first", "second", "third", "fourth", "fifth"));
        stringListOfLists.add(Arrays.asList("sixth", "seventh", "eighth", "ninth"));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////// INTERMEDIATE ////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////


        // Filter
        stringList.stream()
                .filter(v -> v.contains("i"))
                .forEach(System.out::println);

        // Map
        stringList.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);

        // Flatmap
        stringListOfLists.stream()
                .flatMap(Collection::stream)
                .forEach(System.out::println);

        // See difference with map:
        stringListOfLists.stream()
                .map(Collection::stream)
                .forEach(System.out::println);

        // Peek
        stringList.stream()
                //.peek(value ->  value.toUpperCase())
                .peek(System.out::println)
                .forEach(System.out::println);

        // Peek changed inner state of an element withing an object
        Stream<User> userStream = Stream.of(
                new User("John", "Smith", 25),
                new User("Sarah", "Conor", 72));

        userStream
                .peek(u -> u.setFirstName(u.getFirstName().toUpperCase()))
                .forEach(u -> System.out.println(u.toString()));

        // Distinct
        stringList.add("second");
        stringList.forEach(System.out::println);

        System.out.println("##########################");

        stringList.stream()
                .distinct()
                .forEach(System.out::println);

        // Sorted
        stringList.stream()
                .sorted()
                .forEach(System.out::println);

        // Limit
        stringList.stream()
                .limit(2)
                .forEach(System.out::println);

        AtomicInteger j = new AtomicInteger();

        List<Integer> array = Arrays.asList(1,2,3,4,5);

        array.forEach(v -> {
            int i = 0;

            if(v == 3){
                i = v;
            }

            j.set(i);
        });

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////// TERMINAL ////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Collect
        List<String> newList = stringList.stream()
                .map(p -> {
                    String item = p;
                    if (p.contains("i")) {
                        item = p.replace("i", "I");
                    }
                    return item;
                })
                .collect(Collectors.toList());


        newList.forEach(System.out::println);

        // Reduce
        String reduce = stringList.stream()
                .reduce("", (partialString, element) -> partialString + element);
        System.out.println(reduce);

        // ALlMatch
        boolean isAllMatch = stringList.stream()
                .allMatch(p -> p.length() >= 5);
        System.out.println(isAllMatch);

        // AnyMatch
        boolean isAnyMatch = stringList.stream()
                .anyMatch(p -> p.contains("first"));
        System.out.println(isAnyMatch);

        // NoneMatch
        boolean isNoneMatch = stringList.stream()
               .noneMatch(p -> p.contains("first"));
        System.out.println(isNoneMatch);

        // FindFirst
        Optional<String> first = stringList.stream()
                .findFirst();
        System.out.println(first);

        // FindAny
        Optional<String> any = stringList.stream()
                .findAny();
        System.out.println(any);

        // Count
        long count = stringList.stream()
                .count();
        System.out.println(count);

        // Min
        Optional<String> min = stringList.stream()
                        .min(Comparator.naturalOrder());
        System.out.println(min);

        // Max
        Optional<String> max = stringList.stream()
                .max(Comparator.naturalOrder());
        System.out.println(max);

        // Parallel Stream
        stringList.parallelStream()
                .forEach(System.out::println);

        stringList.stream()
                .parallel()
                .forEach(System.out::println);

        // Workflow order
        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("A");
                })
                .forEach(s -> System.out.println("forEach: " + s));


        System.out.println("###################################");

        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));


        // Collect to map
        HashMap<Integer, String> nameMap = new HashMap<>();

        nameMap.put(1, "Victor");
        nameMap.put(2, "Olga");
        nameMap.put(3, "Ivan");
        nameMap.put(4, "Bohdan");
        nameMap.put(5, "Anatolii");
        nameMap.put(6, "John");

        Map<Integer, String> map = nameMap.entrySet().stream()
                .filter(p -> p.getValue().length() == 4)
                .peek(System.out::println)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));


        // Collect groupingBy
        User user1 = new User("Victor", "Petrenko", 23);
        User user2 = new User("John", "Smith", 18);
        User user3 = new User("Olga", "Melnyk", 41);
        User user4 = new User("Ivan", "Balamut", 18);

        List<User> users = Arrays.asList(user1, user2, user3, user4);

        Map<Integer, List<User>> grouped = users.stream()
                .collect(Collectors.groupingBy(User::getAge));
        grouped.forEach((age, user) -> System.out.format("Age %s: %s\n", age, user));

    }
}
