package blackjack.utils;

import java.util.List;

public final class Parser {

    private static final String DELIMITER = ",";

    private Parser() {

    }

    public static List<String> parseNickname(final String string) {
        return List.of(string.split(DELIMITER));
    }

    public static int parseInteger(final String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("정수만 입력 가능합니다.");
        }
    }
}
