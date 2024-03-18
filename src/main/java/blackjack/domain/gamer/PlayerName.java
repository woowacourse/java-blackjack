package blackjack.domain.gamer;

public class PlayerName {
    private static final String NAME_EMPTY_ERROR = "공백이 아닌 플레이어를 입력해 주세요.";
    private final String name;

    public PlayerName(String name) {
        validateBlank(name);
        this.name = name;
    }

    private static void validateBlank(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(NAME_EMPTY_ERROR);
        }
    }

    public String get() {
        return name;
    }
}
