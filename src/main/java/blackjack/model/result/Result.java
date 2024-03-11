package blackjack.model.result;

public enum Result {
    WIN(2.0),
    BLACKJACK(1.5),
    PUSH(1.0),
    LOSE(0.0),
    NONE(0.0);

    private final Double payoutRate;

    Result(Double payoutRate) {
        this.payoutRate = payoutRate;
    }

    public Double getPayoutRate() {
        return payoutRate;
    }
}
