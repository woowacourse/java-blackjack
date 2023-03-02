package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final int SPLIT_LIMIT = -1;
    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> askPlayerNames() {
        String line = scanner.nextLine();
        return parseByDelimiter(line, ",");
    }

    private static List<String> parseByDelimiter(String line, String delimiter) {
        return Arrays.stream(line.split(delimiter, SPLIT_LIMIT))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
