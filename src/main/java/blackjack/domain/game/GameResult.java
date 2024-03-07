package blackjack.domain.game;

public enum GameResult {
    // TODO: 문자열 view 단으로 이동
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String value;

    GameResult(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }

    public GameResult reverse() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return this;
    }
}
