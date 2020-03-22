package blackjack.domain.gamer;

import blackjack.domain.card.Card;

public class Dealer extends Gamer {

    private static final int DRAW_THRESHOLD = 16;
    public static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean canDrawCard() {
        return handScore() <= DRAW_THRESHOLD;
    }

    public Card getFirstCard() {
        return hand.getFirstCard();
    }
}