package blackjack.domain;

public enum GameResult {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String result;

    GameResult(String result) {
        this.result = result;
    }

    public static GameResult compare(int score, int targetScore) {
        if (score > targetScore) {
            return WIN;
        }
        if (score < targetScore) {
            return LOSE;
        }
        return DRAW;
    }

    @Override
    public String toString() {
        return result;
    }
}
