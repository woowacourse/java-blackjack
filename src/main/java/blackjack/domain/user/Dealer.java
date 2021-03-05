package blackjack.domain.user;

import blackjack.domain.card.Card;

public class Dealer extends Participant {
    private static final int DEALER_MINIMUM_SCORE = 16;
    public static final Name DEALER_NAME = Name.from("딜러");

    public Dealer() {
        super(DEALER_NAME);
    }

    public Card getFirstCard() {
        return hand.getFirstCard();
    }

    public boolean isUnderSixteen() {
        return hand.calculateScore() < DEALER_MINIMUM_SCORE;
    }
}
