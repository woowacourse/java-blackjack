package blackjack.domain.player;

public class Name {
    private static final String NOT_EXIST_NAME_ERROR = "[ERROR] 플레이어 이름을 입력해야 합니다.";

    private final String name;

    public Name(String name) {
        validateNullEmpty(name.trim());
        this.name = name;
    }

    private void validateNullEmpty(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(NOT_EXIST_NAME_ERROR);
        }
    }

    public String getName() {
        return name;
    }
}
