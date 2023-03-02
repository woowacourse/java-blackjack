package domain;

public enum ScoreState {
    STAY(17),
    BUST(21),
    HIT(16);

    private final int score;

    ScoreState(int score) {
        this.score = score;
    }

    public static ScoreState of(int score) {
        if (score > BUST.score) {
            return BUST;
        }
        if (score >= STAY.score) {
            return STAY;
        }
        return HIT;
    }
}
