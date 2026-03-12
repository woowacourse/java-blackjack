package blackjack.domain.bet;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BetAmount {
    private final BigDecimal amount;

    public BetAmount(int amount) {
        validatePositive(amount);
        this.amount = new BigDecimal(amount);
    }

    private void validatePositive(int input) {
        if (input <= 0) {
            throw new IllegalArgumentException("베팅 금액은 1원 이상이여야 합니다.");
        }
    }

    public int calculateProfit(BigDecimal payoutRate) {
        return amount.multiply(payoutRate)
                .setScale(0, RoundingMode.FLOOR)
                .intValue();
    }
}
