package domain.participant;

import util.Constants;

public class Name {

    public static final String NAME_REGEX_FORMAT = "^[a-zA-Z가-힣]+$";
    public static final String NAME_EMPTY_ERROR_MESSAGE = Constants.ERROR_PREFIX + "이름은 빈칸이 포함될 수 없습니다.";
    public static final String NAME_FORMAT_ERROR_MESSAGE = Constants.ERROR_PREFIX+ "이름은 영어만 가능합니다.";

    private final String value;

    public Name(final String value) {
        validateName(value);
        this.value = value;
    }

    private void validateName(String name) {
        if (name.isBlank() || isContainBlank(name)) {
            throw new IllegalArgumentException(NAME_EMPTY_ERROR_MESSAGE);
        }
        if (!name.matches(NAME_REGEX_FORMAT)) {
            throw new IllegalArgumentException(NAME_FORMAT_ERROR_MESSAGE);
        }
    }

    private boolean isContainBlank(String name) {
        return name.replaceAll("\\s", "").length() != name.length();
    }

    public String getValue() {
        return value;
    }
}
