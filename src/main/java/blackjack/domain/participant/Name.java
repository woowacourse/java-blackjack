package blackjack.domain.participant;

import java.util.regex.Pattern;

public class Name {

    static final String EMPTY_NAME_EXCEPTION_MESSAGE = "[ERROR] 이름에 빈 값을 입력할 수 없습니다.";
    public static final String NOT_ALLOWED_SPECIFIC_CHARACTER_MESSAGE = "[ERROR] 이름에 특수문자는 허용하지 않습니다.";
    private static final Pattern NAME_PATTERN = Pattern.compile("[^ \\wㄱ-ㅎ가-힣]");

    private final String value;

    public Name(String value) {
        validateName(value);
        value = value.trim();
        this.value = value;
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_NAME_EXCEPTION_MESSAGE);
        }

        if (containNotAllowedPattern(name)) {
            throw new IllegalArgumentException(NOT_ALLOWED_SPECIFIC_CHARACTER_MESSAGE);
        }
    }

    private boolean containNotAllowedPattern(String name) {
        return NAME_PATTERN.matcher(name).find();
    }

    public String getValue() {
        return value;
    }
}
