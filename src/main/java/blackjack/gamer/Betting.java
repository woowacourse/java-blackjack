package blackjack.gamer;

import java.math.BigDecimal;

public class Betting {
    private static final int MINIMUM_AMOUNT = 1000;

    private final BigDecimal amount;

    public Betting(BigDecimal amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.valueOf(MINIMUM_AMOUNT)) < 0) {
            throw new IllegalArgumentException("[ERROR] 베팅은 최소 1000원 이상이어야합니다.");
        }
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
