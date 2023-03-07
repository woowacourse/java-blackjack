package domain;

public class Score {
    private static final Score MAX = new Score(21);
    private static final Score ACE_GAP = new Score(10);

    private final int value;

    private Score(int value) {
        this.value = value;
    }

    public static Score from(Cards cards) {
        Score sumOfScore = new Score(cards.getSumOfScore());

        return applyAce(sumOfScore, cards.countAce());
    }

    private static Score applyAce(Score score, int aceCount) {
        while (score.isGreaterThan(MAX) && aceCount > 0) {
            score = score.minus(ACE_GAP);
            aceCount--;
        }

        return score;
    }

    public Score minus(Score other) {
        return new Score(value - other.value);
    }

    public boolean isGreaterThan(Score other) {
        return this.value > other.value;
    }

    public boolean isEqualTo(Score other) {
        return this.value == other.value;
    }

    public Result competeByScore(Score otherScore) {
        if (this.isGreaterThan(otherScore)) {
            return Result.WIN;
        }

        if (this.isEqualTo(otherScore)) {
            return Result.DRAW;
        }

        return Result.LOSE;
    }

    public int getValue() {
        return value;
    }
}
