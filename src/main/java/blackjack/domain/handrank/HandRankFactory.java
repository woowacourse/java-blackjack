package blackjack.domain.handrank;

import blackjack.domain.card.Hand;

public final class HandRankFactory {

    private HandRankFactory() {
    }

    public static HandRank from(Hand hand) {
        if (hand.isBlackjack()) {
            return new Blackjack(hand);
        }
        if (hand.isBusted()) {
            return new Bust(hand);
        }
        return new Stand(hand);
    }
}
