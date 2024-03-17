package blackjack.domain.handrank;

import blackjack.domain.card.Hand;

public final class Blackjack implements HankRank {

    @Override
    public SingleMatchResult competeWithPlayer(HankRank playerHandRank) {
        if (playerHandRank.isBlackjack()) {
            return SingleMatchResult.DRAW;
        }
        return SingleMatchResult.DEALER_WIN;
    }

    @Override
    public int getScore() {
        return Hand.BLACKJACK_SCORE;
    }

    @Override
    public boolean isBlackjack() {
        return true;
    }

    @Override
    public boolean isBust() {
        return false;
    }
}
