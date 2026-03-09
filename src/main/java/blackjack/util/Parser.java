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

    public static boolean yesOrNo(String value) {
        if (value.equals(YES)) {
            return true;
        }
        if (value.equals(NO)) {
            return false;
        }
        throw new IllegalArgumentException("y 혹은 n으로 입력해야 합니다.");
    }

}
