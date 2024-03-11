package blackjack.domain.gamer;

public record Name(String value) {

    static final String INVALID_NAME = "딜러";
    static final String ERROR_INVALID_NAME = "는 사용할 수 없는 이름입니다.";
    static final String ERROR_PLAYER_NAME_IS_NULL_OR_BLANK = "플레이어 이름을 입력해 주세요.";

    public Name {
        validateNameIsNullOrBlank(value);
        validateIsValidName(value);
    }

    private void validateNameIsNullOrBlank(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(ERROR_PLAYER_NAME_IS_NULL_OR_BLANK);
        }
    }

    private void validateIsValidName(String value) {
        if (value.equals(INVALID_NAME)) {
            throw new IllegalArgumentException(value + ERROR_INVALID_NAME);
        }
    }
}
