package blackjack.resultstate;

import blackjack.money.Money;
import java.util.function.UnaryOperator;

public enum MatchResult {

    PARTICIPANT_WIN(UnaryOperator.identity()),
    PARTICIPANT_BLACKJACK_WIN(money -> money.multipliedBy(1.5)),
    DEALER_WIN(Money::negate),
    TIE(Money::tie),
    ;

    private final UnaryOperator<Money> profitCalculator;

    MatchResult(UnaryOperator<Money> profitCalculator) {
        this.profitCalculator = profitCalculator;
    }

    public Money calculateProfit(Money betMoney) {
        return profitCalculator.apply(betMoney);
    }
}
