package model;

public enum MatchStatus {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    MatchStatus(String status) {
        this.status = status;
    }

    private final String status;

    public String getStatus() {
        return status;
    }
}
