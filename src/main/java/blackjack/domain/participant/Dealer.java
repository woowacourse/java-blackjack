package blackjack.domain.participant;

import blackjack.domain.card.Card;

public class Dealer extends Participant {

    private static final int DEALER_HIT_THRESHOLD_NUMBER = 16;
    private static final String DEALER_NAME = "딜러";

    private final String name = DEALER_NAME;

    public boolean checkHitRule() {
        return super.getScore() <= DEALER_HIT_THRESHOLD_NUMBER;
    }

    public String getName() {
        return name;
    }

    public Card getFirstCard() {
        return super.getCards().get(0);
    }

}
