package domain.participant;

import domain.card.Card;
import domain.card.Score;

public final class Dealer extends Participant {

    private static final Score STANDARD_GIVEN_SCORE = Score.create(16);

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
        return STANDARD_GIVEN_SCORE.isGreaterThanAndEqual(card.calculateScore());
    }
}
