package blackjack.domain.state;

import blackjack.domain.user.Money;

public class Bust extends Finished {
    private static final double RATIO = -1.0;

    @Override
    public double calculateProfit(Money money) {
        return money.getMoney() * RATIO;
    }
}
