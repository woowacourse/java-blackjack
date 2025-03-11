package domain.blackjackgame;

public enum WinStatus {

    WIN("승"),
    LOSE("패"),
    DRAW("무승부");

    private final String status;

    WinStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
