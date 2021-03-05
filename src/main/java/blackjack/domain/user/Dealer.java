package blackjack.domain.user;

import blackjack.domain.card.Card;

public class Dealer extends Participant {
    public static final Name DEALER_NAME = new Name("딜러");

    private static final int NO_MORE_DRAW_NUMBER = 16;

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
