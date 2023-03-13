package blackjack.domain.player;

import blackjack.domain.card.Cards;

public final class Dealer extends Player {

    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_STAY_NUMBER = 17;

    private Dealer(final Cards cards) {
        super(cards);
    }

    public static Dealer create() {
        return new Dealer(new Cards());
    }

    @Override
    public String getName() {
        return DEALER_NAME;
    }

    @Override
    public boolean isInPlaying() {
        return cards.calculateScore() < DEALER_STAY_NUMBER;
    }
}
