package blackjack.model;

public class Player {
    private final String name;

    public Player(final String name) {
        validateNullAndEmptyName(name);
        this.name = name;
    }

    private void validateNullAndEmptyName(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름에는 공백을 사용할 수 없습니다.");
        }
    }

    public String getName() {
        return name;
    }
}
