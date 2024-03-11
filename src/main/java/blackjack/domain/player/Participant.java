package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

public abstract class Participant {

    private static final int BLACKJACK = 21;

    private final Hand hand;

    public Participant(Hand hand) {
        this.hand = hand;
    }

    public abstract boolean canHit();

    public void hit(Card card) {
        if (canHit()) {
            hand.append(card);
        }
    }

    public int calculateHandTotal() {
        return hand.calculateScoreTotalClosestToThreshold(BLACKJACK);
    }

    public boolean isNotBust() {
        return calculateHandTotal() <= BLACKJACK;
    }

    public Hand getHand() {
        return hand;
    }
}
