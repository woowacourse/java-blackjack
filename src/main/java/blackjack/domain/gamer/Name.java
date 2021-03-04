package blackjack.domain.gamer;

public class Name {
    private static final String WRONG_PLAYER_NAME_ERROR_MESSAGE = "유효하지 않은 플레이어 이름입니다.";

    private final String name;

    public Name(String name) {
        validateNameLength(name);
        this.name = name;
    }

    private void validateNameLength(String name) {
        if (name.length() < 1) {
            throw new IllegalArgumentException(WRONG_PLAYER_NAME_ERROR_MESSAGE);
        }
    }

    public String getName() {
        return this.name;
    }
}
