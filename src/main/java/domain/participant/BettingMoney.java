package domain.participant;

import java.util.Objects;

public class BettingMoney {
    private final int amount;

    public BettingMoney(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("베팅 금액이 0이하 일 수 없습니다.");
        }
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BettingMoney that = (BettingMoney) o;
        return amount == that.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }
}
