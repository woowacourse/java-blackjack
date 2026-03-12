package domain;

import java.math.BigDecimal;
import java.util.Objects;

public class BetMoney {
    public static final BetMoney ZERO = BetMoney.of(0);
    private static final BigDecimal BLACKJACK_ODDS = BigDecimal.valueOf(1.5);
    private static final BigDecimal WIN_ODDS = BigDecimal.valueOf(1.0);
    private static final BigDecimal LOSE_ODDS = BigDecimal.valueOf(-1);
    private static final BigDecimal DRAW_ODDS = BigDecimal.valueOf(0);

    private final BigDecimal value;

    private BetMoney(BigDecimal value) {
        this.value = value;
    }

    public static BetMoney of(BigDecimal value) {
        return new BetMoney(value);
    }

    public static BetMoney of(int value) {
        return new BetMoney(BigDecimal.valueOf(value));
    }

    public static BetMoney of(double value) {
        return new BetMoney(BigDecimal.valueOf(value));
    }

    public BetMoney blackjack() {
        return BetMoney.of(value.multiply(BLACKJACK_ODDS));
    }

    public BetMoney win() {
        return BetMoney.of(value.multiply(WIN_ODDS));
    }

    public BetMoney draw() {
        return BetMoney.of(value.multiply(DRAW_ODDS));
    }

    public BetMoney lose() {
        return BetMoney.of(value.multiply(LOSE_ODDS));
    }

    public BetMoney sub(BetMoney target) {
        return BetMoney.of(value.subtract(target.value));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BetMoney betMoney = (BetMoney) o;
        return Objects.equals(value, betMoney.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "BetMoney{" +
                "value=" + value +
                '}';
    }

    public BigDecimal getValue() {
        return value;
    }
}
