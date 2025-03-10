package view;

import exception.IllegalBlackjackInputException;
import java.util.List;

public class InputConverter {
    private static final String COMMA = ",";

    public static List<String> splitByComma(final String input) {
        validateNotNullOrBlankInput(input);
        return List.of(input.trim().split(COMMA));
    }

    public static void validateNotNullOrBlankInput(final String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalBlackjackInputException("잘못된 입력입니다. 다시 입력해주세요.");
        }
    }
}
