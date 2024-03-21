package blackjack.domain.gamer.player;

import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Gamer;

public class Player extends Gamer {
    private static final String NAME_EMPTY_ERROR = "공백이 아닌 플레이어를 입력해 주세요.";

    private final String name;

    public Player(String name, Deck deck) {
        super(deck);
        validateBlank(name);
        this.name = name;
    }


    private void validateBlank(String name) {
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
