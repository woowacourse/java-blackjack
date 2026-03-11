package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputReader {

    private final Scanner scanner;

    public InputReader() {
        this.scanner = new Scanner(System.in);
    }

    public String readInput() {
        return scanner.nextLine();
    }

    public List<String> readInputBasedOnSeparator(String separator) {
        String input = readInput();
        return Arrays.stream(input.split(separator))
                .map(String::trim)
                .toList();
    }
}
