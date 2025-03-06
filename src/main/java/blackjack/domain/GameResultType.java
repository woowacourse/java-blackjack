package blackjack.domain;

public enum GameResultType {

    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String description;

    GameResultType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
