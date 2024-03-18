package domain.vo;

import static domain.BlackjackGame.BLACKJACK_SCORE;

public record Score(int value) {
    public boolean isWin(final Score target) {
        int targetScore = target.value;
        return value <= BLACKJACK_SCORE
                && (targetScore > BLACKJACK_SCORE || (targetScore < value));
    }

    public boolean isDraw(final Score target) {
        int targetScore = target.value;
        return (targetScore > BLACKJACK_SCORE && value > BLACKJACK_SCORE)
                || (targetScore == value);
    }

    public boolean isLose(final Score target) {
        int targetScore = target.value;
        return targetScore <= BLACKJACK_SCORE
                && (value > BLACKJACK_SCORE || (value < targetScore));
    }

    public boolean isEqualOrLessThan(final int target) {
        return value <= target;
    }
}
