package blackjack.domain.gamer;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends Gamer {

    private static final String NAME = "딜러";

    private Dealer(final String name) {
        super(name);
    }

    public Dealer() {
        this(NAME);
    }

    @Override
    public List<Card> showOpenHands() {
        return cards().getCardsWithSize(1);
    }

    @Override
    public boolean canDraw() {
        return !state.isFinished() && state.satisfyRule();
    }
}
