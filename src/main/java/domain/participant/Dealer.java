package domain.participant;

import domain.card.Card;

public final class Dealer extends Participant {

    private static final int STANDARD_GIVEN_SCORE = 16;

    private Dealer(final String name) {
        super(name);
    }

    static Dealer create() {
        return new Dealer(DEALER_NAME.getName());
    }

    public Card getFirstCard() {
        return card.getFirstCard();
    }

    public boolean canGiveCard() {
        return card.calculateScore() <= STANDARD_GIVEN_SCORE;
    }
}
