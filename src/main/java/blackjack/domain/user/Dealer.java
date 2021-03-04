package blackjack.domain.user;

import blackjack.domain.card.Card;

public class Dealer extends Participant{
    private static final int NO_MORE_DRAW_NUMBER = 16;
    public static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    public Card getFirstCard() {
        return hand.getFirstCard();
    }

    public boolean isUnderSixteen() {
        return hand.calculateScore() < NO_MORE_DRAW_NUMBER;
    }
}
