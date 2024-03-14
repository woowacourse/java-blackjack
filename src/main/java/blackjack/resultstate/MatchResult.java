package blackjack.resultstate;

import blackjack.money.Money;
import java.util.function.UnaryOperator;

public enum MatchResult {

    PARTICIPANT_WIN(Money::profitOnWin),
    PARTICIPANT_BLACKJACK_WIN(Money::profitOnBlackJackWin),
    DEALER_WIN(Money::profitOnLose),
    TIE(Money::profitOnTie),
    ;

    private final UnaryOperator<Money> profitCalculator;

    MatchResult(UnaryOperator<Money> profitCalculator) {
        this.profitCalculator = profitCalculator;
    }

    public Money calculateProfit(Money betMoney) {
        return profitCalculator.apply(betMoney);
    }
}
