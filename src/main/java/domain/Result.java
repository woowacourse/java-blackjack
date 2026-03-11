package domain;

public enum Result {
    WIN("승"), LOSE("패"), TIE("무");

    private final String displayName;

    Result(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
