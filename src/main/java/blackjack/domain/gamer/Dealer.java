package blackjack.domain.gamer;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends Gamer {

    private static final String DEALER_NAME = "딜러";
    private static final int DRAW_THRESHOLD = 16;

    public boolean shouldDrawCard() {
        return !isBusted() && calculateScore() <= DRAW_THRESHOLD;
    }

    @Override
    public String getName() {
        return DEALER_NAME;
    }

    public List<Card> getInitialHand() {
        return hand.getFirstHand();
    }
}