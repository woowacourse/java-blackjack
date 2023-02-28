package blackjack.domain;

public class Name {
    private static final int NAME_LENGTH_LOWER_BOUND = 1;
    private static final int NAME_LENGTH_UPPER_BOUND = 5;
    static final String INVALID_NAME_LENGTH_MESSAGE =
            "이름은 " + NAME_LENGTH_LOWER_BOUND + "자 이상, " + NAME_LENGTH_UPPER_BOUND + "자 이하여아 합니다. 입력값:";

    private final String name;

    public Name(final String name) {
        validate(name);
        this.name = name;
    }

    private void validate(final String name) {
        if (isValidName(name)) {
            throw new IllegalArgumentException(INVALID_NAME_LENGTH_MESSAGE + name);
        }
    }

    private boolean isValidName(final String name) {
        return name == null || name.length() < NAME_LENGTH_LOWER_BOUND || NAME_LENGTH_UPPER_BOUND < name.length();
    }
}
