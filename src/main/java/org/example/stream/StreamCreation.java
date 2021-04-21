package org.example.stream;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.example.utils.Printer.printIntStream;
import static org.example.utils.Printer.printStream;

public class StreamCreation {

    public static void main(String[] args) throws IOException {

        // Collection stream
        List<String> collection = Arrays.asList("a", "b", "c");
        Stream<String> streamOfCollection = collection.stream();

        printStream(streamOfCollection);

        // Array stream
        Stream<String> streamOfArray = Stream.of("a", "b", "c");
        printStream(streamOfArray);

        // Values stream
        Stream<Integer> streamOfIntegers = Stream.of(1, 2, 3);
        printStream(streamOfIntegers);

        IntStream intStream = IntStream.range(1, 3);
        printIntStream(intStream);

        IntStream intStreamClosed = IntStream.rangeClosed(1, 3);
        printIntStream(intStreamClosed);

        // String stream
        IntStream streamOfChars = "abc".chars();
        printIntStream(streamOfChars);

        Stream<String> streamOfString = Pattern.compile(", ").splitAsStream("a, b, c");
        printStream(streamOfString);

        // File stream
        File file = getFile("some_file.txt");
        Stream<String> streamOfStrings = Files.lines(file.toPath());
        printStream(streamOfStrings);

        Stream<String> streamWithCharset = Files.lines(file.toPath(), StandardCharsets.UTF_8);
        printStream(streamWithCharset);

        // Stream builder()
        Stream<String> streamBuilder = Stream.<String> builder()
                .add("a")
                .add("b")
                .add("c").build();
        printStream(streamBuilder);

    }

    private static File getFile(String fileName) {
        File file = null;
        ClassLoader classLoader = StreamCreation.class.getClassLoader();

        URL url = classLoader.getResource(fileName);
        if (url != null) {
            file = new File(url.getFile());
        }

        return file;
    }
}
