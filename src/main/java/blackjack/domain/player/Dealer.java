package blackjack.domain.player;

import blackjack.domain.card.Drawable;

public class Dealer extends Player {

    private static final String NAME = "딜러";
    private static final int DRAWABLE_BOUND = 16;

    public Dealer(Drawable deck) {
        super(NAME, deck);
    }

    public boolean isDrawable() {
        return getTotalNumber() <= DRAWABLE_BOUND;
    }
}
