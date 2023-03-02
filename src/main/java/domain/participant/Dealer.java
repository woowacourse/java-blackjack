package domain.participant;

import domain.card.Card;

public class Dealer extends Participant {
    private Dealer(final String name) {
        super(name);
    }

    public static Dealer create() {
        return new Dealer(DEALER_NAME);
    }

    public Card getFirstCard() {
        return participantCard.getFirstCard();
    }
}
