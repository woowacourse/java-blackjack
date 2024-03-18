package blackjack.domain.handrank;

import blackjack.domain.card.Hand;

public abstract class HandRank {

    private final Hand hand;

    protected HandRank(Hand hand) {
        this.hand = hand;
    }

    public abstract SingleMatchResult matchWithPlayer(HandRank playerHandRank);

    protected final int getScore() {
        return hand.calculateScore();
    }

    protected final boolean isBlackjack() {
        return hand.isBlackjack();
    }

    protected final boolean isBust() {
        return hand.isBusted();
    }
}
