package blackjackgame.domain.user;

import blackjackgame.domain.Denomination;

public class Score {
    private static final int BUST_SCORE = 22;
    private static final int DEALER_REQUIRED_MIN_SCORE = 17;
    private static final int INITIAL_ACE_COUNT = 0;
    private static final int ACE_ONE = 1;

    private int score;

    public Score() {
        this.score = 0;
    }

    public void setScore(Hands hands) {
        int aceCount = hands.countOfAce();
        int totalScore = hands.sum();

        if (totalScore >= BUST_SCORE) {
            totalScore = calculateAceAsOne(aceCount, totalScore);
        }
        score = totalScore;
    }

    private int calculateAceAsOne(int aceCount, int score) {
        while (aceCount > INITIAL_ACE_COUNT && score >= BUST_SCORE) {
            score -= Denomination.ACE.getScore();
            score += ACE_ONE;
            aceCount--;
        }
        return score;
    }

    public PlayerStatus calculatePlayerStatus() {
        if (score >= BUST_SCORE) {
            return PlayerStatus.BUST;
        }
        return PlayerStatus.HITTABLE;
    }

    public DealerStatus calculateDealerStatus() {
        if (score >= BUST_SCORE) {
            return DealerStatus.BUST;
        }
        if (score < DEALER_REQUIRED_MIN_SCORE) {
            return DealerStatus.UNDER_MIN_SCORE;
        }
        return DealerStatus.NORMAL;
    }

    public boolean isLessThanBustScore() {
        return score < BUST_SCORE;
    }

    public int getScore() {
        return score;
    }
}
