package blackjack.domain;

public class Name {
    private static final int NAME_LENGTH_LOWER_BOUND = 1;
    private static final int NAME_LENGTH_UPPER_BOUND = 5;
    static final String INVALID_NAME_LENGTH_MESSAGE =
            "이름은 " + NAME_LENGTH_LOWER_BOUND + "자 이상, " + NAME_LENGTH_UPPER_BOUND + "자 이하여아 합니다. 입력값:";
    private static final String RESERVED_NAME = "딜러";
    static final String RESERVED_NAME_MESSAGE = "이름은 " + RESERVED_NAME + " 일 수 없습니다.";

    private final String value;

    private Name(final String value) {
        validate(value);
        this.value = value;
    }

    private static void validate(final String value) {
        if (isValidName(value)) {
            throw new IllegalArgumentException(INVALID_NAME_LENGTH_MESSAGE + value);
        }
    }

    private static boolean isValidName(final String value) {
        return value == null || value.length() < NAME_LENGTH_LOWER_BOUND || NAME_LENGTH_UPPER_BOUND < value.length();
    }

    public static Name from(String value) {
        if (RESERVED_NAME.equals(value)) {
            throw new IllegalArgumentException(RESERVED_NAME_MESSAGE);
        }
        return new Name(value);
    }

    public static Name createDealerName() {
        return new Name(RESERVED_NAME);
    }

    public String getValue() {
        return value;
    }
}
