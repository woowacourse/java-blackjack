package domain.result;

public enum WinState {

    WIN(1),
    DRAW(0),
    LOSE(-1),
    BLACK_JACK(1.5);

    private final double profitRate;

    WinState(double profitRate) {
        this.profitRate = profitRate;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
