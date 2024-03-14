package model.money;

import java.util.function.Function;

public enum DividendPolicy {
    INIT_BLACKJACK((money) -> money.createMultiplyOf(1.5)),
    NORMAL_WIN((money) -> money.createMultiplyOf(1)),
    NORMAL_DRAW((money) -> money.createMultiplyOf(0)),
    NORMAL_LOSE((money) -> money.createMultiplyOf(-1)),
    UNCERTAIN((money) -> money.createMultiplyOf(1));

    private final Function<Money, Money> policy;

    DividendPolicy(Function<Money, Money> policy) {
        this.policy = policy;
    }

    public Money apply(Money money) {
        return policy.apply(money);
    }
}
