package domain.participant;

import domain.Betting;
import domain.Money;
import domain.WinningStatus;
import domain.strategy.PlayerDrawStrategy;

public class Player extends Participant {
    private Money profit;
    private Betting betting;

    public Player(String name) {
        this(name, new Money(0));
    }

    public Player(String name, Money initialProfit) {
        super(name, new PlayerDrawStrategy());
        this.profit = initialProfit;
        this.betting = Betting.none();
    }

    public int profit() {
        return profit.amount();
    }

    public void bet(Money money) {
        betting = Betting.of(money.amount());
    }

    public void applyRoundResult(WinningStatus status) {
        int profitAmount = betting.profit(status);
        profit = profit.plus(profitAmount);
        betting = Betting.none();
    }
}
