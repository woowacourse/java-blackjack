package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.rule.HitStrategy;

public class Participant {

    private static final int BLACKJACK = 21;

    private final Hand hand;
    private final HitStrategy hitStrategy;

    public Participant(Hand hand, HitStrategy hitStrategy) {
        this.hand = hand;
        this.hitStrategy = hitStrategy;
    }

    public void hit(Card card) {
        if (canHit()) {
            hand.append(card);
        }
    }

    private boolean canHit() {
        return hitStrategy.canHit(calculateHandTotalClosestToBlackjack());
    }

    public boolean isBust() {
        return calculateHandTotalClosestToBlackjack() > BLACKJACK;
    }

    public int calculateHandTotalClosestToBlackjack() {
        return hand.calculateScoreTotalClosestToThreshold(BLACKJACK);
    }
}
