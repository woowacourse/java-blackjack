package blackjack.domain;

import java.util.Objects;

public class BetAmount {

    private static final int MIN_BET_AMOUNT = 1_000;
    private static final int MAX_BET_AMOUNT = 100_000;
    private static final int BET_AMOUNT_UNIT = 100;
    private static final int CONVERT_SIGN = -1;

    private final int value;

    private BetAmount(int value) {
        this.value = value;
    }

    public static BetAmount fromPlayer(int value) {
        validateAmount(value);
        return new BetAmount(value);
    }

    public static BetAmount from(int value) {
        return new BetAmount(value);
    }

    private static void validateAmount(int value) {
        if (value < MIN_BET_AMOUNT) {
            throw new IllegalArgumentException("베팅 금액은 1,000원 이상부터 가능합니다.");
        }
        if (value > MAX_BET_AMOUNT) {
            throw new IllegalArgumentException("베팅 금액은 10만원까지만 가능합니다.");
        }
        if (isNotBetAmountUnit(value)) {
            throw new IllegalArgumentException("베팅 금액은 100원 단위만 가능합니다.");
        }
    }

    private static boolean isNotBetAmountUnit(int value) {
        return value % BET_AMOUNT_UNIT != 0;
    }

    public BetAmount multiple(double number) {
        return from((int) (value * number));
    }

    public BetAmount add(BetAmount otherBetAmount) {
        return from(this.value + otherBetAmount.value);
    }

    public BetAmount convertSign() {
        return from(this.value * CONVERT_SIGN);
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BetAmount betAmount = (BetAmount) o;
        return value == betAmount.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
