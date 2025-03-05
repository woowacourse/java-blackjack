package blackjack.domain;

public enum GameResult {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String text;

    GameResult(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
