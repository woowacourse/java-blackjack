package blackjack.model.game;

public enum ParticipantResult {
    WIN("승"),
    LOSE("패"),
    DRAW("무"),
    ;

    private final String detail;

    ParticipantResult(String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }
}
