package blackjack.domain.handrank;

import blackjack.domain.card.Hand;

final class Bust extends HankRank {

    private final int score;

    public Bust(int score) {
        validate(score);
        this.score = score;
    }

    private void validate(int score) {
        if (score <= Hand.BLACKJACK_SCORE) {
            throw new IllegalArgumentException("버스트 점수는 블랙잭 점수보다 높아야 합니다.");
        }
    }

    @Override
    public SingleMatchResult competeWithPlayer(HankRank playerHandRank) {
        if (playerHandRank.isBust()) {
            return SingleMatchResult.DEALER_WIN;
        }
        if (playerHandRank.isBlackjack()) {
            return SingleMatchResult.PLAYER_BLACKJACK;
        }
        return SingleMatchResult.PLAYER_WIN;
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
        return true;
    }
}
