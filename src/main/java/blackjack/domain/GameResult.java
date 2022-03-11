package blackjack.domain;

public enum GameResult {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String result;

    GameResult(String result) {
        this.result = result;
    }

    public static GameResult getPairResult(GameResult gameResult) {
        if(gameResult == WIN) {
            return LOSE;
        }
        if(gameResult == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public String getResult() {
        return result;
    }
}
