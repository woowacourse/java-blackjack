package object.game;

import java.util.Objects;

public class Score {
    public static final int BUST_THRESHOLD = 21;
    private static final int BLACKJACK_REQUIRED_CARD = 2;

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
        if (score > BUST_THRESHOLD) {
            return ScoreType.BUST;
        }
        if (score == BUST_THRESHOLD && cardSize == BLACKJACK_REQUIRED_CARD) {
            return ScoreType.BLACKJACK;
        }
        return ScoreType.NORMAL;
    }
}
