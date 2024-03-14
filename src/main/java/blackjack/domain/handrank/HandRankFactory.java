package blackjack.domain.handrank;

import blackjack.domain.card.Hand;

public final class HandRankFactory {

    private static final HankRank BLACKJACK = new Blackjack();

    private HandRankFactory() {
    }

    public static HankRank from(Hand hand) {
        if (hand.isBlackjack()) {
            return BLACKJACK;
        }
        if (hand.isBusted()) {
            return new Bust(hand.calculateScore());
        }
        return new Stand(hand.calculateScore());
    }
}
