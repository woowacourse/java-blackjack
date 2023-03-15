package domain.result;

public enum Outcome {

    WIN, DRAW, LOSE;

    private static final int BLACK_JACK_NUMBER = 21;

    public static Outcome reverse(final Outcome outcome) {
        if (outcome == Outcome.WIN) {
            return Outcome.LOSE;
        }
        if (outcome == Outcome.LOSE) {
            return Outcome.WIN;
        }
        return Outcome.DRAW;
    }

    public static Outcome decide(final int score, final int otherScore) {
        if (lose(score, otherScore)) {
            return Outcome.LOSE;
        }
        if (win(score, otherScore)) {
            return Outcome.WIN;
        }
        return Outcome.DRAW;
    }

    private static boolean lose(final int score, final int otherScore) {
        if (score > BLACK_JACK_NUMBER) {
            return true;
        }
        if (score < otherScore && otherScore <= BLACK_JACK_NUMBER) {
            return true;
        }
        return false;
    }

    private static boolean win(final int score, final int otherScore) {
        if (otherScore > BLACK_JACK_NUMBER) {
            return true;
        }
        if (score > otherScore) {
            return true;
        }
        return false;
    }
}
