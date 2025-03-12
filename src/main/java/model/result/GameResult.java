package model.result;

public enum GameResult {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String resultMeaning;

    GameResult(String resultMeaning) {
        this.resultMeaning = resultMeaning;
    }

    public String getResultMeaning() {
        return resultMeaning;
    }

    public GameResult findReverse() {
        if (this == GameResult.WIN) {
            return GameResult.LOSE;
        }
        if (this == GameResult.LOSE) {
            return GameResult.WIN;
        }
        return GameResult.DRAW;
    }
}
