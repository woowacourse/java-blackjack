package blackjack.domain.game;

public enum GameResult {

    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String view;

    GameResult(final String view) {
        this.view = view;
    }

    public String getView() {
        return view;
    }
}
