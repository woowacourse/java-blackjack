package blackjack.domain.handrank;

public final class Blackjack implements HankRank {

    @Override
    public SingleMatchResult matchAtDealer(HankRank playerHandRank) {
        if (playerHandRank.isBlackjack()) {
            return SingleMatchResult.DRAW;
        }
        return SingleMatchResult.DEALER_WIN;
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
