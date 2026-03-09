package domain.score;

public class Score {
    private static final int BURST_SCORE = 21;
    public static final int ACE_ADVANTAGE_SCORE = 10;

    private final int value;
    private final boolean containsAce;

    public Score(int score, boolean containsAce) {
        this.value = score;
        this.containsAce = containsAce;
    }

    // 버스트 확인
    public boolean isBurst() {
        return value > BURST_SCORE;
    }

    public Result getResult(Score otherScore) {
        if (isBurst() || (!otherScore.isBurst() && getValue() < otherScore.getValue())) {
            return Result.LOSE;
        }
        if (getValue() == otherScore.getValue()) {
            return Result.DRAW;
        }
        return Result.WIN;
    }

    public int getValue() {
        if (containsAce && !aceAdvantageIsBurst()) {
            return value + ACE_ADVANTAGE_SCORE;
        }

        return value;
    }

    private boolean aceAdvantageIsBurst() {
        return value + ACE_ADVANTAGE_SCORE > BURST_SCORE;
    }
}
