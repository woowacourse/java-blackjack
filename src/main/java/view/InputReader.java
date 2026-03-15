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

    public int readInteger() {
        String input = readInput();
        return Integer.parseInt(input);
    }

    public List<String> readInputBasedOnSeparator(String separator) {
        String input = readInput();
        return Arrays.stream(input.split(separator))
                .map(String::trim)
                .toList();
    }

    public String readInputOnlyCandidate(List<String> candidates) {
        String input = readInput();
        validateInCandidate(candidates, input);
        return input;
    }

    private void validateInCandidate(List<String> candidates, String input) {
        if (!candidates.contains(input)) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }
}
