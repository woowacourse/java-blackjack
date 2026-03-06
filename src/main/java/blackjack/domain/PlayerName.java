package blackjack.domain;

public record PlayerName(
        String name
) {

    public PlayerName {
        name = name.strip();
        validate(name);
    }
    
    private void validate(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("플레이어 이름은 공백이 될 수 없습니다.");
        }
        if (name.length() > 5) {
            throw new IllegalArgumentException("플레이어 이름은 5자가 넘을 수 없습니다.");
        }
    }

}
