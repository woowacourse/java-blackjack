package blackjackgame.domain.user;

public class Name {
    private final String name;

    public Name(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("플레이어 이름 형식에 맞지 않는 이름이 존재합니다.");
        }
    }

    public String getName() {
        return name;
    }
}
