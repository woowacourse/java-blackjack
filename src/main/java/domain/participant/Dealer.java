package domain.participant;

import domain.card.Card;
import domain.card.Hand;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int THRESHOLD = 16;

    public Dealer() {
        super(DEALER_NAME);
    }

    public Card openOneCard() {
        Hand hand = getHand();
        return hand.getFirstCard();
    }

    @Override
    public boolean isPlayer() {
        return false;
    }

    @Override
    public boolean shouldDrawMore() {
        return getHandTotal() <= THRESHOLD;
    }

    public int getExtraHandSize() {
        Hand hand = getHand();
        return hand.getExtraSize();
    }
}
