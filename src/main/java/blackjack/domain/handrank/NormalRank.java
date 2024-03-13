package blackjack.domain.handrank;

public final class NormalRank implements HankRank {

    private final int score;

    public NormalRank(int score) {
        this.score = score;
    }

    @Override
    public SingleMatchResult matchAtDealer(HankRank playerHandRank) {
        return null;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBust() {
        return false;
    }
}
