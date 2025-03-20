package view;

import exception.IllegalBlackjackInputException;
import java.util.Arrays;
import java.util.List;

public class InputConverter {

    public static List<String> splitByDelimiter(final String input, final String delimiter) {
        validateNotNullOrBlankInput(input);
        return Arrays.stream(input.split(delimiter))
                .map(String::trim)
                .filter(value -> !value.isBlank())
                .toList();
    }

    public static int parseToInt(final String input) {
        validateNotNullOrBlankInput(input);
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalBlackjackInputException("입력이 숫자가 아닙니다.");
        }
    }

    public static void validateNotNullOrBlankInput(final String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalBlackjackInputException("잘못된 입력입니다. 다시 입력해주세요.");
        }
    }
}
