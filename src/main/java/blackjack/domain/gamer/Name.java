package blackjack.domain.gamer;

public record Name(String value) {

    static final String INVALID_NAME = "딜러";
    static final String ERROR_INVALID_NAME = "는 사용할 수 없는 이름입니다.";

    public Name {
        validateIsValidName(value);
    }

    private void validateIsValidName(String value) {
        if (value.equals(INVALID_NAME)) {
            throw new IllegalArgumentException(value + ERROR_INVALID_NAME);
        }
    }
}
