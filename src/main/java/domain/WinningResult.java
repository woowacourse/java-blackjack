package domain;

public enum WinningResult {
    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    UNDEFINED("미정");

    private final String name;

    WinningResult(String name) {
        this.name = name;
    }

    public static WinningResult calculate(int dealerScore, int playerScore) {
        if (dealerScore < playerScore) {
            return WIN;
        }
        if (dealerScore == playerScore) {
            return DRAW;
        }
        return LOSE;
    }

    public String getName() {
        return name;
    }
}
