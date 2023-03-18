package blackjack.domain.game;

import java.math.BigDecimal;

public final class Money {

    private static final Money min = new Money(0);
    private static final int MIN_BET_MONEY = 100;
    private static final int MAX_BET_MONEY = 100_000;
    private static final int BET_MONEY_UNIT = 100;

    private final BigDecimal money;

    private Money(final int money) {
        this.money = new BigDecimal(money);
    }

    public static Money betting(final int money) {
        validateBetMoney(money);
        return new Money(money);
    }

    private static void validateBetMoney(final int money) {
        validateBetMoneyUnit(money);
        validateBetMoneyRange(money);
    }

    private static void validateBetMoneyUnit(final int money) {
        if (money % BET_MONEY_UNIT != 0) {
            throw new IllegalArgumentException("베팅금액은 " + BET_MONEY_UNIT + " 단위로 입력해주세요");
        }
    }

    private static void validateBetMoneyRange(final int money) {
        if (money < MIN_BET_MONEY || money > MAX_BET_MONEY) {
            throw new IllegalArgumentException("베팅금액은 최소 " + MIN_BET_MONEY + " 이상, " + MAX_BET_MONEY + " 이하여야합니다");
        }
    }

    public Money add(final Money otherMoney) {
        return new Money(this.money.add(otherMoney.money).intValue());
    }

    public Money multiply(final double ratio) {
        return new Money(money.multiply(BigDecimal.valueOf(ratio)).intValue());
    }

    public Money calculatePrize(final GameResult result) {
        return multiply(result.getRatio());
    }

    public static Money getMin() {
        return min;
    }

    public int getValue() {
        return money.intValue();
    }
}
