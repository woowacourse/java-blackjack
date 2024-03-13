package blackjack.domain.handrank;

public final class Bust implements HankRank {

    private final int score;

    public Bust(int score) {
        this.score = score;
    }

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
    public int getScore() {
        return score;
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
