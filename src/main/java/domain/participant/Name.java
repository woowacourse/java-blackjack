package domain.participant;

import java.util.regex.Pattern;

public class Name {

    private static final Pattern NAME_REGEX = Pattern.compile("^[a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣|\\s]*$");
    public static final String FORMAT_ERROR_MESSAGE = "[ERROR] 이름에 숫자나 특수문자가 포함될 수 없습니다.";
    public static final String LENGTH_ERROR_MESSAGE = "[ERROR] 이름의 길이는 10글자 이하여야 합니다.";
    public static final String NULL_ERROR_MESSAGE = "[ERROR] 이름에는 null이나 빈 값이 들어갈 수 없습니다.";
    private final String name;

    public Name(final String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        validateNull(name);
        validateLength(name);
        validateFormat(name);
    }

    private void validateFormat(final String name) {
        if (!NAME_REGEX.matcher(name).matches()) {
            throw new IllegalArgumentException(FORMAT_ERROR_MESSAGE);
        }
    }

    private void validateLength(final String name) {
        if (name.length() > 10) {
            throw new IllegalArgumentException(LENGTH_ERROR_MESSAGE);
        }
    }

    private void validateNull(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(NULL_ERROR_MESSAGE);
        }
    }

    public String getName() {
        return name;
    }
}
