package blackjack.domain.gamer;

import blackjack.domain.card.Card;

public class Dealer extends Gamer {
    private static final int ADDITIONAL_DISTRIBUTE_STANDARD = 16;
    private static final String NAME = "딜러";

    public Dealer() {
        super(NAME);
    }

    @Override
    boolean canDraw() {
        return getCardsNumberSum() <= ADDITIONAL_DISTRIBUTE_STANDARD;
    }
}
