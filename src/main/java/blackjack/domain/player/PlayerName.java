package blackjack.domain.player;

public record PlayerName(String name) {

    public PlayerName {
        validateName(name);
    }

    private void validateName(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("이름의 길이는 1이상입니다.");
        }
    }
}
