package domain.result;

import domain.user.Money;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

public enum Result {
    PLAYER_WIN_WITHOUT_BLACKJACk(money -> money.multiply(1)),
    PLAYER_WIN_WITH_BLACKJACK(money -> money.multiply(1.5)),
    DRAW(money -> money.multiply(0)),
    DEALER_WIN(money -> money.multiply(1));
    private final ProfitCalculator calculator;

    Result(ProfitCalculator calculator) {
        this.calculator = calculator;
    }

    public static Result judge(MatchRule matchRule, User player, User dealer) {
        return matchRule.match(player, dealer);
    }

    public Money calculateProfit(Money money) {
        return calculator.calculate(money);
    }

    private interface ProfitCalculator {
        Money calculate(Money money);
    }
}
