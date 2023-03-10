package blackjack.domain.player;

import blackjack.domain.card.Cards;

public final class Dealer extends Player {

    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_STAY_NUMBER = 17;


    private Dealer(final String name, final Cards cards) {
        super(name, cards);
    }

    public static Dealer create() {
        return new Dealer(DEALER_NAME, new Cards());
    }

    @Override
    public boolean isInPlaying() {
        return cards.calculateScore() < DEALER_STAY_NUMBER;
    }
}
