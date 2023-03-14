package blackjack.domain.result;

import blackjack.domain.bet.Money;

public class PlayerResult {

    private final String name;
    private final int benefit;

    public PlayerResult(String name, Money benefit) {
        this.name = name;
        this.benefit = benefit.getValue();
    }

    public String getName() {
        return name;
    }

    public int getBenefit() {
        return benefit;
    }
}
