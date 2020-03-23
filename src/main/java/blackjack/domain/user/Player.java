package blackjack.domain.user;

import blackjack.domain.user.component.BettingAmount;
import blackjack.domain.result.ResultType;
import blackjack.domain.user.component.Name;

import java.util.Objects;

public class Player extends User {
    private final BettingAmount bettingAmount;

    public Player(Name name, BettingAmount bettingAmount) {
        super(name);

        Objects.requireNonNull(bettingAmount);
        this.bettingAmount = bettingAmount;
    }

    @Override
    public boolean receivable() {
        return !getCards().isBust();
    }

    public double computeProfit(ResultType resultType) {
        return bettingAmount.getBettingMoney() * resultType.getProfitRate();
    }
}
