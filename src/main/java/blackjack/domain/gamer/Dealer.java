package blackjack.domain.gamer;

import blackjack.domain.card.Card;

public class Dealer extends Gamer {

    private static final int DEALER_HIT_MAX_SCORE = 16;

    public Dealer() {
    }

    public Card getFirstCard() {
        return getHandDeck().get(0);
    }

    @Override
    public boolean canHit() {
        return calculateTotalScore() <= DEALER_HIT_MAX_SCORE;
    }
}
