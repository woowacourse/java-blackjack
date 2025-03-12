package domain.participant;

import java.util.Objects;

public class Score {
    private final ScoreType scoreType;
    private final int score;

    private Score(ScoreType scoreType, int score) {
        this.scoreType = scoreType;
        this.score = score;
    }

    public static Score of(int score, int cardSize) {
        ScoreType scoreType = getScoreType(score, cardSize);
        return new Score(scoreType, score);
    }

    public boolean isBlackJack() {
        return Objects.equals(scoreType, ScoreType.BLACKJACK);
    }

    public boolean isBust() {
        return Objects.equals(scoreType, ScoreType.BUST);
    }

    public int getScore() {
        return score;
    }

    private static ScoreType getScoreType(int score, int cardSize) {
        if (score > 21) {
            return ScoreType.BUST;
        }
        if (score == 21 && cardSize == 2) {
            return ScoreType.BLACKJACK;
        }
        return ScoreType.NORMAL;
    }
}
