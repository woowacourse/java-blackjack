package blackjack.domain.human.element;

import java.util.regex.Pattern;

public final class Name {
    private static final String NAME_ERROR_MESSAGE = "이름 형식에 맞게 입력해야 합니다";
    private static final Pattern NAME_PATTERN = Pattern.compile("[가-힣a-zA-Z]+");

    private final String value;

    private Name(final String input) {
        validateName(input);
        this.value = input;
    }

    public static Name valueOf(final String name) {
        return new Name(name);
    }

    private static void validateName(final String input) {
        if (!NAME_PATTERN.matcher(input).matches()) {
            throw new RuntimeException(NAME_ERROR_MESSAGE);
        }
    }

    public String get() {
        return value;
    }

    @Override
    public String toString() {
        return "Name{" +
                "value='" + value + '\'' +
                '}';
    }
}
