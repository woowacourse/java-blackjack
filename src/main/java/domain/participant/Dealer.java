package domain.participant;

import domain.card.Card;
import domain.card.Hand;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    public Card openOneCard() {
        Hand hand = getHand();
        return hand.getFirstCard();
    }

    public int getExtraHandSize() {
        Hand hand = getHand();
        return hand.getExtraSize();
    }
}
