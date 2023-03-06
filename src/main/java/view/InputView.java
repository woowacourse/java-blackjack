package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String SPLIT_DELIMITER = ",";
    private static final String YES = "y";

    private InputView() {
    }

    public static List<String> readNames() {
        final String input = scanner.nextLine();
        final List<String> names = splitAsList(input);

        validateName(names);
        return names;
    }

    private static void validateName(final List<String> names) {
        InputValidator.validatePlayerCount(names);
        InputValidator.validatePlayerNameDuplicated(names);
        InputValidator.validatePlayerNameCannotBeSameAsDealerName(names);

    }

    private static List<String> splitAsList(final String input) {
        return Arrays.stream(input.split(SPLIT_DELIMITER))
                .map(String::strip)
                .collect(Collectors.toList());
    }

    public static boolean readAnswer() {
        final String input = scanner.nextLine();

        validateAnswer(input);

        if (input.equals(YES)) {
            return true;
        }
        return false;
    }

    private static void validateAnswer(final String input) {
        InputValidator.validateAnswerYesOrNo(input);
    }
}
