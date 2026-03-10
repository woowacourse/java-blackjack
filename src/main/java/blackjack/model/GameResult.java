package blackjack.model;

public enum GameResult {
    WIN("승", 1.0),
    LOSE("패", -1.0),
    DRAW("무", 1.0),
    BLACK_JACK("블랙잭", 1.5);

    private final String status;
    private final Double payout;

    GameResult(String status, Double payout) {
        this.status = status;
        this.payout = payout;
    }

    public String getStatus() {
        return status;
    }

    public Double getPayout() {
        return payout;
    }
}
