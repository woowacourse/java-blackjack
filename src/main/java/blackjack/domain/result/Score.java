package blackjack.domain.result;

public class Score {
    private static final int BUSTED_STANDARD_VALUE = 21;

    private final int scoreValue;

    public Score(int scoreValue) {
        this.scoreValue = scoreValue;
    }

    public boolean isBusted() {
        return scoreValue > BUSTED_STANDARD_VALUE;
    }

    public int getScoreValue() {
        return scoreValue;
    }
}
