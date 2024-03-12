package blackjack.domain;

public record PlayerName(String name) {
    public PlayerName {
        if (name.isBlank()) {
            throw new IllegalArgumentException("이름이 비어있습니다.");
        }
    }
}
