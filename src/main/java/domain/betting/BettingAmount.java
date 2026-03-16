package domain.betting;

import java.math.BigDecimal;
import java.util.Objects;

public class BettingAmount {
    private final BigDecimal money;

    public BettingAmount(BigDecimal bettingAmount) {
        validateMinus(bettingAmount);
        this.money = bettingAmount;
    }

    private void validateMinus(BigDecimal bettingAmount) {
        if (bettingAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("베팅 금액은 음수일 수 없습니다.");
        }
    }

    public BigDecimal getMoney() {
        return money;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BettingAmount that = (BettingAmount) o;
        return Objects.equals(money, that.money);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(money);
    }
}
