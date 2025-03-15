package blackjack.util;

import java.util.Arrays;
import java.util.List;
import java.util.regex.PatternSyntaxException;

public final class StringParser {

    private static final String COMMA = ",";

    public static List<String> parseByComma(String input) {
        try {
            return Arrays.asList(input.replace(" ", "").split(COMMA));
        } catch (PatternSyntaxException exception) {
            throw new IllegalArgumentException("[ERROR] 입력 형식이 잘못되었습니다.");
        }
    }
}
