package blackjack.util;

import blackjack.exception.ExceptionMessage;
import java.util.Arrays;
import java.util.List;

public final class Parser {

    private Parser() {
    }

    public static List<String> parseByDelimiter(String delimiter, String input) {
        return Arrays.stream(input.split(delimiter))
                .map(String::strip)
                .toList();
    }

    public static int parseToInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_NUMBER.getMessage());
        }
    }
}
