package blackjack.object;

public enum WinningType {
    WIN("승"),
    DEFEAT("패"),
    DRAW("무");
    private final String displayName;

    WinningType(final String displayName) {
        this.displayName = displayName;
    }
}
