package blackjack.domain.handrank;

import blackjack.domain.card.Hand;

final class Stand extends HankRank {

    private final int score;

    public Stand(int score) {
        validate(score);
        this.score = score;
    }

    private void validate(int score) {
        if (score > Hand.BLACKJACK_SCORE) {
            throw new IllegalArgumentException("스탠드 점수는 블랙잭 점수보다 낮거나 같아야 합니다.");
        }
    }

    @Override
    public SingleMatchResult competeWithPlayer(HankRank playerHandRank) {
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
    protected int getScore() {
        return score;
    }

    @Override
    protected boolean isBlackjack() {
        return false;
    }

    @Override
    protected boolean isBust() {
        return false;
    }
}
