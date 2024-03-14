package blackjack.domain.handrank;

public final class Stand implements HankRank {

    private final int score;

    public Stand(int score) {
        this.score = score;
    }

    @Override
    public SingleMatchResult matchAtDealer(HankRank playerHandRank) {
        return null;
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
        return false;
    }
}
