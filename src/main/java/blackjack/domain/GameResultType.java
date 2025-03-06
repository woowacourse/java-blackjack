package blackjack.domain;

public enum GameResultType {

    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String description;

    GameResultType(String description) {
        this.description = description;
    }

    public GameResultType reverse() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public String getDescription() {
        return description;
    }
}
