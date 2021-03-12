package blackjack.domain.state;

import blackjack.domain.user.Money;

public class Blackjack extends Finished {
    private static final double RATIO = 1.5;

    @Override
    public double calculateProfit(Money money) {
        return money.getMoney() * RATIO;
    }
}
