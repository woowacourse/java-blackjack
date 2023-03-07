package domain.participant;

import domain.card.Card;

public final class Dealer extends Participant {

    private static final int STANDARD_GIVEN_SCORE = 16;

    private Dealer(final String name) {
        super(name);
    }

    public static Dealer create() {
        return new Dealer(DEALER_NAME.getName());
    }

    public Card getFirstCard() {
        return participantCard.getFirstCard();
    }

    public boolean canGiveCard() {
        return participantCard.calculateScore() <= STANDARD_GIVEN_SCORE;
    }
}
