package blackjack.domain;

public record PlayerName(
        String name
) {

    public PlayerName {
        validate(name);
    }

    private void validate(String name) {
        if (name.length() > 5) {
            throw new IllegalArgumentException("플레이어 이름은 5자가 넘을 수 없습니다.");
        }
    }

}
