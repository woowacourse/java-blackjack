package blackjack.util;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private static final String YES = "y";
    private static final String NO = "n";

    public static void notEmpty(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("입력이 비어있습니다.");
        }
    }

    public static List<String> splitDelimiter(String playerNames) {
        String[] values = playerNames.split(",");

        List<String> splitResult = new ArrayList<>();
        for (String part : values) {
            splitResult.add(part.trim());
        }

        return splitResult;
    }

    public static boolean parseAnswer(String input) {
        notEmpty(input);
        yesOrNo(input);
        return input.equals(YES);
    }

    private static void yesOrNo(String input) {
        if (!input.equals(YES) && !input.equals(NO)) {
            throw new IllegalArgumentException("y 또는 n만 입력 가능합니다.");
        }
    }
}
