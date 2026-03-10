package model;

public enum MatchStatus {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String status;

    MatchStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}


