package blackjack.domain.player;

import java.util.regex.Pattern;

public class Name {

    private static final String NAME_ERROR_MESSAGE = "[ERROR] 입력 형식에 맞춰 입력해주세요.";
    private static final String NULL_NAMES_ERROR_MESSAGE = "[ERROR] 이름에 공백은 포함될 수 없습니다.";
    private static final Pattern NAME_PATTERN = Pattern.compile("[가-힣a-zA-Z]+");
    public static final String NULL_NAME = "";
    public static final String SPACE = " ";

    private final String name;

    private Name(String name) {
        this.name = name;
        validateNullName(name);
        validateName(name);
    }

    public static Name of(String name) {
        return new Name(name);
    }

    private static void validateNullName(String inputName) {
        if (inputName.equals(NULL_NAME) || inputName.contains(SPACE) || inputName == null) {
            throw new IllegalArgumentException(NULL_NAMES_ERROR_MESSAGE);
        }
    }

    private static void validateName(String input) {
        if (!NAME_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException(NAME_ERROR_MESSAGE);
        }
    }

    public String getName() {
        return name;
    }
}
