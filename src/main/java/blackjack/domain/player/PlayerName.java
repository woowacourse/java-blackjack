package blackjack.domain.player;

public record PlayerName(String value) {

    public PlayerName {
        validateNotEmpty(value);
    }

    private void validateNotEmpty(String value) {
        if (value.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 이름이 빈 문자열입니다.");
        }
    }
}
