package domain.result;

import domain.user.Money;
import domain.user.Profit;
import domain.user.User;

public enum Result {
    PLAYER_WIN_WITHOUT_BLACKJACk(money -> new Profit(money.multiply(1))),
    PLAYER_WIN_WITH_BLACKJACK(money -> new Profit(money.multiply(1.5))),
    DRAW(money -> new Profit(money.multiply(0))),
    DEALER_WIN(money -> new Profit(money.multiply(1)));

    private final ProfitCalculator calculator;

    Result(ProfitCalculator calculator) {
        this.calculator = calculator;
    }

    public static Result judge(MatchRule matchRule, User player, User dealer) {
        return matchRule.match(player, dealer);
    }

    public Profit calculateProfit(Money money) {
        return calculator.calculate(money);
    }

    private interface ProfitCalculator {
        Profit calculate(Money money);
    }
}
