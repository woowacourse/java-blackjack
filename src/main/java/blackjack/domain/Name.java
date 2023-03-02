package blackjack.domain;

public class Name {

    private static final int UPPER_BOUND = 10;
    private static final String RESTRICT = "딜러";

    private final String value;

    public Name(final String value) {
        validate(value);
        this.value = value;
    }

    private void validate(final String value) {
        validateBlank(value);
        validateLength(value);
        validateRestrictWord(value);
    }

    private void validateBlank(final String value) {
        if (value.isBlank()) {
            throw new IllegalArgumentException("이름은 존재해야 합니다. 현재 이름: " + value);
        }
    }

    private void validateLength(final String value) {
        if (value.length() > UPPER_BOUND) {
            throw new IllegalArgumentException("이름은 " + UPPER_BOUND + "글자 이하여야 합니다. 현재 이름: " + value);
        }
    }

    private void validateRestrictWord(final String value) {
        if (value.equals(RESTRICT)) {
            throw new IllegalArgumentException("이름은 " + RESTRICT + "일 수 없습니다. 현재 이름: " + value);
        }
    }
}
