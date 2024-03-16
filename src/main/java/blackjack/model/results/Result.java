package blackjack.model.results;

import blackjack.vo.Money;
import java.util.function.IntUnaryOperator;

public enum Result {
    WIN_BY_BLACKJACK(money -> (int) (money * 1.5)),
    WIN(money -> money),
    PUSH(money -> 0),
    LOSE(money -> -money);

    private final IntUnaryOperator operator;

    Result(IntUnaryOperator operator) {
        this.operator = operator;
    }

    public Money calculateProfit(Money money) {
        return new Money(operator.applyAsInt(money.value()));
    }
}
