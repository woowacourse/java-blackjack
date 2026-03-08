package domain;

public class Score {
    private static final int BUST_LIMIT_SCORE = 21;
    private final int value;

    public Score(int value) {
        this.value = value;
    }

    public boolean isBust(){
        return value > BUST_LIMIT_SCORE;
    }

    public int getValue() {
        return value;
    }
}
