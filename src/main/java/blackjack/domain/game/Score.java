package blackjack.domain.game;

public enum Score {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String view;

    Score(final String view) {
        this.view = view;
    }

    public String getView() {
        return view;
    }
}
