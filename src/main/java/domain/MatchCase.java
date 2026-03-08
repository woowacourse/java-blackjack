package domain;

public enum MatchCase {
    WIN("승"), LOSE("패"), DRAW("무");

    private final String displayName;

    MatchCase(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
