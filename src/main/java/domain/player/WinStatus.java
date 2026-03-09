package domain.player;

public enum WinStatus {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String status;

    WinStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
