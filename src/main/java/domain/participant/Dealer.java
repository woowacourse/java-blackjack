package domain.participant;

import domain.card.Card;
import domain.card.CardHand;

public class Dealer extends AbstractGambler {

    public static final String DEALER_NAME = "딜러";

    public Dealer(CardHand cardHand) {
        super(DEALER_NAME, cardHand);
    }

    public Card getOpenCard() {
        return cardHand.getFirst();
    }

    @Override
    public boolean canTakeMoreCard() {
        return (calculateScore() <= 16);
    }
}
