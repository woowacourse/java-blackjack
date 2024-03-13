package blackjack.domain.handrank;

import blackjack.domain.card.Hand;

public final class HandRankFactory {

    public static HankRank from(Hand hand) {
        if (hand.isBlackjack()) {
            return new Blackjack();
        }
        if (hand.isBusted()) {
            return new Bust();
        }
        return new NormalRank(hand.calculateScore());
    }
}
