package model.blackjackgame;

public enum ResultStatus {
    WIN("승"),
    PUSH("무"),
    LOOSE("패");

    private final String displayName;

    ResultStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
