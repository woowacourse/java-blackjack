package blackjack.domain;

public enum GameResult {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String resultText;

    GameResult(final String resultText) {
        this.resultText = resultText;
    }

    public String getText() {
        return this.resultText;
    }
}
