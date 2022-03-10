package blackjack.domain;

public enum GameResult {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String result;

    GameResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return this.result;
    }
}
