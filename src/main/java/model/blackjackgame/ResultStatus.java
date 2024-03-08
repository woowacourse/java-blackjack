package model.blackjackgame;

public enum ResultStatus {
    WIN("승"),
    LOOSE("패"),
    PUSH("무");

    private final String displayName;

    ResultStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
