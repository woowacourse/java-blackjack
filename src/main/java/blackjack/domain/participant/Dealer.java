package blackjack.domain.participant;

import blackjack.domain.card.Card;

public class Dealer extends Participant {

    private static final int HIT_THRESHOLD = 16;

    private static final String DEALER_NAME = "딜러";

    private final String name = DEALER_NAME;

    public boolean isHittable() {
        return getScore() <= HIT_THRESHOLD;
    }

    public String getName() {
        return name;
    }

    public Card getFirstCard() {
        return participantCards.getFirstCard();
    }

}
