package blackjack.domain.participant;

public class Name {

    private static final String BLANK_NAME_MESSAGE = "이름은 공백일 수 없습니다.";

    private final String value;

    public Name(final String value) {
        validate(value);
        this.value = value;
    }

    private void validate(final String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(BLANK_NAME_MESSAGE);
        }
    }

    public String getValue() {
        return value;
    }
}
