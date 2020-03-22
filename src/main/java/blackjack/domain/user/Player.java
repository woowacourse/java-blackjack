package blackjack.domain.user;

import blackjack.domain.user.component.BettingAmount;
import blackjack.domain.result.ResultType;
import blackjack.domain.user.component.Name;
import blackjack.domain.user.component.Point;

import java.util.Objects;

public class Player extends User {
    private BettingAmount bettingAmount;

    public Player(String name) {
        super(name);
    }

    public Player(Name name, BettingAmount bettingAmount) {
        super(name);

        Objects.requireNonNull(bettingAmount);
        this.bettingAmount = bettingAmount;
    }

    @Override
    public boolean receivable() {
        return !(new Point(cards).isBust());
    }

    public double computeProfit(ResultType resultType) {
        return bettingAmount.getBettingMoney() * resultType.getProfitRate();
    }
}
