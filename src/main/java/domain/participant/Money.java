package domain.participant;

import java.math.BigDecimal;

public class Money {
    private final BigDecimal bettingMoney;
    private static final String NEGATIVE_BETTING_MONEY_MESSAGE = "[ERROR] 배팅금은 0보다 커야 합니다!";
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
            throw new IllegalArgumentException(NEGATIVE_BETTING_MONEY_MESSAGE);
        }
    }
}
