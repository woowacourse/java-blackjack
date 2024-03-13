package blackjack.model.participant;

import java.util.Objects;

public class BettingAmount {
    private final int money;

    public BettingAmount(final int money) {
        validatePositiveNumber(money);
        this.money = money;
    }

    private void validatePositiveNumber(final int money) {
        if (money < 0) {
            throw new IllegalArgumentException("배팅 금액은 양수만 가능합니다.");
        }
    }

    public BettingAmount multiple(final double profitRate) {
        return new BettingAmount((int) (money * profitRate));
    }

    public BettingAmount payAll() {
        return new BettingAmount(0);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BettingAmount that = (BettingAmount) o;
        return money == that.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
