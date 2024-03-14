package blackjack.domain.rule;

import blackjack.domain.bet.Money;

public class BetResult {

    private final String name;
    private final Money earned;

    public BetResult(String name, Money earned) {
        this.name = name;
        this.earned = earned;
    }

    public String getName() {
        return name;
    }

    public int getEarned() {
        return earned.getAmount();
    }
}
