package domain;

import java.util.regex.Pattern;

public class Player {

    private static final String STRING_REGEX = "^[a-zA-Z]*$";

    private final String name;

    public Player(String name) {
        validateNameLength(name);
        validateOnlyEnglish(name);
        this.name = name;
    }

    private void validateNameLength(String name) {
        if (name.isEmpty() || name.length() > 8) {
            throw new IllegalArgumentException("플레이어 이름은 1글자 이상 8글자 이하여야 합니다.");
        }
    }

    private void validateOnlyEnglish(String name) {
        if (!Pattern.matches(STRING_REGEX, name)) {
            throw new IllegalArgumentException("플레이어 이름은 영문자만 포함되어야 합니다.");
        }
    }
}
