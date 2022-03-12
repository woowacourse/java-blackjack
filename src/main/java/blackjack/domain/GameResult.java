package blackjack.domain;

public enum GameResult {
    LOSE("패"),
    DRAW("무"),
    WIN("승");

    private final String result;

    GameResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return this.result;
    }
}
