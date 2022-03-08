package blackjack.domain;

import java.util.regex.Pattern;

public class Name {
    public static final String NAME_ERROR_MESSAGE = "이름 형식에 맞게 입력해야 합니다";
    private static final Pattern NAME_PATTERN = Pattern.compile("[가-힣a-zA-Z]+");

    private static void validateName(String input) {
        if (!NAME_PATTERN.matcher(input).matches()) {
            throw new RuntimeException(NAME_ERROR_MESSAGE);
        }
    }
}
