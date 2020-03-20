package blackjack.domain.result;

import blackjack.domain.participant.Money;

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

    public double computeProfit(Money money) {
        return money.getBettingMoney() * profitRate;
    }

    public String getWord() {
        return word;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
