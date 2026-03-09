package domain.participant.player;

public enum WinStatus {
    WIN("승"),
    DRAW("무"),
    LOSS("패");

    private String status;

    WinStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}