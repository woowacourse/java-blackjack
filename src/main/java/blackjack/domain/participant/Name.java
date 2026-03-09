package blackjack.domain.participant;

public record Name(String value) {

    private static final String BLANK_NAME_MESSAGE = "이름은 공백일 수 없습니다.";

    public Name {
        validate(value);
    }

    private void validate(final String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(BLANK_NAME_MESSAGE);
        }
    }
}
