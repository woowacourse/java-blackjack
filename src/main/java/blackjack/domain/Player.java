package blackjack.domain;

public class Player {
    private static final String NAME_EMPTY_ERROR = "플레이어 이름은 공백일 수 없습니다.";
    private final String name;

    public Player(String name) {
        validateEmpty(name);
        this.name = name;
    }

    private void validateEmpty(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(NAME_EMPTY_ERROR);
        }
    }

    public String getName() {
        return name;
    }
}
