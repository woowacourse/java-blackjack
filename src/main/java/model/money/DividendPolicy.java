package model.money;

import java.util.function.Function;

public enum DividendPolicy {
    INIT_BLACKJACK((money) -> money.createMultiplyOf(1.5)),
    WIN((money) -> money.createMultiplyOf(1)),
    DRAW((money) -> money.createMultiplyOf(0)),
    LOSE((money) -> money.createMultiplyOf(-1)),
    UNCERTAIN((money) -> money.createMultiplyOf(1));

    private final Function<Money, Money> policy;

    DividendPolicy(Function<Money, Money> policy) {
        this.policy = policy;
    }

    public Money apply(Money money) {
        return policy.apply(money);
    }
}
