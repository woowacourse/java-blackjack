package blackjack.domain.gamer;

import blackjack.domain.card.Card;

public class Dealer extends Gamer {

    private static final int DRAW_THRESHOLD = 16;

    @Override
    public boolean canDrawCard() {
        return calculateSum() <= DRAW_THRESHOLD;
    }

    @Override
    public String getName() {
        return "딜러";
    }

    public Card getOpenCard() {
        return hand.getCardStatus().get(0);
    }
}