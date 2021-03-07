package blackjack.domain.user;

import blackjack.domain.card.Card;

public class Dealer extends Participant {
    public static final String DEALER_NAME = "딜러";

    private static final int DEALER_MINIMUM_SCORE = 16;

    public Dealer() {
        super(DEALER_NAME);
    }

    public Card getFirstCard() {
        return hand.getFirstCard();
    }

    public boolean canDealerMoreDraw() {
        return hand.calculateScore() <= DEALER_MINIMUM_SCORE;
    }
}
