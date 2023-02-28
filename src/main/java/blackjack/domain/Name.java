package blackjack.domain;

public class Name {
    private static final int NAME_LENGTH_LOWER_BOUND = 1;
    private static final int NAME_LENGTH_UPPER_BOUND = 5;
    static final String INVALID_NAME_LENGTH_MESSAGE =
            "이름은 " + NAME_LENGTH_LOWER_BOUND + "자 이상, " + NAME_LENGTH_UPPER_BOUND + "자 이하여아 합니다. 입력값:";

    private final String value;

    public Name(final String value) {
        validate(value);
        this.value = value;
    }

    private void validate(final String value) {
        if (isValidName(value)) {
            throw new IllegalArgumentException(INVALID_NAME_LENGTH_MESSAGE + value);
        }
    }

    private boolean isValidName(final String value) {
        return value == null || value.length() < NAME_LENGTH_LOWER_BOUND || NAME_LENGTH_UPPER_BOUND < value.length();
    }

    public String getValue() {
        return value;
    }
}
