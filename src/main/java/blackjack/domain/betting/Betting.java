package blackjack.domain.betting;

import java.math.BigDecimal;
import java.util.Objects;

public class Betting {

    private static final BigDecimal MIN_BETTING_STANDARD = BigDecimal.ZERO;

    private BigDecimal bettingMoney;

    public Betting(String bettingMoney) {
        this.bettingMoney = parseMoneyInput(bettingMoney);
    }

    public Betting calculateBettingMoney(Double profitRate) {
        bettingMoney = bettingMoney.multiply(BigDecimal.valueOf(profitRate));
        return this;
    }

    public BigDecimal getBettingMoney() {
        return bettingMoney;
    }

    private BigDecimal parseMoneyInput(String bettingMoneyInput) {
        try {
            double bettingMoneyValue = Double.parseDouble(bettingMoneyInput);
            BigDecimal bettingMoney = BigDecimal.valueOf(bettingMoneyValue);
            validateMoneyRange(bettingMoney);
            return bettingMoney;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 숫자여야 합니다.");
        }
    }

    private void validateMoneyRange(BigDecimal bettingMoney) {
        if (bettingMoney.compareTo(MIN_BETTING_STANDARD) <= 0) {
            throw new IllegalArgumentException("[ERROR] 0원보다 많은 돈을 걸어야 합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Betting betting = (Betting) o;
        return Objects.equals(bettingMoney, betting.bettingMoney);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bettingMoney);
    }
}
