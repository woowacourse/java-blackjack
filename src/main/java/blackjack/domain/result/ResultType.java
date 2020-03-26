package blackjack.domain.result;

public enum ResultType {
    BLACKJACK(1.5, "블랙잭"),
    WIN(1, "승"),
    LOSE(-1, "패"),
    DRAW(0, "무");

    private final double profitRate;
    private final String word;

    ResultType(double profitRate, String word) {
        this.profitRate = profitRate;
        this.word = word;
    }

    public ResultType reverse() {
        if (this == BLACKJACK) {
            return LOSE;
        }
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return this;
    }

    public double getProfitRate() {
        return profitRate;
    }

    public String getWord() {
        return word;
    }
}
