package blackjack.domain.handrank;

public final class Bust implements HankRank {

    @Override
    public SingleMatchResult matchAtDealer(HankRank playerHandRank) {
        if (playerHandRank.isBust()) {
            return SingleMatchResult.DEALER_WIN;
        }
        if (playerHandRank.isBlackjack()) {
            return SingleMatchResult.PLAYER_BLACKJACK;
        }
        return SingleMatchResult.PLAYER_WIN;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBust() {
        return true;
    }
}
