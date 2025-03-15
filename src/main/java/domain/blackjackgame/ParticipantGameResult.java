package domain.blackjackgame;

public enum ParticipantGameResult {

    WIN("승"),
    LOSE("패"),
    DRAW("무승부");

    private final String status;

    ParticipantGameResult(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public boolean isDraw() {
        return this == DRAW;
    }

    public boolean isWin() {
        return this == WIN;
    }

    public boolean isLose() {
        return this == LOSE;
    }
}
