package blackjack.domain.game;

public enum ResultType {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String displayName;

    ResultType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
