package blackjack.domain.handrank;

public final class Blackjack implements HankRank {
    @Override
    public SingleMatchResult matchAtDealer(HankRank playerHandRank) {
        return null;
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
