package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

public abstract class Participant {

    private static final int BLACKJACK = 21;

    private final Hand hand;

    public Participant(Hand hand) {
        this.hand = hand;
    }

    public void hit(Card card) {
        if (canHit(calculateHandTotalClosestToBlackjack())) {
            hand.append(card);
        }
    }

    protected abstract boolean canHit(int score);

    public boolean isBust() {
        return calculateHandTotalClosestToBlackjack() > BLACKJACK;
    }

    public int calculateHandTotalClosestToBlackjack() {
        return hand.calculateScoreTotalClosestToThreshold(BLACKJACK);
    }

    public Hand getHand() {
        return hand;
    }
}
