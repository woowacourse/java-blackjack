package blackjack.domain.gamer;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends Gamer {

    private static final String DEALER_NAME = "딜러";
    private static final int DRAW_THRESHOLD = 16;

    public boolean shouldDrawCard() {
        return calculateSum() <= DRAW_THRESHOLD;
    }

    public Card getOpenCard() {
        return hand.getCardStatus().get(0);
    }

    @Override
    public String getName() {
        return DEALER_NAME;
    }
}