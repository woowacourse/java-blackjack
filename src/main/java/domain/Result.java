package domain;

public enum Result {
    WIN("승"), LOSE("패");

    private final String displayName;

    Result(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}