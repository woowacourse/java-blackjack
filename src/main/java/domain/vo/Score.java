package domain.vo;

import static domain.BlackjackGame.BLACKJACK_SCORE;

public record Score(int value) {
    public boolean isWin(Score target) {
        int targetScore = target.value;
        return value <= BLACKJACK_SCORE
                && (targetScore > BLACKJACK_SCORE || (targetScore < value));
    }

    public boolean isDraw(Score target) {
        int targetScore = target.value;
        return (targetScore > BLACKJACK_SCORE && value > BLACKJACK_SCORE)
                || (targetScore == value);
    }

    public boolean isLose(Score target){
        int targetScore = target.value;
        return targetScore <= BLACKJACK_SCORE
                && (value > BLACKJACK_SCORE || (value < targetScore));
    }

    public boolean isEqualOrLessThan(int target){
        return value <= target;
    }
}
