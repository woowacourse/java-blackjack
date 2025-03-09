package domain;

public enum BlackjackMatchResult {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String state;

    BlackjackMatchResult(String state) {
        this.state = state;
    }

    public static BlackjackMatchResult judge(int referenceHandScore, int comparedHandScore) {
        if (referenceHandScore == comparedHandScore) {
            return DRAW;
        }

        if (referenceHandScore > comparedHandScore) {
            return WIN;
        }

        return LOSE;
    }

    public String getState() {
        return state;
    }
}
