package blackjack.domain;

public class Name {
    private static final int MINIMUM_LENGTH = 1;
    private static final int MAXIMUM_LENGTH = 10;

    private final String value;

    public Name(final String value) {
        validateLength(value);
        this.value = value;
    }

    private void validateLength(final String value) {
        if (MINIMUM_LENGTH <= value.length() && value.length() <= MAXIMUM_LENGTH) {
            return;
        }

        final String errorMessage = String.format("[ERROR] 이름의 길이는 %s 이상, %s 이하여야 합니다.",
                MINIMUM_LENGTH, MAXIMUM_LENGTH);
        throw new IllegalArgumentException(errorMessage);
    }

    public String getValue() {
        return value;
    }
}
