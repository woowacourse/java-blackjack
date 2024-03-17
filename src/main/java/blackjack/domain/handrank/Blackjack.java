package blackjack.domain.handrank;

import blackjack.domain.card.Hand;

final class Blackjack extends HandRank {

    @Override
    public SingleMatchResult matchWithPlayer(HandRank playerHandRank) {
        if (playerHandRank.isBlackjack()) {
            return SingleMatchResult.DRAW;
        }
        return SingleMatchResult.DEALER_WIN;
    }

    @Override
    protected int getScore() {
        return Hand.BLACKJACK_SCORE;
    }

    @Override
    protected boolean isBlackjack() {
        return true;
    }

    @Override
    protected boolean isBust() {
        return false;
    }
}
