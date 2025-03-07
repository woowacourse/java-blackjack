package domain;

public enum MatchResult {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private static final int BLACKJACK_NUMBER = 21;

    private final String value;

    MatchResult(String value) {
        this.value = value;
    }

    public static MatchResult calculateWinner(int dealerSum, int playerSum) {
        if (dealerSum > playerSum || playerSum > BLACKJACK_NUMBER) {
            return LOSE;
        }
        if (dealerSum < playerSum) {
            return WIN;
        }
        return DRAW;
    }

    public String getValue() {
        return value;
    }
}