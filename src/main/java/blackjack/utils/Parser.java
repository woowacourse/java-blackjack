package blackjack.utils;

import java.util.List;

public final class Parser {

    private static final String DELIMITER = ",";

    private Parser() {

    }

    public static List<String> parseNickname(final String string) {
        return List.of(string.split(DELIMITER));
    }
}
