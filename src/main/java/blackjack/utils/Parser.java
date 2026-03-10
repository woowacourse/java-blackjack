package blackjack.utils;

import java.util.List;

public final class Parser {

    private Parser() {

    }

    public static List<String> parseNickname(final String string) {
        final String delimiter = ",";

        return List.of(string.split(delimiter));
    }
}
