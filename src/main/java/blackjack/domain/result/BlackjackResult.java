package blackjack.domain.result;

public enum BlackjackResult {

    WIN(1),
    LOSE(-1),
    TIE(0);

    private final double earningRate;

    BlackjackResult(double earningRate) {
        this.earningRate = earningRate;
    }

    public double getEarningRate() {
        return earningRate;
    }
}
