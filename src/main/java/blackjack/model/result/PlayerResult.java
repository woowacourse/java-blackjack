package blackjack.model.result;

public enum PlayerResult {

    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String displayName;

    PlayerResult(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
