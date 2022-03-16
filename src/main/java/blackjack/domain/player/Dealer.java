package blackjack.domain.player;

import blackjack.domain.card.Drawable;
import blackjack.domain.card.HoldCards;

public class Dealer extends Player {

    private static final String NAME = "딜러";
    private static final int DRAWABLE_BOUND = 16;

    public Dealer(Drawable deck) {
        super(new Name(NAME), HoldCards.drawTwoCards(deck));
    }

    public boolean isDrawable() {
        return getTotalNumber() <= DRAWABLE_BOUND;
    }
}
