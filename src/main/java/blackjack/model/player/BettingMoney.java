package blackjack.model.player;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class BettingMoney {

    private static final int MIN_BETTING_AMOUNT = 1_000;
    private static final int MAX_BETTING_AMOUNT = 1_000_000;

    private final BigDecimal amount;

    public BettingMoney(final int amount) {
        validateBettingMoneyRange(amount);
        this.amount = BigDecimal.valueOf(amount);
    }

    private void validateBettingMoneyRange(final int amount) {
        if (amount < MIN_BETTING_AMOUNT || amount > MAX_BETTING_AMOUNT) {
            throw new IllegalArgumentException("베팅 금액은 최소 %d원 최대 %d원 까지 가능합니다.".formatted(
                    MIN_BETTING_AMOUNT, MAX_BETTING_AMOUNT
            ));
        }
    }

    public BigDecimal multiply(final double value) {
        return amount.multiply(BigDecimal.valueOf(value))
                .setScale(0, RoundingMode.FLOOR);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BettingMoney that)) {
            return false;
        }
        return Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }

}
