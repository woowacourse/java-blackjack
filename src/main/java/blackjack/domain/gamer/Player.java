package blackjack.domain.gamer;

public class Player extends Gamer {
    private static final String NAME_EMPTY_ERROR = "공백이 아닌 플레이어를 입력해 주세요.";

    private final String name;

    protected Player(String name) {
        super();
        validateBlank(name);
        this.name = name;
    }

    private void validateBlank(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(NAME_EMPTY_ERROR);
        }
    }

    public boolean canContinue() {
        return !(isBust() || isMaxScore());
    }

    public String getName() {
        return name;
    }
}
