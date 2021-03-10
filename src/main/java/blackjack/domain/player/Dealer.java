package blackjack.domain.player;

import blackjack.domain.card.Card;

public class Dealer extends Player {

    private static final String DEALER_NAME = "dealer";
    private static final int DRAWABLE_THRESHOLD = 16;

    private Dealer(Card first, Card second) {
        super(DEALER_NAME, 0, first, second);
    }

    public static Dealer of(Card first, Card second) {
        return new Dealer(first, second);
    }

    @Override
    public boolean drawable() {
        return score().isLessThan(DRAWABLE_THRESHOLD);
    }
}
