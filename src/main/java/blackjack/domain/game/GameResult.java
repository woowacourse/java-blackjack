package blackjack.domain.game;

public enum GameResult {

    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String name;

    GameResult(final String view) {
        this.name = view;
    }

    public String getName() {
        return name;
    }
}
