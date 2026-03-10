package domain;

public enum WinningStatus {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String description;

    WinningStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
