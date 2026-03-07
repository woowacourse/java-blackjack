package blackjack.utils;

import java.util.List;

public class Parser {

    public static List<String> parseNickname(final String string) {
        final String delimiter = ",";

        return List.of(string.split(delimiter));
    }
}
