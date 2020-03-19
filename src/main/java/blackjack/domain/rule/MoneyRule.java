package blackjack.domain.rule;

import java.util.Arrays;
import java.util.function.Predicate;

public enum MoneyRule {
    WIN_BLACK_JACK_MONEY(1.5, basicRule -> basicRule == BasicRule.WIN_BLACK_JACK),
    WIN_MONEY(1, basicRule -> basicRule == BasicRule.WIN),
    DRAW_MONEY(0, basicRule -> basicRule == BasicRule.DRAW),
    LOSE_MONEY(-1, basicRule -> basicRule == BasicRule.LOSE);

    private final double multiplyValue;
    private final Predicate<BasicRule> expression;

    MoneyRule(double multiplyValue, Predicate<BasicRule> expression) {
        this.multiplyValue = multiplyValue;
        this.expression = expression;
    }

    public static MoneyRule getMoneyRule(BasicRule basicRule) {
        return Arrays.stream(MoneyRule.values())
            .filter(moneyRule -> moneyRule.expression.test(basicRule))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("BasicRule 조건에 없습니다!"));
    }

    public double getMultiplyValue() {
        return multiplyValue;
    }

    // public static Money applyMoney(Money bettingMoney, BasicRule basicRule) {
    //     return bettingMoney.multiply(getMoneyRule(basicRule).getMultiplyValue());
    // }
}
