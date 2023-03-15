package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class InputView {

    private static final String SPLIT_DELIMITER = ",";
    private static final String YES = "y";

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> readNames() {
        final String input = scanner.nextLine();
        final List<String> names = splitAsList(input);
        validateNames(names);
        return names;
    }

    private void validateNames(final List<String> names) {
        InputValidator.validatePlayerCount(names);
        InputValidator.validatePlayerNameDuplicated(names);
        InputValidator.validatePlayerNameCannotBeSameAsDealerName(names);

    }

    private List<String> splitAsList(final String input) {
        return Arrays.stream(input.split(SPLIT_DELIMITER))
                .map(String::strip)
                .collect(Collectors.toList());
    }

    public boolean readAnswer() {
        final String input = scanner.nextLine();
        validateAnswer(input);

        if (input.equals(YES)) {
            return true;
        }
        return false;
    }

    private void validateAnswer(final String input) {
        InputValidator.validateAnswerYesOrNo(input);
    }

    public int readBetMoney() {
        final String input = scanner.nextLine();
        validateBetMoney(input);

        return Integer.parseInt(input);
    }

    private void validateBetMoney(final String input) {
        InputValidator.validateIsNumeric(input);
    }
}
