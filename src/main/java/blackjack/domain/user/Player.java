package blackjack.domain.user;

import blackjack.domain.result.Profit;
import blackjack.domain.result.Rule;

public class Player extends AbstractUser {
    private final BettingMoney bettingMoney;

    private Player(String name, String bettingMoney) {
        super(name, Cards.emptyCards());
        this.bettingMoney = BettingMoney.from(bettingMoney);
    }

    public static Player of(String name, String bettingMoney) {
        return new Player(name, bettingMoney);
    }

    public Profit calculateProfit(User user) {
        double profitRate = Rule.getProfitRate(this, user);
        return new Profit(bettingMoney.multiplyAndGetValue(profitRate));
    }
}