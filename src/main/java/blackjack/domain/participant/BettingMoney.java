package blackjack.domain.participant;

import java.math.BigDecimal;
import java.util.Objects;

public class BettingMoney {

    private final BigDecimal amount;

    private BettingMoney(BigDecimal bigDecimal) {
        this.amount = bigDecimal;
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
        if (amount.length() < 4) {
            throw new IllegalArgumentException("배팅 금액은 1000원 이상입니다.");
        }
    }

    private static void validateDivide(String amount) {
        if (!amount.endsWith("000")) {
            throw new IllegalArgumentException("배팅 금액은 1000으로 나누어 떨어져야 합니다.");
        }
    }

    public BettingMoney times(double percent) {
        BigDecimal multiplied = BigDecimal.valueOf(percent);
        BigDecimal result = amount.multiply(multiplied);
        return new BettingMoney(result);
    }

    public int getAmount() {
        return amount.intValue();
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
