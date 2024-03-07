package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String INPUT_PARSE_DELIMITER = ",";
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String input(String inputRequestMessage) {
        System.out.println(inputRequestMessage);
        String userInput = scanner.nextLine();
        System.out.print(System.lineSeparator());
        return userInput;
    }

    public static List<String> inputNames(String inputRequestMessage) {
        String initialInput = input(inputRequestMessage);
        return Arrays.stream(initialInput.split(INPUT_PARSE_DELIMITER))
                .map(String::trim)
                .toList();
    }
}
