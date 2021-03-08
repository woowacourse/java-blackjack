package blackjack.domain.player;

import java.util.Objects;

public class BetAmount {
    private static final int minBetAmount = 0;

    private final int amount;

    public BetAmount(String amount) {
        this(Integer.parseInt(amount));
    }

    public BetAmount(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        if (amount <= minBetAmount) {
            throw new IllegalArgumentException("배팅금액은 0원보다 커야합니다.");
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
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BetAmount betAmount = (BetAmount) o;
        return amount == betAmount.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
