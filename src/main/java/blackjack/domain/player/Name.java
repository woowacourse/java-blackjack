package blackjack.domain.player;

public class Name {
    private final String name;

    public Name(String name) {
        validateNullEmpty(name.trim());
        this.name = name;
    }

    private void validateNullEmpty(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 플레이어 이름을 입력해야 합니다.");
        }
    }

    public String getName() {
        return name;
    }
}
