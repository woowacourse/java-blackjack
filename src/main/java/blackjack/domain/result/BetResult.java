package blackjack.domain.result;

import blackjack.domain.bet.Money;

public class BetResult {

    private final String name;
    private final Money profit;

    public BetResult(String name, Money profit) {
        this.name = name;
        this.profit = profit;
    }

    public String getName() {
        return name;
    }

    public int getProfit() {
        return profit.getAmount();
    }
}
