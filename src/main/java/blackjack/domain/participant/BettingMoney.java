package blackjack.domain.participant;

import java.util.Objects;

public class BettingMoney {

    public static final int MINIMUM_AMOUNT = 10000;

    private final int value;

    public BettingMoney(int value) {
        validateMinimumAmount(value);
        this.value = value;
    }

    private void validateMinimumAmount(int value) {
        if (value < MINIMUM_AMOUNT) {
            throw new IllegalArgumentException(
                    String.format("배팅 금액은 %d원 이상이어야 합니다", MINIMUM_AMOUNT));
        }
    }


    public static BettingMoney from(String bettingMoneyInput) {
        return new BettingMoney(Integer.parseInt(bettingMoneyInput));
    }

    public int toInt() {
        return value;
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
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
