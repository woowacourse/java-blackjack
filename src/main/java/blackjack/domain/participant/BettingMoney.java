package blackjack.domain.participant;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class BettingMoney {

    private static final int MONEY_SCALE = 0;
    private static final int MONEY_LENGTH = 4;
    private static final String MONEY_DIVIDE_STANDARD = "000";

    public static final BettingMoney ZERO = new BettingMoney("0");

    private final BigDecimal amount;

    private BettingMoney(BigDecimal bigDecimal) {
        this.amount = bigDecimal.setScale(MONEY_SCALE, RoundingMode.FLOOR);
    }

    private BettingMoney(String amount) {
        this(new BigDecimal(amount));
    }

    public static BettingMoney of(String amount) {
        validateLength(amount);
        validateDivide(amount);
        return new BettingMoney(amount);
    }

    private static void validateLength(String amount) {
        if (amount.length() < MONEY_LENGTH) {
            throw new IllegalArgumentException("배팅 금액은 1000원 이상입니다.");
        }
    }

    private static void validateDivide(String amount) {
        if (!amount.endsWith(MONEY_DIVIDE_STANDARD)) {
            throw new IllegalArgumentException("배팅 금액은 1000으로 나누어 떨어져야 합니다.");
        }
    }

    public BettingMoney times(double percent) {
        BigDecimal multiplied = BigDecimal.valueOf(percent);
        BigDecimal result = amount.multiply(multiplied);
        return new BettingMoney(result);
    }

    public BettingMoney add(BettingMoney otherBettingMoney) {
        BigDecimal add = amount.add(otherBettingMoney.amount);
        return new BettingMoney(add);
    }

    public String getAmount() {
        return amount.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BettingMoney that = (BettingMoney) o;
        return Objects.equals(amount.intValue(), that.amount.intValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
