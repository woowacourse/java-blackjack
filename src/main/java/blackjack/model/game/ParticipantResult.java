package blackjack.model.game;

public enum ParticipantResult {
    BLACKJACK("승", 1.5),
    WIN("승", 1),
    LOSE("패", -1),
    DRAW("무", 0),
    ;

    private final String detail;
    private final double rate;

    ParticipantResult(String detail, double rate) {
        this.detail = detail;
        this.rate = rate;
    }

    public String getDetail() {
        return detail;
    }

    public double getRate() {
        return rate;
    }
}
