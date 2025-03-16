package domain.rule;

public enum BlackjackMatchResult {
    WIN(1),
    DRAW(0),
    LOSE(-1);

    private final double ratio;

    BlackjackMatchResult(double ratio) {
        this.ratio = ratio;
    }

    public static BlackjackMatchResult judge(int referenceHandScore, int comparedHandScore) {
        if (referenceHandScore == comparedHandScore) {
            return DRAW;
        }

        if (referenceHandScore > comparedHandScore) {
            return LOSE;
        }

        return WIN;
    }

    public double getRatio() {
        return ratio;
    }
}
