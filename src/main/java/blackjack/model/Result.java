package blackjack.model;

public enum Result {
    WIN(1),
    DRAW(0),
    LOSE(-1);

    private final int profitRate;

    Result(int profitRate) {
        this.profitRate = profitRate;
    }

    public int getProfitRate() {
        return profitRate;
    }

    public Result opposite() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }
}
