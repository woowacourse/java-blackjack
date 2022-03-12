package blackjack.domain.participant;

import blackjack.domain.card.Card;

public class Dealer extends Participant {

    private static final int DEALER_HIT_THRESHOLD_NUMBER = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean checkMustHit() {
        return getScore() <= DEALER_HIT_THRESHOLD_NUMBER;
    }

    public Card getFirstCard() {
        return cards.getCards().get(0);
    }
}
