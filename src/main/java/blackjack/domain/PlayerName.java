package blackjack.domain;

public class PlayerName {

    private final String value;

    public PlayerName(String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String value) {
        validateNotEmpty(value);
    }

    private void validateNotEmpty(String value) {
        if (value.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 이름이 빈 문자열입니다.");
        }
    }
}
