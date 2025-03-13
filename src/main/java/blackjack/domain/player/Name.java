package blackjack.domain.player;

public class Name {

    private final String name;

    public Name(final String name) {
        validateNameLength(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void validateNameLength(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("최소 이름의 길이는 1자 이상입니다.");
        }
    }
}
