package domain.participant;

import domain.card.Card;

public class Dealer extends Participant {
    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_DRAW_LIMIT = 16;

    public Dealer() {
        super(DEALER_NAME);
    }

    public Card getFirstCard() {
        return hand.firstCard();
    }

    @Override
    public boolean canDraw() {
        return score() <= DEALER_DRAW_LIMIT;
    }
}
