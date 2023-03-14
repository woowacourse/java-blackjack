package blackjack.domain.participant;

import java.util.Objects;

public class BettingAmount {

    public static final BettingAmount ZERO = new BettingAmount(0);

    private final int value;

    public BettingAmount(int value) {
        this.value = value;
    }

    private static void validateBettingAmount(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("1원 이상의 배팅금액을 입력해주세요.");
        }
    }

    public BettingAmount updateBettingAmount(int bettingAmount) {
        validateBettingAmount(bettingAmount);
        return new BettingAmount(bettingAmount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BettingAmount bettingAmount = (BettingAmount) o;
        return value == bettingAmount.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public int getValue() {
        return value;
    }
}
