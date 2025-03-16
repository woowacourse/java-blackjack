package domain.participant;

import domain.result.ResultStatus;

import java.util.Objects;

public class BetAmount {

    private final int amount;

    public BetAmount(int amount) {
        validatePrice(amount);
        this.amount = amount;
    }

    private void validatePrice(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 최소 1원 입니다.");
        }
    }

    public int calculateIncome(ResultStatus resultStatus) {
        return resultStatus.calculateIncome(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BetAmount betAmount = (BetAmount) o;
        return amount == betAmount.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }
}
