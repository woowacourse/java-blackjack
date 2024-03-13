package model.casino;

public enum MatchResult {
    WIN("승"), DRAW("무"), LOSE("패");

    private static final int BUST_THRESHOLD = 22;
    private final String value;

    MatchResult(String value) {
        this.value = value;
    }

    public static MatchResult of(int dealerHand, int playerHand) {
        if ((dealerHand >= BUST_THRESHOLD && playerHand < BUST_THRESHOLD)
                || (playerHand < BUST_THRESHOLD && dealerHand < playerHand)) {
            return WIN;
        }
        if (dealerHand == playerHand) {
            return DRAW;
        }
        return LOSE;
    }

    public String getValue() {
        return value;
    }
}
