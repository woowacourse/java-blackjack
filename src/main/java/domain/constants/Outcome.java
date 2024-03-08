package domain.constants;

public enum Outcome {
    WIN,
    LOSE;

    public static Outcome from(final boolean isWinner) {
        if (isWinner) {
            return Outcome.WIN;
        }
        return Outcome.LOSE;
    }
}
