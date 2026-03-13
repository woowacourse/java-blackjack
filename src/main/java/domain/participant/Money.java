package domain.participant;

import java.math.BigDecimal;

public class Money {
    private final BigDecimal bettingMoney;
    private static final String NEGATIVE_BETTING_MONEY_MESSAGE = "[ERROR] [%s원]은 입력할 수 없습니다.";
    private static final BigDecimal MIN_BETTING_MONEY = BigDecimal.ZERO;

    public Money(BigDecimal bettingMoney) {
        validatePositiveMoney(bettingMoney);
        this.bettingMoney = bettingMoney;
    }

    public BigDecimal getBettingMoney() {
        return bettingMoney;
    }

    private void validatePositiveMoney(BigDecimal bettingMoney) {
        if (bettingMoney.compareTo(MIN_BETTING_MONEY) < 0) {
            throw new IllegalArgumentException(
                    NEGATIVE_BETTING_MONEY_MESSAGE.formatted(bettingMoney.toPlainString()));
        }
    }
}
