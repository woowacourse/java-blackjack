package blackjack.domain.handrank;

public final class Stand implements HankRank {

    private final int score;

    public Stand(int score) {
        this.score = score;
    }

    @Override
    public SingleMatchResult matchAtDealer(HankRank playerHandRank) {
        if (playerHandRank.isBlackjack()) {
            return SingleMatchResult.PLAYER_BLACKJACK;
        }
        if (playerHandRank.isBust()) {
            return SingleMatchResult.DEALER_WIN;
        }
        return matchThroughScore(playerHandRank);
    }

    private SingleMatchResult matchThroughScore(HankRank playerHandRank) {
        if (playerHandRank.getScore() > this.getScore()) {
            return SingleMatchResult.PLAYER_WIN;
        }
        if (playerHandRank.getScore() == this.getScore()) {
            return SingleMatchResult.DRAW;
        }
        return SingleMatchResult.DEALER_WIN;
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
