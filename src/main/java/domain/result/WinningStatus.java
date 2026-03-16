package domain.result;

public enum WinningStatus {

    WIN(100),
    LOSS(-100),
    DRAW(0),
    BLCAKJACK(150);

    private final int profitRate;

    WinningStatus(int profitRate) {
        this.profitRate = profitRate;
    }

    public WinningStatus reverseResult() {
        if (this == WIN) {
            return LOSS;
        }

        if (this == LOSS) {
            return WIN;
        }

        return this;
    }

    public int getProfitRate() {
        return profitRate;
    }

}
