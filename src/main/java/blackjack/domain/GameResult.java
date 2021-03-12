package blackjack.domain;

public enum GameResult {

    WIN("승"),
    LOSE("패"),
    TIE("무");

    private final String name;

    GameResult(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
