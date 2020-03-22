package blackjack.domain.result;

public enum ResultType {
    BLACKJACK("블랙잭", 1.5),
    WIN("승", 1),
    LOSE("패", -1),
    DRAW("무", 0);

    private final String word;
    private final double profitRate;

    ResultType(String word, double profitRate) {
        this.word = word;
        this.profitRate = profitRate;
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

    public String getWord() {
        return word;
    }

    public double computeProfit(double bettingMoney) {
        return this.profitRate * bettingMoney;
    }
}
