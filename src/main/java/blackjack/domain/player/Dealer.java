package blackjack.domain.player;

import blackjack.domain.card.Card;

public class Dealer extends Player {

    private static final String DEALER_NAME = "dealer";
    private static final int DRAWABLE_THRESHOLD = 16;

    private Dealer(String name, Card first, Card second) {
        super(name, first, second);
    }

    public static Dealer of(Card first, Card second) {
        return new Dealer(DEALER_NAME, first, second);
    }

    @Override
    public boolean drawable() {
        return score().isLessThan(DRAWABLE_THRESHOLD);
    }
}
