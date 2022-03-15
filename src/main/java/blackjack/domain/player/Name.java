package blackjack.domain.player;

import java.util.regex.Pattern;

public class Name {

    private static final String NAME_ERROR_MESSAGE = "[ERROR] 입력 형식에 맞춰 입력해주세요.";
    private static final Pattern NAME_PATTERN = Pattern.compile("[가-힣a-zA-Z]+");

    private final String name;

    private Name(String name) {
        this.name = name;
        validateName(name);
    }

    private static void validateName(String input) {
        if (!NAME_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException(NAME_ERROR_MESSAGE);
        }
    }

    public static Name of(String name) {
        return new Name(name);
    }

    public String getName() {
        return name;
    }
}
