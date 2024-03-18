package blackjack.domain.participant;

import java.util.Objects;

public class BetAmount {
    private static final int MIN_AMOUNT = 1000;

    private final int amount;

    public BetAmount(int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    private void validateAmount(int amount) {
        if (amount < MIN_AMOUNT) {
            throw new IllegalArgumentException(String.format("베팅금액은 1,000원 이상의 양수만 가능합니다. 입력값: %d", amount));
        }
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BetAmount betAmount)) {
            return false;
        }
        return amount == betAmount.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
