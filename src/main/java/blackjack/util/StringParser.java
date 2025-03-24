package blackjack.util;

import java.util.Arrays;
import java.util.List;
import java.util.regex.PatternSyntaxException;

public final class StringParser {

    private static final String COMMA = ",";

    public static List<String> parseByComma(final String input) {
        try {
            return Arrays.asList(input.replace(" ", "").split(COMMA));
        } catch (PatternSyntaxException exception) {
            throw new IllegalArgumentException(ExceptionMessage.makeMessage("%s는 입력 형식이 잘못되었습니다.", input));
        }
    }

    public static int parseInt(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ExceptionMessage.makeMessage("%s은 숫자 형식이 아닙니다.", input));
        }
    }
}
