package domain;

public enum WinningResult {
    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    UNDEFINED("미정");

    private final String korean;

    WinningResult(String korean) {
        this.korean = korean;
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

    public String getKorean() {
        return korean;
    }
}
