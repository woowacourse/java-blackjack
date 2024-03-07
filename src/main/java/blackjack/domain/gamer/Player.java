package blackjack.domain.gamer;

public class Player extends Gamer {
    private static final String NAME_EMPTY_ERROR = "공백이 아닌 플레이어를 입력해 주세요.";

    public Player(String name) {
        super(name);
        validateEmpty(name);
    }

    private void validateEmpty(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(NAME_EMPTY_ERROR);
        }
    }
}
