package domain.participant;

public class Score {
    private final ScoreType scoreType;
    private final int score;

    public Score(int score, int cardSize) {
        this.scoreType = getScoreType(score, cardSize);
        this.score = score;
    }

    private ScoreType getScoreType(int score, int cardSize) {
        if (score > 21) {
            return ScoreType.BUST;
        }
        if (score == 21 && cardSize == 2) {
            return ScoreType.BLACKJACK;
        }
        return ScoreType.NORMAL;
    }
}
