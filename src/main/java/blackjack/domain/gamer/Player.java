package blackjack.domain.gamer;

import blackjack.domain.card.Deck;

public class Player extends Gamer {
    private static final String NAME_EMPTY_ERROR = "공백이 아닌 플레이어를 입력해 주세요.";

    private final String name;

    private Player(String name, Deck deck) {
        super(deck);
        this.name = name;
    }

    public static Player of(String name, Deck deck) {
        validateBlank(name);
        return new Player(name, deck);
    }

    private static void validateBlank(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(NAME_EMPTY_ERROR);
        }
    }

    public boolean canContinue() {
        return !isBlackjack() && isUnderBound();
    }

    public String getName() {
        return name;
    }
}
