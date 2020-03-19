package domain.result;

import domain.money.Money;
import domain.user.Dealer;
import domain.user.Player;

public enum Result {
    PLAYER_WIN_WITHOUT_BLACKJACk(money -> money.multiply(1)),
    PLAYER_WIN_WITH_BLACKJACK(money -> money.multiply(1.5)),
    DRAW(money -> money.multiply(0)),
    DEALER_WIN(money -> money.multiply(1));
    private final ProfitCalculator calculator;

    Result(ProfitCalculator calculator) {
        this.calculator = calculator;
    }

    static Result judge(MatchService matchService, Player player, Dealer dealer) {
        return matchService.match(player, dealer);
    }

    Money calculateProfit(Money money) {
        return calculator.calculate(money);
    }

    private interface ProfitCalculator {
        Money calculate(Money money);
    }
}
